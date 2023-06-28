package models.entities

import slick.jdbc.PostgresProfile.api._
import slick.sql.SqlProfile.ColumnOption.SqlType

import java.sql.Timestamp
import slick.jdbc.PostgresProfile.api._
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery


case class VisitationEntity(id:Long, hostId:Option[Long], guestId:Option[Long], officeId:Option[Long], departmentId:Option[Long], timeIn:Option[Timestamp], timeOut:Option[Timestamp], status:String, timezone:Option[String], created_at:Option[Timestamp], updated_at:Option[Timestamp])

  class VisitationTable(tag: Tag) extends Table[VisitationEntity](tag,"visitations") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def hostId = column[Option[Long]]("host_id")

    def guestId = column[Option[Long]]("guest_id")
    def officeId = column[Option[Long]]("office_id")

    def departmentId = column[Option[Long]]("department_id")


    def timeIn = column[Option[Timestamp]]("time_in")

    def timeOut = column[Option[Timestamp]]("time_out")

    def status = column[String]("status",O.Default("PENDING"))

    def timezone = column[Option[String]]("timezone")

    def created_at = column[Option[Timestamp]]("created_at", SqlType("timestamp not null default CURRENT_TIMESTAMP "))

    def updated_at = column[Option[Timestamp]]("updated_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

    //  override  def * = (id,hostId,guestId,timeIn,timeOut,status,timezone,createdAt,updatedAt) <> (Visitation.tupled,Visitation.unapply)
    override def * = (id, hostId, guestId,officeId,departmentId, timeIn, timeOut, status, timezone, created_at, updated_at) .mapTo[VisitationEntity]
    //<> (Visitation.tupled, Visitation.unapply)sz

    //  Visitation(id:Long,hostId:Option[Long], guestId:Option[Long], timeIn:Option[Timestamp], timeOut:Option[Timestamp], status:String, timezone:String,created_at:Timestamp,updated_at:Timestamp)
  }