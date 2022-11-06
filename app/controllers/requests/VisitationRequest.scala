package controllers.requests

import org.joda.time.DateTime

case class VisitationRequest(hostId:Integer, guestId:Integer, timeIn:DateTime, timeOut:DateTime, status:String, timezone:String)

