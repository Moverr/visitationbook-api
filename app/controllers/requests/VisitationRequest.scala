package controllers.requests

case class VisitationRequest(hostId:Long, guestId:Long,  timeIn:String, timeOut:String, status:String, timezone:String)

