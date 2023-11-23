package controllers.responses

import org.joda.time.DateTime

import java.sql.Timestamp
case class VisitationResponse(
                               id:Long,
                               host: Option[ProfileResponse],
                               guest: Option[ProfileResponse],
                               office: Option[OfficeResponse],
                               timeIn:Option[String],
                               timeOut:Option[String],
                               status:String,
                               invType:String,
                               timezone:Option[String],
                               createdAt:Option[Timestamp],
                               updatedAt:Option[Timestamp]
                             )


