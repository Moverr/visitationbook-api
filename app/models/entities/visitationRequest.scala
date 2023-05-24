package models.entities



import slick.jdbc.PostgresProfile.api._
import slick.sql.SqlProfile.ColumnOption.SqlType

import java.sql.Timestamp
import slick.jdbc.PostgresProfile.api._
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery



case class visitationRequest(id:Long
                             ,hostId:Option[Long]
                             ,guestId:Option[Long]
                             ,officeId:Option[Long]
                             ,departmentId:Option[Long],
                             invtype:Option[String],
                             date_created:Timestamp,
                             created_by:Option[Long],
                             updated_at:Timestamp
                             ,updated_by:Option[Long]
                            )

class VisitationRequestTable(tag:Tag) extends  Table[visitationRequest] (tag,"visitationrequests"){

  def id = column[Long]("id",O.AutoInc,O.PrimaryKey)
  def hostId = column[Option[Long]]("hostid")
  def guestId = column[Option[Long]]("guestid")
  def officeId = column[Option[Long]]("officeid")
  def departmentId = column[Option[Long]]("departmentid")
  def invtype = column[Option[String]]("invtype")
  def date_created = column[Timestamp]("datecreated", SqlType("timestamp not null default CURRENT_TIMESTAMP "))
  def created_by = column[Option[Long]]("createdby")
  def date_updated = column[Timestamp]("dateupdated", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))
  def updated_by = column[Option[Long]]("updatedby")

  override def * = (id, hostId, guestId, officeId, departmentId,invtype, date_created, created_by, date_updated, updated_by ).mapTo[visitationRequest]

}