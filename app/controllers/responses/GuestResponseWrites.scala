package controllers.responses


import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{JsPath, Writes}


object GuestResponseWrites {
  implicit val guestWrites:Writes[GuestResponse]=(
    (JsPath \ "id").write[Long] and
      (JsPath \ "first_name").write[String] and
      (JsPath \ "last_name").write[String]
    )(unlift(GuestResponse.unapply))


}
