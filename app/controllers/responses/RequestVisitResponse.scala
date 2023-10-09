package controllers.responses

import java.sql.Timestamp

case class RequestVisitResponse(
                                 id: Long,
                                 host: Option[HostReponse],
                                 guest: Option[GuestResponse],
                                 timeIn: Option[String],
                                 timeOut: Option[String],
                                 status: String,
                                 timezone: Option[String],
                                 createdAt: Option[Timestamp],
                                 updatedAt: Option[Timestamp]
                               )



