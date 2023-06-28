package controllers.responses

import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{JsPath, Writes}

import java.sql.Timestamp

object VisitationResponseWrites {

  implicit val VisitationResponseWrites: Writes[VisitationResponse] =     (
      (JsPath \ "id").write[Long] and
        (JsPath \ "hostId").write[Option[Long]] and
        (JsPath \ "guestId").write[Option[Long]] and
        (JsPath \ "timeIn").write[Option[String]] and
        (JsPath \ "timeOut").write[Option[String]] and
        (JsPath \ "status").write[String] and
        (JsPath \ "timezone").write[Option[String]] and
        (JsPath \ "created_at").write[Option[Timestamp]] and
        (JsPath \ "updated_at").write[Option[Timestamp]]
      ) (unlift(VisitationResponse.unapply))


}