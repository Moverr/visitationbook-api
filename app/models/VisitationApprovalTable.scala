package models

import slick.ast.ScalaBaseType.{longType, stringType}
import slick.jdbc.H2Profile.Table
import slick.jdbc.PostgresProfile.api._
import slick.lifted.{ProvenShape, Tag}
import slick.sql.SqlProfile.ColumnOption.SqlType

import java.sql.Timestamp


class VisitationApprovalTable(tag: Tag) extends Table[Address](tag, "visitation_approvals") {

  override def * : ProvenShape[VisitationApproval] = (
    id,
    requestId,
    approvalDate

    , createdAt
    , updatedAt
    , createdBy
    , updatedBy
    , status
  ).mapTo[VisitationApproval]


  def id = column[Long]("id", O.AutoInc, O.PrimaryKey)

  def requestId = column[Long]("request_id")

  def approvalDate = column[Timestamp]("approval_date")


  def createdAt = column[Timestamp]("created_at", SqlType("timestamp not null default CURRENT_TIMESTAMP "))

  def createdBy = column[Option[Long]]("created_by")

  def updatedAt = column[Option[Timestamp]]("updated_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

  def updatedBy = column[Option[Long]]("updated_by")

  def status = column[Option[String]]("status")


  //todo: to be complated
  def profile = foreignKey("fk_profile", requestId, ProfileTable.profiles)(_.id, onDelete = ForeignKeyAction.Cascade)


}
