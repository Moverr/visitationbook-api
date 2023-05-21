package controllers.requests

import controllers.requests.VisitationStatusReads._
import models.VisitationStatus.VisitationStatus
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{JsPath, Reads}
import VisitationStatusReads._

object VisitationRequestReads {

  implicit val visitationRequestReads: Reads[VisitationRequest] = (
    (JsPath \ "host_id").read[Long] and
      (JsPath \ "guest_id").readNullable[Long] and
      (JsPath \ "office_id").readNullable[Long] and
      (JsPath \ "department_id").readNullable[Long] and
      (JsPath \ "time_in").read[String] and
      (JsPath \ "time_out").read[String] and
      (JsPath \ "status").read[VisitationStatus] and
      (JsPath \ "timezone").read[String]
    ) (VisitationRequest.apply _)

}





