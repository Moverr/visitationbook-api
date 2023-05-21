package controllers.requests

case class VisitationRequest(hostId:Long, guestId:Option[Long],  officeId:Option[Long],  departmentId:Option[Long],   timeIn:String, timeOut:String, status:String, timezone:String)

