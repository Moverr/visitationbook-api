package models.entities

import slick.jdbc.PostgresProfile.api._
import slick.sql.SqlProfile.ColumnOption.SqlType

import java.sql.Timestamp
import slick.jdbc.PostgresProfile.api._
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery


case class Visitation(id:Long,hostId:Option[Long], guestId:Option[Long],timeIn:Option[Timestamp], timeOut:Option[Timestamp], status:String, timezone:String,created_at:Timestamp,updated_at:Timestamp)

  class VisitationTable(tag: Tag) extends Table[Visitation](tag,"visitations") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def hostId = column[Option[Long]]("hostId")

    def guestId = column[Option[Long]]("guestId")

    def timeIn = column[Option[Timestamp]]("timeIn")

    def timeOut = column[Option[Timestamp]]("timeOut")

    def status = column[String]("status")

    def timezone = column[String]("timezone")

    def created_at = column[Timestamp]("created_at", SqlType("timestamp not null default CURRENT_TIMESTAMP "))

    def updated_at = column[Timestamp]("updated_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

    //  override  def * = (id,hostId,guestId,timeIn,timeOut,status,timezone,createdAt,updatedAt) <> (Visitation.tupled,Visitation.unapply)
    override def * = (id, hostId, guestId, timeIn, timeOut, status, timezone, created_at, updated_at) .mapTo[Visitation]
    //<> (Visitation.tupled, Visitation.unapply)sz

    //  Visitation(id:Long,hostId:Option[Long], guestId:Option[Long], timeIn:Option[Timestamp], timeOut:Option[Timestamp], status:String, timezone:String,created_at:Timestamp,updated_at:Timestamp)
  }