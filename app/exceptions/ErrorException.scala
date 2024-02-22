package exceptions

import play.api.http.Status.{FORBIDDEN, NOT_FOUND, UNAUTHORIZED}
import play.api.libs.json.{Json, Writes}

sealed trait ErrorException {
  val message: String
  val error: String
  val statusCode: Int
}

case class UnauthorizedException(message: String, error: String, statusCode: Int = UNAUTHORIZED) extends ErrorException

case class ForbiddenException(message: String, error: String, statusCode: Int = FORBIDDEN) extends ErrorException

case class NotFoundException(message: String, error: String, statusCode: Int = NOT_FOUND) extends ErrorException

object ErrorException {
  def apply(msg: String, error: String, statusCode: Int): ErrorException = statusCode match {
    case UNAUTHORIZED => UnauthorizedException(msg, error, UNAUTHORIZED)
    case FORBIDDEN => ForbiddenException(msg, error, FORBIDDEN)
    case NOT_FOUND => NotFoundException(msg, error, NOT_FOUND)
    case _ => throw new IllegalArgumentException(s"Invalid status code: $statusCode")
  }
}

object ExceptionHandler {
  implicit val unauthorizedWrites: Writes[UnauthorizedException] = Json.writes[UnauthorizedException]
  implicit val forbiddenWrites: Writes[ForbiddenException] = Json.writes[ForbiddenException]
  implicit val notFoundWrites: Writes[NotFoundException] = Json.writes[NotFoundException]

  implicit val errorExceptionWrites: Writes[ErrorException] = {
    case unauthorized: UnauthorizedException => Json.toJson(unauthorized)
    case forbidden: ForbiddenException => Json.toJson(forbidden)
    case notFound: NotFoundException => Json.toJson(notFound)
  }
}
