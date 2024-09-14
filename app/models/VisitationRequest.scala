package models

import java.sql.Timestamp

case class VisitationRequest(
                              id: Long,
                              hostId: Long,
                              guestId: Long,
                              visitDate: Timestamp,
                              requestDate: Timestamp
                            //time stamp etc
                              , createdAt: Timestamp
                              , updatedAt: Option[Timestamp]
                              , createdBy: Option[Long]
                              , updatedBy: Option[Long]
                              , status: Option[String]

                            )
