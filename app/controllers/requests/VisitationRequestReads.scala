package controllers.requests

import controllers.requests.DateTimeReads.readDateTime
import org.joda.time.DateTime
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsError, JsPath, JsResult, JsString, JsSuccess, JsValue, Reads}

object VisitationRequestReads {


  implicit val visitationRequestReads: Reads[VisitationRequest] = (
    (
      (JsPath \ "hostId").read[Long] and
        (JsPath \ "guestId").read[Long] and
        (JsPath \ "timestamp").read[DateTime]and
        (JsPath \ "timeOut").read[DateTime]and
        (JsPath \ "status").read[String]and
        (JsPath \ "timezone").read[String]
      ) (VisitationRequest.apply _)
    )

}





