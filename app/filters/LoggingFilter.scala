package filters

import javax.inject.Inject
import akka.stream.Materializer
import play.api.mvc._

import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


  class LoggingFilter @Inject() (implicit val mat: Materializer) extends Filter {
  override def apply(nextFilter: RequestHeader => Future[Result])
                    (requestHeader: RequestHeader): Future[Result] = {
    val startTime = System.currentTimeMillis()


    nextFilter(requestHeader).map { result =>
      val endTime = System.currentTimeMillis()
      val requestTime = endTime - startTime

      // Log request information
      val msg = s"${requestHeader.method} ${requestHeader.uri} took ${requestTime}ms"
      play.Logger.info(msg)

      result.withHeaders("Request-Time" -> requestTime.toString)
    }
  }
}
