package models



import slick.ast.ScalaBaseType.{longType, stringType}
import slick.jdbc.H2Profile.Table
import slick.jdbc.PostgresProfile.api._
import slick.lifted.{ProvenShape, Tag}
import slick.sql.SqlProfile.ColumnOption.SqlType

import java.sql.Timestamp



class HostTable(tag: Tag) extends Table[Host](tag,"hosts"){

  override def * : ProvenShape[Host] = (
        id.?
        , profileId
        , createdAt
        , updatedAt
        , createdBy
        , updatedBy
        , status
  ).mapTo[Host]



  def id = column[Long]("id", O.AutoInc, O.PrimaryKey)

  def profileId = column[Long]("profile_id")

  def createdAt = column[Timestamp]("created_at", SqlType("timestamp not null default CURRENT_TIMESTAMP "))

  def createdBy = column[Option[Long]]("created_by")

  def updatedAt = column[Option[Timestamp]]("updated_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

  def updatedBy = column[Option[Long]]("updated_by")

  def status = column[Option[String]]("status")


  def profile = foreignKey("fk_profile", profileId, ProfileTable.profiles)(_.id, onDelete = ForeignKeyAction.Cascade)



}
object HostTable {
  val host = TableQuery[HostTable]
}
