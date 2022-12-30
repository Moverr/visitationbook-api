package models.entities

import java.sql.Timestamp

import slick.jdbc.PostgresProfile.api._
import slick.sql.SqlProfile.ColumnOption.SqlType
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

import java.util.UUID


import slick.jdbc.PostgresProfile.api._
import slick.sql.SqlProfile.ColumnOption.SqlType
//import defaults ::
import slick.lifted.TableQuery
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery


case class Visitation(id:Long,hostId:Option[Long], guestId:Option[Long], timeIn:Option[Timestamp], timeOut:Option[Timestamp], status:String, timezone:String,createdAt:Timestamp,updatedAt:Timestamp)

class VisitationTable(tag: Tag) extends Table[Visitation](tag,"visitations"){
  def id: column[Long]("id",O.PrimaryKey,O.AutoInc)
  def hostId: column[Option[Long]]("hostId")
  def guestId: column[Option[Long]]("guestId")
  def timeIn: column[Option[Timestamp]]("timeIn")
  def timeOut: column[Option[Timestamp]]("timeOut")
  def status: column[String]("status")
  def timezone: column[String]("timezone")
  def createdAt: column[Timestamp]("created_at")
  def updatedAt: column[Timestamp]("updated_at")

  override  def * = (id,hostId,guestId,timeIn,timeOut,status,timezone,createdAt,updatedAt) <> (Visitation.tupled,Visitation.unapply)
}