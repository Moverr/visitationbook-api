package controllers.requests

import VisitationStatusReads._
import models.VisitationStatus.VisitationStatus
case class VisitationRequest(
                              hostId:Option[Long],
                              guestId:Option[Long],
                              officeId:Option[Long],
                              departmentId:Option[Long],
                             timeIn:String, 
                              timeOut:String,
                             status:String
                             , timezone:String)

