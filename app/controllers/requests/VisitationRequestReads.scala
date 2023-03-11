package controllers.requests

import controllers.requests.DateTimeReads.readDateTime
import org.joda.time.DateTime
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Reads}

object VisitationRequestReads {

  implicit val visitationRequestReads: Reads[VisitationRequest] = (
    (JsPath \ "hostId").read[Long] and
      (JsPath \ "guestId").read[Long] and
      (JsPath \ "timestamp").read[String] and
      (JsPath \ "timeOut").read[String] and
      (JsPath \ "status").read[String] and
      (JsPath \ "timezone").read[String]
    ) (VisitationRequest.apply _)

}





