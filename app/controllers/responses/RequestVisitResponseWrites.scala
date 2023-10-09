package controllers.responses

import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.{JsPath, Writes}

import java.sql.Timestamp

import HostResponseWrites._
import  GuestResponseWrites._

object RequestVisitResponseWrites {
  implicit val requestVisitResponseWrites:Writes[RequestVisitResponse]=(
    (JsPath \ "id").write[Long] and
      (JsPath \ "hostId").write[Option[HostReponse]] and
      (JsPath \ "guestId").write[Option[GuestResponse]] and
      (JsPath \ "timeIn").write[Option[String]] and
      (JsPath \ "timeOut").write[Option[String]] and
      (JsPath \ "status").write[String] and
      (JsPath \ "timezone").write[Option[String]] and
      (JsPath \ "created_at").write[Option[Timestamp]] and
      (JsPath \ "updated_at").write[Option[Timestamp]]
  )(unlift(RequestVisitResponse.unapply))

}
