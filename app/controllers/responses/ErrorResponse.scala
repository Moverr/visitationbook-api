package controllers.responses

import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{JsPath, Writes}


sealed trait Error

case class ErrorResponse(code: Int, message: String) extends Error

object ErrorResponseWrites {
  implicit val ErrorResponseWrites: Writes[ErrorResponse] =
    ( (JsPath \ "code").write[Int] and (JsPath \ "message").write[String] )(unlift(ErrorResponse.unapply))
}