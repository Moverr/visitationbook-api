package controllers.requests

import org.joda.time.DateTime

case class VisitationRequest(hostId:Long, guestId:Long, timeIn:DateTime, timeOut:DateTime, status:String, timezone:String)

