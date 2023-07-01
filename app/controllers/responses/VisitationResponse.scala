package controllers.responses

import org.joda.time.DateTime

import java.sql.Timestamp
case class VisitationResponse(
                               id:Long,
                               hostId:Option[Long],
                               guestId:Option[Long],
                               timeIn:Option[String],
                               timeOut:Option[String],
                               status:String,
                               timezone:Option[String],
                               createdAt:Option[Timestamp],
                               updatedAt:Option[Timestamp]
                             )


