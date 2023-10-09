package controllers.responses

import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.{JsPath, Writes}

import scala.Function.unlift

object OfficeResponseWrites {
  implicit  val officeResponse:Writes[OfficeResponse]=(
    (JsPath \ "id").write[Long] and
      (JsPath \ "first_name").write[String]
  )(unlift(OfficeResponse.unapply))

}
