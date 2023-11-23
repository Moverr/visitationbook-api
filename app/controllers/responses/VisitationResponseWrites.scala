package controllers.responses

import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{JsPath, Writes}

import java.sql.Timestamp
import ProfileResponseWrites._
import  OfficeResponseWrites._

object VisitationResponseWrites {

  implicit val VisitationResponseWrites: Writes[VisitationResponse] =     (
      (JsPath \ "id").write[Long] and
        (JsPath \ "host").write[Option[ProfileResponse]] and
        (JsPath \ "guest").write[Option[ProfileResponse]] and
        (JsPath \ "office").write[Option[OfficeResponse]] and
        (JsPath \ "start_date").write[Option[String]] and
        (JsPath \ "end_date").write[Option[String]] and
        (JsPath \ "status").write[String] and
        (JsPath \ "inv_type").write[String] and
        (JsPath \ "timezone").write[Option[String]] and
        (JsPath \ "created_at").write[Option[Timestamp]] and
        (JsPath \ "updated_at").write[Option[Timestamp]]
      ) (unlift(VisitationResponse.unapply))


}