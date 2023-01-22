package controllers.responses

import org.joda.time.DateTime

import java.sql.Timestamp
case class VisitationResponse(id:Long,hostId:Option[Long], guestId:Option[Long], timeIn:Option[DateTime], timeOut:Option[DateTime], status:String, timezone:String,created_at:DateTime,updated_at:DateTime)


