package controllers.requests

import models._
import play.api.libs.json.{JsResult, JsSuccess, JsValue, Reads}

object VisitationStatusReads {
  implicit val readVisitationStatus: Reads[VisitationStatus.Value] = new Reads[VisitationStatus.Value]{
    override def reads(json: JsValue): JsResult[VisitationStatus.Value] =  JsSuccess(VisitationStatus.withName(json.as[String]))
  }
}
