package controllers.requests

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Reads}

/*
 @moverr@gmail.com
 */

case class VisitRequest(
                              hostId: Long,
                              guestId: Long,
                              officeId: Option[Long],
                              departmentId: Option[Long],
                              timeIn:Long,
                              timeOut: Long,
                              invitationType:Option[String]
                       )


object VisitRequest {

  implicit val visitRequestReads: Reads[VisitRequest] = (
    (JsPath \ "host_id").read[Long] and
      (JsPath \ "guest_id").read[Long] and
      (JsPath \ "office_id").readNullable[Long] and
      (JsPath \ "department_id").readNullable[Long] and
      (JsPath \ "time_in").read[Long] and //epoch standard
      (JsPath \ "time_out").read[Long] and //epoch standard
      (JsPath \ "inv_type").readNullable[String]

    ) (VisitRequest.apply _)

}