package models

import java.sql.Timestamp

case class Guest(
                 id: Long
                 , profile: Profile
                 , createdAt: Timestamp
                 , updatedAt: Option[Timestamp]
                 , createdBy: Option[Long]
                 , updatedBy: Option[Long]
                 , status: Option[String]
               )
