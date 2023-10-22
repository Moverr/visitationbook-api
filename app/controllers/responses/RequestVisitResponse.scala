package controllers.responses

import java.sql.Timestamp

case class RequestVisitResponse(
                                 id: Long,
                                 host: Option[ProfileResponse],
                                 guest: Option[ProfileResponse],
                                 office: Option[OfficeResponse],
                                 startDate: Option[String],
                                 endDate: Option[String],
                                 status: String,
                                 invType: String, 
                                 timezone: Option[String],
                                 createdAt: Option[Timestamp],
                                 updatedAt: Option[Timestamp]
                               )



