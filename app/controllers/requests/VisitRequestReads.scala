package controllers.requests

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Reads}

object VisitRequestReads {

  implicit val visitRequestReads: Reads[VisitRequest] = (
    (JsPath \ "host_id").read[Long] and
      (JsPath \ "guest_id").read[Long] and
      (JsPath \ "office_id").readNullable[Long] and
      (JsPath \ "department_id").readNullable[Long] and
      (JsPath \ "time_in").readNullable[String] and
      (JsPath \ "time_out").readNullable[String] and
      (JsPath \ "inv_type").readNullable[String]

    ) (VisitRequest.apply _)

}

