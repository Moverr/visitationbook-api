package models.entities

import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import slick.sql.SqlProfile.ColumnOption.SqlType

import java.sql.Timestamp


case class VisitationEntity(id:Long, hostId:Option[Long], guestId:Option[Long], officeId:Option[Long], departmentId:Option[Long], timeIn:Option[Timestamp], timeOut:Option[Timestamp], status:String, timezone:Option[String], created_at:Option[Timestamp], updated_at:Option[Timestamp])

  class VisitationTable(tag: Tag) extends Table[VisitationEntity](tag,"visitations") {
    def id: Rep[Long] = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def hostId: Rep[Option[Long]] = column[Option[Long]]("host_id")

    def guestId: Rep[Option[Long]] = column[Option[Long]]("guest_id")

    def officeId: Rep[Option[Long]] = column[Option[Long]]("office_id")

    def departmentId: Rep[Option[Long]] = column[Option[Long]]("department_id")


    def timeIn: Rep[Option[Timestamp]] = column[Option[Timestamp]]("time_in")

    def timeOut: Rep[Option[Timestamp]] = column[Option[Timestamp]]("time_out")

    def status: Rep[String] = column[String]("status", O.Default("PENDING"))

    def timezone: Rep[Option[String]] = column[Option[String]]("timezone")

    def created_at: Rep[Option[Timestamp]] = column[Option[Timestamp]]("created_at", SqlType("timestamp not null default CURRENT_TIMESTAMP "))

    def updated_at: Rep[Option[Timestamp]] = column[Option[Timestamp]]("updated_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

      override def * :ProvenShape[VisitationEntity] = (id, hostId, guestId,officeId,departmentId, timeIn, timeOut, status, timezone, created_at, updated_at) .mapTo[VisitationEntity]
     }