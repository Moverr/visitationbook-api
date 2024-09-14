package models

import models.entities.ProfileEntity
import slick.ast.ScalaBaseType.{longType, stringType}
import slick.jdbc.H2Profile.Table
import slick.lifted.{ProvenShape, Tag}
import slick.sql.SqlProfile.ColumnOption.SqlType

import java.sql.Timestamp


import slick.jdbc.PostgresProfile.api._



class ProfileTable(tag: Tag) extends Table[Profile](tag,"profiles"){

  override def * : ProvenShape[Profile] = (
    id
    , firstName
    , middleName
    , lastName
    , gender
    , createdAt
    , updatedAt
    , createdBy
    , updatedBy
    , status
  ).mapTo[Profile]


  def id = column[Long]("id", O.AutoInc, O.PrimaryKey)


  def firstName = column[Option[String]]("firstname")

  def middleName = column[Option[String]]("middleName")

  def lastName = column[Option[String]]("lastname")

  def gender = column[Option[String]]("gender")

  def profileType = column[Option[String]]("profile_type")

  def createdAt = column[Timestamp]("created_at", SqlType("timestamp not null default CURRENT_TIMESTAMP "))

  def createdBy = column[Option[Long]]("created_by")

  def updatedAt = column[Option[Timestamp]]("updated_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

  def updatedBy = column[Option[Long]]("updated_by")

  def status = column[Option[String]]("status")

}


object ProfileTable {
  val profiles = TableQuery[ProfileTable]
}


