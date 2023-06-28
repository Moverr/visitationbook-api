package controllers.requests


import models.VisitationStatus.VisitationStatus
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{JsPath, Reads}

import  VisitationStatusReads.readVisitationStatus

object VisitationRequestReads {

  implicit val visitationRequestReads: Reads[VisitationsRequest] = (
    (JsPath \ "host_id").readNullable[Long] and
      (JsPath \ "guest_id").readNullable[Long] and
      (JsPath \ "office_id").readNullable[Long] and
      (JsPath \ "department_id").readNullable[Long] and
      (JsPath \ "time_in").read[String] and
      (JsPath \ "time_out").read[String] and
      (JsPath \ "status").read[String] and
      (JsPath \ "timezone").readNullable[String]
    ) (VisitationsRequest.apply _)

}





