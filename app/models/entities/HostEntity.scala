package models.entities

import java.sql.Timestamp

case class HostEntity(
                       id: Long
                       ,profile:ProfileTable
                       , createdAt: Timestamp
                       , updatedAt: Option[Timestamp]
                       , createdBy: Option[Long]
                       , updatedBy: Option[Long]
                       , status: Option[String]

                     )
