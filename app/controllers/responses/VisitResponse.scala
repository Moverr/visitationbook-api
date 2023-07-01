package controllers.responses

import java.sql.Timestamp

case class VisitResponse(
                          id:Long,
                          hostId: Option[Long],
                          guestId: Option[Long],
                          officeId: Option[Long],
                          departmentId: Option[Long],
                          timeIn: Option[String],
                          timeOut: Option[String],
                          invitationType: Option[String]
                        )


