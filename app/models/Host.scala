package models

import java.sql.Timestamp

case class Host(
                 id: Long
                 , profile: Long
                 , createdAt: Timestamp
                 , updatedAt: Option[Timestamp]
                 , createdBy: Option[Long]
                 , updatedBy: Option[Long]
                 , status: Option[String]
               )
