package controllers.requests

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.OFormat.oFormatFromReadsAndOWrites
import play.api.libs.json.{JsPath, Reads}

import OptionReads.intOptin
import java.util.UUID

object VisitationRequestReads {

  implicit val visitationRequestReads: Reads[VisitationRequest] = (
    (JsPath \ "host_id").read[Long] and
      (JsPath \ "guest_id").read[Option[Long]] and
      (JsPath \ "guest_id").read[Option[Long]] and
      (JsPath \ "guest_id").read[Option[Long]] and
      (JsPath \ "time_in").read[String] and
      (JsPath \ "time_out").read[String] and
      (JsPath \ "status").read[String] and
      (JsPath \ "timezone").read[String]
    ) (VisitationRequest.apply _)

}





