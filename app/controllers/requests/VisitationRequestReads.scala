package controllers.requests


import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{JsPath, Reads}

object VisitationRequestReads {

  implicit val visitationRequestReads: Reads[VistationRequest] = (
    (JsPath \ "host_id").readNullable[Long] and
      (JsPath \ "guest_id").readNullable[Long] and
      (JsPath \ "office_id").readNullable[Long] and
      (JsPath \ "department_id").readNullable[Long] and
      (JsPath \ "time_in").read[String] and
      (JsPath \ "time_out").read[String] and
      (JsPath \ "status").read[String] and
      (JsPath \ "timezone").readNullable[String]
    ) (VistationRequest.apply _)

}





