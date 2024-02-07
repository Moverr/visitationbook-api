package filters

import akka.stream.Materializer
import annotation.CLogger
import play.api.mvc.Results.Unauthorized
import play.api.mvc._

import javax.inject.Inject
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContext, Future}

@CLogger
class AuthenticationFilter @Inject()(userManager: UserManager, implicit val mat: Materializer) extends Filter {

  @Override def apply(nextFilter: RequestHeader => Future[Result])(requestHeader: RequestHeader): Future[Result] = {

    println("Testing out some info")
      val ec: ExecutionContext = ExecutionContext.global
    val dataMap: Map[String, Seq[String]] = requestHeader.headers.toMap
    val bearerInfo: Option[Seq[String]] = dataMap.get("Authorization")



    val token: Option[String] = bearerInfo flatMap (x => x.headOption.filter(_.nonEmpty))
    val res: String = token.getOrElse("NONE")
    println(s" token result $res ")

    if (!res.equalsIgnoreCase("none") ) {

     userManager.isAuthenticated(res)
       .flatMap{
         status =>
           if(status)
             nextFilter(requestHeader)
           else
             Future.successful(Unauthorized("Unauthorized"))
       }(ec)


    } else {
      Future.successful(Unauthorized("Unauthorized"))
    }

  }
}