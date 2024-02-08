package controllers.requests

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json._


case class VisitationRequest(
                               hostId: Option[Long],
                               guestId: Option[Long],
                               officeId: Option[Long],
                               departmentId: Option[Long],
                               timeIn: String,
                               timeOut: String,
                               status: Option[String]
                               , timezone: Option[String]

                             )

object VisitationRequest {

  implicit val visitationRequestReads: Reads[VisitationRequest] = (
    (JsPath \ "host_id").readNullable[Long] and
      (JsPath \ "guest_id").readNullable[Long] and
      (JsPath \ "office_id").readNullable[Long] and
      (JsPath \ "department_id").readNullable[Long] and
      (JsPath \ "time_in").read[String] and
      (JsPath \ "time_out").read[String] and
      (JsPath \ "status").readNullable[String] and
      (JsPath \ "timezone").readNullable[String]
    )(VisitationRequest.apply _)

}

