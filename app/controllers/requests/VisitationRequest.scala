package controllers.requests

import VisitationStatusReads._
import models.VisitationStatus.VisitationStatus
case class VisitationRequest(
                              hostId:Long,
                              guestId:Option[Long],
                              officeId:Option[Long],
                              departmentId:Option[Long],
                             timeIn:String, timeOut:String,
                             status:VisitationStatus
                             , timezone:String)

