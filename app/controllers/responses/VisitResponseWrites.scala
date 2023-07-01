package controllers.responses


import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{JsPath, Writes}
import java.sql.Timestamp

object VisitResponseWrites {

  implicit val VisitationResponseWrites: Writes[VisitResponse] = (
    (JsPath \ "id").write[Long] and
      (JsPath \ "host_id").write[Option[Long]] and
      (JsPath \ "guest_id").write[Option[Long]] and
      (JsPath \ "time_in").write[Option[String]] and
      (JsPath \ "time_out").write[Option[String]] and
      (JsPath \ "status").write[String] and
      (JsPath \ "timezone").write[Option[String]] and
      (JsPath \ "created_at").write[Option[Timestamp]] and
      (JsPath \ "updated_at").write[Option[Timestamp]]
    ) (unlift(VisitationResponse.unapply))



}
