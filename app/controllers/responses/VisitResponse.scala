package controllers.responses

import java.sql.Timestamp

case class VisitResponse(
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


