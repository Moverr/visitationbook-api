package controllers.responses


import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{JsPath, Writes}
import java.sql.Timestamp

object VisitResponseWrites {

  implicit val VisitationResponseWrites: Writes[VisitResponse] = (
    (JsPath \ "id").write[Long] and
      (JsPath \ "host").write[Option[Long]] and
      (JsPath \ "guest").write[Option[Long]] and
      (JsPath \ "office").write[Option[Long]] and
      (JsPath \ "department").write[Option[Long]] and
      (JsPath \ "time_in").write[Option[String]] and
      (JsPath \ "time_out").write[Option[String]] and
      (JsPath \ "invitation_type").write[Option[String]]

    ) (unlift(VisitResponse.unapply))



}
