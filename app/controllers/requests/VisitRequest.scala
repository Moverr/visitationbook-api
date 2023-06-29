package controllers.requests

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Reads}


case class VisitRequest(
                              hostId: Option[Long],
                              guestId: Option[Long],
                              officeId: Option[Long],
                              departmentId: Option[Long],
                              timeIn: String,
                              timeOut: String,
                              invitationType:Option[String]
                            )

object VisitRequestReads {

  implicit val visitRequestReads: Reads[VisitRequest] = (
    (JsPath \ "host_id").readNullable[Long] and
      (JsPath \ "guest_id").readNullable[Long] and
      (JsPath \ "office_id").readNullable[Long] and
      (JsPath \ "department_id").readNullable[Long] and
      (JsPath \ "time_in").read[String] and
      (JsPath \ "time_out").read[String] and
      (JsPath \ "inv_type").readNullable[String]

    ) (VisitRequest.apply _)

}


