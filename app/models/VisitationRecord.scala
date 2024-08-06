package models

import java.sql.Timestamp
import java.util.UUID

case class VisitationRecord(
                             id: Option[Long],
                             requestId: Long,
                             approvalId: Long,
                           //Generate a unique UUID
                             clientUniqueId: UUID, // Unique ID for each client per visitation
                             timeIn: Timestamp,
                             timeOut: Option[Timestamp],

                             visitType: String, // "online" or "physical"
                             meetUpLocation: Option[Address], // applicable for physical visits
                             clientOrigin: Option[Address] // where the client is coming from in case of physical
                             , clientContact: Option[Contact]

                           )
