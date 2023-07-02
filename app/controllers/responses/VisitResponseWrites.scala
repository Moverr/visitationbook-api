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
      (JsPath \ "office_id").write[Option[Long]] and
      (JsPath \ "departmemnt_id").write[Option[Long]] and
      (JsPath \ "time_in").write[Option[String]] and
      (JsPath \ "time_out").write[Option[String]] and
      (JsPath \ "invitation_type").write[Option[String]]

    ) (unlift(VisitResponse.unapply))



}
