package controllers.responses

import controllers.responses.OfficeResponseWrites._
import controllers.responses.ProfileResponseWrites._
import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.{JsPath, Writes}

import java.sql.Timestamp



case class RequestVisitResponse(
                                 id: Long,
                                 host: Option[ProfileResponse],
                                 guest: Option[ProfileResponse],
                                 office: Option[OfficeResponse],
                                 startDate: Option[String],
                                 endDate: Option[String],
                                 status: String,
                                 invType: String, 
                                 timezone: Option[String],
                                 createdAt: Option[Timestamp],
                                 updatedAt: Option[Timestamp]
                               )




object RequestVisitResponse {
  implicit val requestVisitResponseWrites:Writes[RequestVisitResponse]=(
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
    )(unlift(RequestVisitResponse.unapply))

}

