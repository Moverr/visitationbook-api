package controllers.requests

import models._
import play.api.libs.json.Reads

object VisitationStatusReads {
  implicit val readVisitationStatus: Reads[VisitationStatus.Value] = Reads.enumNameReads(VisitationStatus)


}
//implicit val genderReads = Reads.enumNameReads(VisitationStatus)
