package filters

import akka.stream.Materializer
import play.api.Logger
import play.api.http.Status.{BAD_REQUEST, INTERNAL_SERVER_ERROR, UNAUTHORIZED}
import play.api.libs.json.Json
import play.api.mvc.Results._
import play.api.mvc._

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import exceptions._
import jdk.jshell.spi.ExecutionControl.InternalException
import models.dtos.Auth

class AuthenticationFilter @Inject()(userManager: UserManager, implicit val mat: Materializer) extends Filter {

  private implicit val ec = ExecutionContext.global
  private val exactPaths: Seq[String] = Seq("/", "/")
  private val relativePaths: Seq[String] = Seq("/assets/")

  @Override def apply(nextFilter: RequestHeader => Future[Result])(requestHeader: RequestHeader): Future[Result] = {

    val requestedAPiPath = requestHeader.uri
    log.info(s"------------Path :  $requestedAPiPath -------")

    val isUrlExcluded = shouldExclude(requestedAPiPath)


    isUrlExcluded match {
      case true =>
        log.info(s" excluded requestedAPiPath :  $requestedAPiPath -   -")
        nextFilter(requestHeader)
      case false =>
        log.info(s" requestedAPiPath needs to be validated :  $requestedAPiPath -  -")
        val dataMap = requestHeader.headers.toMap
        val bearerInfo = dataMap.get("Authorization")
        val requestApi = dataMap.get("Raw-Request-URI")

        val token = bearerInfo.flatMap(_.headOption.filter(_.nonEmpty)).getOrElse("NONE")


        token match {
          case "NONE" =>
            log.info(s" No token provided  ")

            val exception = ErrorException("un authorized access", "Unauthorized", UNAUTHORIZED)
            val unauthorizedJson = ExceptionHandler.errorExceptionWrites.writes(exception)

            val unAuthorizedAccess = Json.toJson(unauthorizedJson)
            Future.successful(Unauthorized(unAuthorizedAccess))

          case _ =>
            log.info(s"  token  provided :    ${token} ")
            userManager.isAuthenticated(token)
              .flatMap {
                case Left(exc: Throwable) =>

                  exc.printStackTrace()
                  log.warn(s" exeption ${exc.getMessage}")

                  val exceptionStatus = exc match {

                    case _: IllegalArgumentException =>
                      val exception = ErrorException(exc.getLocalizedMessage, exc.getMessage, UNAUTHORIZED)
                      val unauthorizedJson = ExceptionHandler.errorExceptionWrites.writes(exception)
                      val unAuthorizedAccess = Json.toJson(unauthorizedJson)
                      Unauthorized(unAuthorizedAccess)
                    case _: BadRequestException =>
                      val exception = ErrorException(exc.getLocalizedMessage, exc.getMessage, BAD_REQUEST)
                      val unauthorizedJson = ExceptionHandler.errorExceptionWrites.writes(exception)
                      val unAuthorizedAccess = Json.toJson(unauthorizedJson)
                      BadRequest(unAuthorizedAccess)

                    case _: InternalException =>
                      val exception = ErrorException(exc.getLocalizedMessage, exc.getMessage, INTERNAL_SERVER_ERROR)
                      val exceptionJson = ExceptionHandler.errorExceptionWrites.writes(exception)
                      val exceptiontoJson = Json.toJson(exceptionJson)
                      InternalServerError(exceptiontoJson)

                    case _  =>
                      val exception = ErrorException(exc.getLocalizedMessage, exc.getMessage, INTERNAL_SERVER_ERROR)
                      val exceptionJson = ExceptionHandler.errorExceptionWrites.writes(exception)
                      val exceptiontoJson = Json.toJson(exceptionJson)
                      InternalServerError(exceptiontoJson)

                  }

                  Future.successful(exceptionStatus)

                case Right(auth: Auth) => nextFilter(requestHeader)
                case _ =>
                  val exception = ErrorException("un authorized access", "Unauthorized", UNAUTHORIZED)
                  val unauthorizedJson = ExceptionHandler.errorExceptionWrites.writes(exception)
                  val unAuthorizedAccess = Json.toJson(unauthorizedJson)
                  Future.successful(Unauthorized(unAuthorizedAccess))
              }

        }

    }


  }

  private lazy val log = Logger(this.getClass).logger

  private def shouldExclude(path: String): Boolean = exactPaths.exists(item => item.equalsIgnoreCase(path)) || relativePaths.exists(item => item.startsWith(path))
}