package filters

import akka.stream.Materializer
import play.api.mvc.Results.Unauthorized

import javax.inject.Inject
import play.api.mvc._
import play.libs.Json

import scala.concurrent.Future
class AuthenticationFilter @Inject()(userManager: UserManager, implicit val mat: Materializer) extends Filter
{
 @Override def apply(nextFilter: RequestHeader => Future[Result])(requestHeader: RequestHeader): Future[Result] = {

   println(requestHeader.headers)

   val dataMap: Map[String, Seq[String]] = requestHeader.headers.toMap
   //{ case (k, v) => k -> Json.fromString(v.toString) }.toMap

   println(dataMap)

    if (userManager.isAuthenticated(requestHeader)) {
      nextFilter(requestHeader)
    } else {
      Future.successful(Unauthorized("Unauthorized"))
    }
  }
}