package models

import java.sql.Timestamp

case class Address(
                    id: Long,
                    street: String,
                    city: String,
                    state: String,
                    postalCode: String,
                    country: String,
                    latitude: Option[Double], // Latitude as a Double
                    longitude: Option[Double] // Longitude as a Double


                    //important for time stamp
                    , createdAt: Timestamp
                    , updatedAt: Option[Timestamp]
                    , createdBy: Option[Long]
                    , updatedBy: Option[Long]
                    , status: Option[String]
                  )
