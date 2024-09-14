package models

import java.sql.Timestamp

case class Contact(
                    id: Long
                    , email: Option[String]
                    , phone: Option[String]
                    , fax: Option[String]
                    //important for time stamp
                    , createdAt: Timestamp
                    , updatedAt: Option[Timestamp]
                    , createdBy: Option[Long]
                    , updatedBy: Option[Long]
                    , status: Option[String]

                  )
