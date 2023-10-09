package controllers.responses


import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{JsPath, Writes}
import java.sql.Timestamp


object GuestResponseWrites {
  implicit val guestWrites:Writes[GuestResponse]=(
    (JsPath \ "id").write[Option[Long]] and
      (JsPath \ "first_name").write[Option[String]] and
      (JsPath \ "last_name").write[Option[String]]

    )(unlift(GuestResponse.unapply))


}
