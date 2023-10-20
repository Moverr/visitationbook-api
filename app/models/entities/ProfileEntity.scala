package models.entities

import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import slick.sql.SqlProfile.ColumnOption.SqlType

import java.sql.Timestamp


case class ProfileEntity(
                          id: Long
                         , user_id: Option[Long]
                         , firstname: Option[String]
                         , othernames: Option[String]
                         , gender: Option[String]
                         , profile_type: Option[String]
                         , created_at: Timestamp
                         , updated_at: Option[Timestamp]
                         , created_by: Option[Long]
                         , updated_by: Option[Long]
                        )


class ProfileTable(tag: Tag) extends Table[ProfileEntity](tag, "profile") {

  override def * : ProvenShape[ProfileEntity] = (
    id
    , userId
    , firstName
    , othernames
    , gender
    , profileType
    , createdAt
    , updatedAt
    , createdBy
    , updatedBy
  ).mapTo[ProfileEntity]

  def id = column[Long]("id", O.AutoInc, O.PrimaryKey)

  def userId = column[Option[Long]]("user_id")

  def firstName = column[Option[String]]("firstname")

  def othernames = column[Option[String]]("othernames")

  def gender = column[Option[String]]("gender")

  def profileType = column[Option[String]]("profile_type")

  def createdAt = column[Timestamp]("created_at", SqlType("timestamp not null default CURRENT_TIMESTAMP "))

  def createdBy = column[Option[Long]]("created_by")

  def updatedAt = column[Option[Timestamp]]("updated_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

  def updatedBy = column[Option[Long]]("updated_by")
}