package filters

import akka.stream.Materializer
import play.api.mvc.{Filter, RequestHeader, Result}

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class LoggingFilter @Inject()(implicit val mat: Materializer) extends Filter {
  @Override def apply(nextFilter: RequestHeader => Future[Result])(requestHeader: RequestHeader): Future[Result] = {
    val startTime = System.currentTimeMillis()

    nextFilter(requestHeader).map { result =>
      val endTime = System.currentTimeMillis()
      val requestTime = endTime - startTime

      println("This filter is working gracefully  ")
      println(s"${requestHeader.method} ${requestHeader.uri} took ${requestTime} ms and returned ${result.header.status}")

      result
    }
  }
}
