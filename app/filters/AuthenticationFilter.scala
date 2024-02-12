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

class AuthenticationFilter @Inject()(userManager: UserManager, implicit val mat: Materializer) extends Filter {

  private def log = Logger(this.getClass).logger

  private def shouldExclude(path: String): Boolean = {
    log.info(s"------------Path :  $path -------")
    path.equalsIgnoreCase("/") || path.startsWith("/assets/")
  }

  @Override def apply(nextFilter: RequestHeader => Future[Result])(requestHeader: RequestHeader): Future[Result] = {

    val requestedAPiPath = requestHeader.uri

    if(shouldExclude(requestedAPiPath)){
      log.info(s" excluded requestedAPiPath :  $requestedAPiPath -   -")
      nextFilter(requestHeader)
    }else{

      log.info(s" requestedAPiPath needs to be validated :  $requestedAPiPath -  -")
      val ec = ExecutionContext.global
      val dataMap = requestHeader.headers.toMap
      val bearerInfo = dataMap.get("Authorization")

      val requestApi = dataMap.get("Raw-Request-URI")


      val token = bearerInfo flatMap (x => x.headOption.filter(_.nonEmpty))
      val res = token.getOrElse("NONE")
      log.info(s"  header :    ${requestApi.get.head} ")

      if (!res.equalsIgnoreCase("none")) {

        userManager.isAuthenticated(res)
          .flatMap {
            status =>
              if (status)
                nextFilter(requestHeader)
              else
                {


                  val exception = ErrorException("un authorized access", "Unauthorized", UNAUTHORIZED)
                  val unauthorizedJson = ExceptionHandler.errorExceptionWrites.writes(exception)

                  val unAuthorizedAccess = Json.toJson(unauthorizedJson)
                  Future.successful(Unauthorized(unAuthorizedAccess))
                }

          }(ec)


      } else {
        val exception = ErrorException("un authorized access", "Unauthorized", UNAUTHORIZED)
        val unauthorizedJson = ExceptionHandler.errorExceptionWrites.writes(exception)

        val unAuthorizedAccess = Json.toJson(unauthorizedJson)
        Future.successful(Unauthorized(unAuthorizedAccess))
      }

    }



  }
}