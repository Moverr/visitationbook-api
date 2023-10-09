package controllers.responses

import org.joda.time.DateTime

import java.sql.Timestamp
case class VisitationResponse(
                               id:Long,
                               host:Option[Long],
                               guest:Option[Long],
                               timeIn:Option[String],
                               timeOut:Option[String],
                               status:String,
                               timezone:Option[String],
                               createdAt:Option[Timestamp],
                               updatedAt:Option[Timestamp]
                             )


