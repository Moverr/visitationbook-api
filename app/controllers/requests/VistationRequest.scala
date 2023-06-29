package controllers.requests

import VisitationStatusReads._
import models.VisitationStatus.VisitationStatus

import play.api.libs.json._


case class VistationRequest(
                               hostId: Option[Long],
                               guestId: Option[Long],
                               officeId: Option[Long],
                               departmentId: Option[Long],
                               timeIn: String,
                               timeOut: String,
                               status: String
                               , timezone: Option[String]

                             )

 