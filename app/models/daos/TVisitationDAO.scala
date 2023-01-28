package models.daos

import models.entities.{Visitation, VisitationTable}
import slick.lifted.TableQuery

import scala.concurrent.Future

trait TVisitationDAO {

  val visitationTable: TableQuery[VisitationTable]

  //todo: create
  def create(visitation: Visitation): Future[Visitation]

  //todo: list
  def list(offset: Long, limit: Long): Future[Seq[Visitation]]

  //todo: get by id
  def get(id: Long): Future[Option[Visitation]]

  //todo: update

  def update(id: Long, visitation: Visitation): Future[Visitation]

  //todo: delete recrod
  def delete(id:Long):Any

}
