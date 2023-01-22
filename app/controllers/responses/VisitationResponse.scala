package controllers.responses

import org.joda.time.DateTime
case class VisitationResponse(id:Long,hosstId:Option[Long], guestId:Option[Long], timeIn:Option[DateTime], timeOut:Option[DateTime], status:String, timezone:String,created_at:DateTime,updated_at:DateTime)


