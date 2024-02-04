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

   println("Testing out some info")

   val dataMap: Map[String, Seq[String]] = requestHeader.headers.toMap
   val bearerInfo:Option[Seq[String]] = dataMap.get("Authorization")

   println("Dealing in the nature and also .....")

   val token:Option[String] = for {
     values <- bearerInfo
     value <- values.headOption

   }yield {
     println("Indeed interesting")
     println(value)
     value
   }

   val res:String = for {
     result <- token.get
   }yield result

   println("Result nature ......")

   //{ case (k, v) => k -> Json.fromString(v.toString) }.toMap
   println(res)
   println(dataMap)

    if (userManager.isAuthenticated(requestHeader)) {
      nextFilter(requestHeader)
    } else {
      Future.successful(Unauthorized("Unauthorized"))
    }
  }
}