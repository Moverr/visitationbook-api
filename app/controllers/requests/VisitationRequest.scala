package controllers.requests

import akka.http.javadsl.model.DateTime

case class VisitationRequest(hostId:Long, guestId:Long,  timeIn:String, timeOut:String, status:String, timezone:String)

