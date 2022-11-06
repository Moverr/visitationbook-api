package controllers.requests

import org.joda.time.DateTime
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Reads}
import CustomTimeStamps._
import play.api.libs.json.Format.GenericFormat

object VisitationRequestReads {


  implicit val authorResponsewrites: Reads[VisitationRequest] = (
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
