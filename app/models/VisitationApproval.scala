package models

import java.sql.Timestamp

case class VisitationApproval(
                               id: Long,
                               requestId: Long,
                               approvalDate: Timestamp

                               //time stamp etc
                               , createdAt: Timestamp
                               , updatedAt: Option[Timestamp]
                               , createdBy: Option[Long]
                               , updatedBy: Option[Long]
                               , status: Option[String]

                             )
