package models

import java.sql.Timestamp

case class Profile(
                    id: Long
                    , userId: Option[Long]
                    , firstName: String
                    , middleName: Option[String]
                    , lastName: String
                    , gender: String
                    , createdAt: Timestamp
                    , updatedAt: Option[Timestamp]
                    , createdBy: Option[Long]
                    , updatedBy: Option[Long]
                    , status: String

                  )
