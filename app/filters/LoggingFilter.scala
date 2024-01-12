package filters

import akka.stream.Materializer
import play.api.mvc._

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class LoggingFilter @Inject()(implicit val mat: Materializer) extends Filter {
  override def apply(next: RequestHeader => Future[Result])(request: RequestHeader): Future[Result] = {
    val startTime = System.currentTimeMillis()

    next(request).map { result =>
      val endTime = System.currentTimeMillis()
      val requestTime = endTime - startTime

      println(s"Request ${request.method} ${request.uri} took ${requestTime}ms")
      result
    }
  }
}