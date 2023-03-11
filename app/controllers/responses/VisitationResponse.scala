package controllers.responses

import org.joda.time.DateTime

import java.sql.Timestamp
case class VisitationResponse(id:Long,hostId:Option[Long], guestId:Option[Long], timeIn:Option[Timestamp], timeOut:Option[Timestamp], status:String, timezone:String,created_at:Timestamp,updated_at:Timestamp)


