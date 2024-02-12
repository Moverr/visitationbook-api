package filters

import akka.stream.Materializer
import play.api.Logger
import play.api.http.Status.UNAUTHORIZED
import play.api.libs.json.Json
import play.api.mvc.Results.Unauthorized
import play.api.mvc._

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


class AuthenticationFilter @Inject()(userManager: UserManager, implicit val mat: Materializer) extends Filter {

  def log = Logger(this.getClass).logger


  private def shouldExclude(path: String) = {
    log.info(s"------------Path :  $path -------")

    path.equalsIgnoreCase("/")|| path.startsWith("/assets/")
  }

  @Override def apply(nextFilter: RequestHeader => Future[Result])(requestHeader: RequestHeader): Future[Result] = {


    //todo: work on the exclusion
    val path = requestHeader.uri

    if(shouldExclude(path)){
      log.info(s" excluded path :  $path -   -")
      nextFilter(requestHeader)
    }else{

      log.info(s" path needs to be validated :  $path -  -")
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
                  import exceptions._

                  val exception = errorException("un authorized access", "Unauthorized", UNAUTHORIZED)
                  val unAuthorizedAccess = Json.toJson(exception)
                  Future.successful(Unauthorized(unAuthorizedAccess))
                }

          }(ec)


      } else {
        //todo: sometimes this may not be debatable based on path..
        Future.successful(Unauthorized("Unauthorized"))
      }

    }



  }
}