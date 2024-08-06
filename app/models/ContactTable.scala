package models

import slick.ast.ScalaBaseType.{longType, stringType}
import slick.jdbc.H2Profile.Table
import slick.jdbc.PostgresProfile.api._
import slick.lifted.{ProvenShape, Tag}
import slick.sql.SqlProfile.ColumnOption.SqlType

import java.sql.Timestamp


class ContactTable(tag: Tag) extends Table[Contact](tag, "contacts") {

  override def * : ProvenShape[Contact] = (
    id
    , email
    , phone
    , fax

    , createdAt
    , updatedAt
    , createdBy
    , updatedBy
    , status
  ).mapTo[Contact]


  def id = column[Long]("id", O.AutoInc, O.PrimaryKey)

  def email = column[String]("email")

  def phone = column[String]("phone")

  def fax = column[String]("fax")


  def createdAt = column[Timestamp]("created_at", SqlType("timestamp not null default CURRENT_TIMESTAMP "))

  def createdBy = column[Option[Long]]("created_by")

  def updatedAt = column[Option[Timestamp]]("updated_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

  def updatedBy = column[Option[Long]]("updated_by")

  def status = column[Option[String]]("status")

}
