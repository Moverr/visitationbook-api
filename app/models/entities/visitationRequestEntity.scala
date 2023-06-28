package models.entities



import slick.jdbc.PostgresProfile.api._
import slick.sql.SqlProfile.ColumnOption.SqlType

import java.sql.Timestamp
import slick.jdbc.PostgresProfile.api._
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery



case class visitationRequestEntity(id:Long
                                   , hostId:Option[Long]
                                   , guestId:Option[Long]
                                   , officeId:Option[Long]
                                   , departmentId:Option[Long],
                                   invType:Option[String],
                                   dateCreated:Timestamp,
                                   createdBy:Option[Long],
                                   updatedAt:Option[Timestamp]
                                   , updatedBy:Option[Long]
                                  ,startDate:Option[Timestamp]
                                  ,endDate:Option[Timestamp]
                            )

class VisitationRequestTable(tag:Tag) extends  Table[visitationRequestEntity] (tag,"visitationrequests"){

  def id = column[Long]("id",O.AutoInc,O.PrimaryKey)
  def hostId = column[Option[Long]]("host_id")
  def guestId = column[Option[Long]]("guest_id")
  def officeId = column[Option[Long]]("office_id")
  def departmentId = column[Option[Long]]("department_id")
  def invType = column[Option[String]]("inv_type")
  def dateCreated = column[Timestamp]("date_created", SqlType("timestamp not null default CURRENT_TIMESTAMP "))
  def createdBy = column[Option[Long]]("created_by")
  def dateUpdated = column[Timestamp]("date_updated", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))
  def updatedBy = column[Option[Long]]("updated_by")

  def startDate = column[Option[Timestamp]]("start_date")

  def endDate = column[Option[Timestamp]]("end_date")

  override def * = (id, hostId, guestId, officeId, departmentId,invType, dateCreated, createdBy, dateUpdated, updatedBy,startDate,endDate ).mapTo[visitationRequestEntity]

}