package models.daos

import models.entities.{VisitationEntity, VisitationTable}
import slick.lifted.TableQuery

import scala.concurrent.Future

trait TVisitationDAO {

  val visitationTable: TableQuery[VisitationTable]

  //todo: create
  def create(visitation: VisitationEntity): Future[VisitationEntity]

  //todo: list
  def list(offset: Long, limit: Long): Future[Seq[VisitationEntity]]

  //todo: get by id
  def get(id: Long): Future[Option[VisitationEntity]]

  //todo: update

  def update(id: Long, visitation: VisitationEntity): Future[VisitationEntity]

  //todo: delete recrod
  def delete(id:Long):Any

}
