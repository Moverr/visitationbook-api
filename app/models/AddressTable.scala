package models

import slick.lifted.Tag
import slick.ast.ScalaBaseType.{longType, stringType}
import slick.jdbc.H2Profile.Table
import slick.jdbc.PostgresProfile.api._
import slick.lifted.{ProvenShape, Tag}
import slick.sql.SqlProfile.ColumnOption.SqlType

import java.sql.Timestamp


class AddressTable(tag: Tag) extends Table[Address](tag, "addresses") {

  override def * : ProvenShape[Address] = (
    id
    , street
    , city
    , state
    , postalCode
    , country
    , latitude
    , longitude
    , createdAt
    , updatedAt
    , createdBy
    , updatedBy
    , status
  ).mapTo[Address]


  def id = column[Long]("id", O.AutoInc, O.PrimaryKey)

  def street = column[String]("street")

  def city = column[String]("city")

  def state = column[String]("state")

  def postalCode = column[String]("postal_code")

  def country = column[String]("country")

  def latitude = column[Long]("latitude")

  def longitude = column[Long]("longitude")

  def createdAt = column[Timestamp]("created_at", SqlType("timestamp not null default CURRENT_TIMESTAMP "))

  def createdBy = column[Option[Long]]("created_by")

  def updatedAt = column[Option[Timestamp]]("updated_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

  def updatedBy = column[Option[Long]]("updated_by")

  def status = column[Option[String]]("status")


}

