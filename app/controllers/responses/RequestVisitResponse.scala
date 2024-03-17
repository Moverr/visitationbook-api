package controllers.responses

import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.{JsPath, Writes}

import java.sql.Timestamp


case class RequestVisitResponse(
                                 id: Long,
                                 host: Option[ProfileResponse],
                                 guest: Option[ProfileResponse],
                                 office: Option[OfficeResponse],
                                 timeInDate: Option[String],
                                 timeOutDate: Option[String],
                                 status: String,
                                 invType: String,
                                 timezone: Option[String],
                                 createdAt: Option[String],
                                 updatedAt: Option[String]
                               )


object RequestVisitResponse {
  implicit val requestVisitResponseWrites: Writes[RequestVisitResponse] = (
    (JsPath \ "id").write[Long] and
      (JsPath \ "host").write[Option[ProfileResponse]] and
      (JsPath \ "guest").write[Option[ProfileResponse]] and
      (JsPath \ "office").write[Option[OfficeResponse]] and
      (JsPath \ "time_in").write[Option[String]] and
      (JsPath \ "time_out").write[Option[String]] and
      (JsPath \ "status").write[String] and
      (JsPath \ "inv_type").write[String] and
      (JsPath \ "timezone").write[Option[String]] and
      (JsPath \ "created_at").write[Option[String]] and
      (JsPath \ "updated_at").write[Option[String]]
    )(unlift(RequestVisitResponse.unapply))

}

