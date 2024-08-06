package models

import slick.ast.ScalaBaseType.{longType, stringType}
import slick.jdbc.H2Profile.Table
import slick.jdbc.PostgresProfile.api._
import slick.lifted.{ProvenShape, Tag}
import slick.sql.SqlProfile.ColumnOption.SqlType

import java.sql.Timestamp


class VisitationRequestTable(tag: Tag) extends Table[VisitationRequest](tag, "visitation_requests") {

  override def * : ProvenShape[VisitationRequest] = (
    id,
    hostId,
    guestId,
    visitDate,
    requestDate

    , createdAt
    , updatedAt
    , createdBy
    , updatedBy
    , status
  ).mapTo[VisitationRequest]


  def id = column[Long]("id", O.AutoInc, O.PrimaryKey)

  def hostId = column[Long]("host_id")

  def guestId = column[Long]("guest_id")

  def visitDate = column[Timestamp]("visit_date")

  def requestDate = column[Timestamp]("request_date")


  def createdAt = column[Timestamp]("created_at", SqlType("timestamp not null default CURRENT_TIMESTAMP "))

  def createdBy = column[Option[Long]]("created_by")

  def updatedAt = column[Option[Timestamp]]("updated_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

  def updatedBy = column[Option[Long]]("updated_by")

  def status = column[Option[String]]("status")


  def host = foreignKey("fk_profile1", hostId, HostTable.host)(_.id, onDelete = ForeignKeyAction.NoAction)


  def guest = foreignKey("fk_profile2", hostId, GuestTable.guest)(_.id, onDelete = ForeignKeyAction.NoAction)


}
