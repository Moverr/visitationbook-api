package controllers.responses

import org.joda.time.DateTime

import java.sql.Timestamp
case class VisitationResponse(id:Long, host_id:Option[Long], guest_id:Option[Long], time_in:Option[String], time_out:Option[String], status:String, timezone:String, created_at:Timestamp, updated_at:Timestamp)


