package controllers.responses

import org.joda.time.DateTime
import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{JsPath, Writes}

import DateTimeWrites._

object VisistationWrites {

  val VisistationWrites: Writes[VisitationResponse] = (
    (
      (JsPath \ "id").write[Long] and
        (JsPath \ "hostId").write[Option[Long]] and
        (JsPath \ "guestId").write[Option[Long]] and
        (JsPath \ "timeIn").write[Option[DateTime]] and
        (JsPath \ "timeOut").write[Option[DateTime]] and
        (JsPath \ "status").write[String] and
        (JsPath \ "timezone").write[String] and
        (JsPath \ "created_at").write[DateTime] and
        (JsPath \ "updated_at").write[DateTime]
      ) (unlift(VisitationResponse.unapply))
    )

}