package exceptions

import play.api.http.Status.{BAD_REQUEST, FORBIDDEN, INTERNAL_SERVER_ERROR, NOT_FOUND, UNAUTHORIZED}
import play.api.libs.json.{Json, Writes}

sealed trait ErrorException {
  val message: String
  val error: String
  val statusCode: Int
}

case class UnauthorizedException(message: String, error: String, statusCode: Int = UNAUTHORIZED) extends ErrorException

case class ForbiddenException(message: String, error: String, statusCode: Int = FORBIDDEN) extends ErrorException

case class NotFoundException(message: String, error: String, statusCode: Int = NOT_FOUND) extends ErrorException

case class InternalServerException(message: String, error: String, statusCode: Int = INTERNAL_SERVER_ERROR) extends ErrorException
case class BadRequestException(message: String, error: String, statusCode: Int = BAD_REQUEST) extends ErrorException


object ErrorException {
  def apply(msg: String, error: String, statusCode: Int): ErrorException = statusCode match {
    case UNAUTHORIZED => UnauthorizedException(msg, error, UNAUTHORIZED)
    case FORBIDDEN => ForbiddenException(msg, error, FORBIDDEN)
    case NOT_FOUND => NotFoundException(msg, error, NOT_FOUND)
    case INTERNAL_SERVER_ERROR => InternalServerException(msg, error, INTERNAL_SERVER_ERROR)
    case BAD_REQUEST => BadRequestException(msg, error, BAD_REQUEST)
    case _ => throw new IllegalArgumentException(s"Invalid status code: $statusCode")
  }
}

object ExceptionHandler {
  implicit val unauthorizedWrites: Writes[UnauthorizedException] = Json.writes[UnauthorizedException]
  implicit val forbiddenWrites: Writes[ForbiddenException] = Json.writes[ForbiddenException]
  implicit val notFoundWrites: Writes[NotFoundException] = Json.writes[NotFoundException]
  implicit val internalExceptionWrites: Writes[InternalServerException] = Json.writes[InternalServerException]
  implicit val badRequestExceptionWrites: Writes[BadRequestException] = Json.writes[BadRequestException]

  implicit val errorExceptionWrites: Writes[ErrorException] = {
    case unauthorized: UnauthorizedException => Json.toJson(unauthorized)
    case forbidden: ForbiddenException => Json.toJson(forbidden)
    case notFound: NotFoundException => Json.toJson(notFound)
    case internalException: InternalServerException => Json.toJson(internalException)
    case badRequestException: BadRequestException => Json.toJson(badRequestException)
  }
}
