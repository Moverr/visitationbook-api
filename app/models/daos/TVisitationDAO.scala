package models.daos

import models.entities.{ProfileEntity, VisitationEntity, VisitationTable}
import slick.lifted.TableQuery

import scala.concurrent.Future

trait TVisitationDAO {

  val visitationTable: TableQuery[VisitationTable]

  //todo: create
  def create(visitation: VisitationEntity): Future[VisitationEntity]

  //todo: list
  def   list( limit: Long,offset: Long): Future[Seq[(VisitationEntity, Option[ProfileEntity], Option[ProfileEntity])]]

  //todo: get by id
  def get(id: Long): Future[Option[VisitationEntity]]

  //todo: update

  def update(id: Long, visitation: VisitationEntity): Future[VisitationEntity]

  //todo: delete recrod
  def delete(id:Long):Any

}
