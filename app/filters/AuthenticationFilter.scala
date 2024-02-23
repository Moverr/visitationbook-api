package filters

import akka.stream.Materializer
import play.api.Logger
import play.api.http.Status.UNAUTHORIZED
import play.api.libs.json.Json
import play.api.mvc.Results.Unauthorized
import play.api.mvc._

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


import exceptions._

class AuthenticationFilter @Inject()(userManager: TUserManager, implicit val mat: Materializer) extends Filter {

  private implicit val ec = ExecutionContext.global
  private val exactPaths: Seq[String] = Seq("/", "/")
  private val relativePaths: Seq[String] = Seq("/assets/")

  @Override def apply(nextFilter: RequestHeader => Future[Result])(requestHeader: RequestHeader): Future[Result] = {

    val requestedAPiPath = requestHeader.uri
    log.info(s"------------Path :  $requestedAPiPath -------")

    val rexte = shouldExclude(requestedAPiPath)
    log.info("Passing the information ")
    log.info("building it closely ")

    rexte match {
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
                case _: Boolean => nextFilter(requestHeader)
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