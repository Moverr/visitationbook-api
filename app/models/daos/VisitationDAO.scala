package models.daos


import models.entities.{VisitationEntity, VisitationTable}

import scala.concurrent.{Await, ExecutionContext, Future}
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

import scala.concurrent.impl.Promise



@Singleton
class VisitationDAO  @Inject()
(protected  val dbConfigProvider: DatabaseConfigProvider)(  implicit executionContext: ExecutionContext) extends TVisitationDAO {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  override lazy val visitationTable = TableQuery[VisitationTable]

  import dbConfig._

  //todo: create
  override def create(visitation: VisitationEntity): Future[VisitationEntity] = {
    val query = visitationTable.returning(visitationTable) += visitation
    db.run(query)
  }

  //todo: list
  override def list(offset: Long, limit: Long): Future[Seq[VisitationEntity]] = {
    db.run(visitationTable.sortBy(_.created_at).take(offset).drop(limit).result)
  }

  //todo: get by id
  override def get(id: Long): Future[Option[VisitationEntity]] = {
    db.run(visitationTable.filter(_.id === id).result.headOption)
  }

  //todo: update
  override def update(id: Long, visitation: VisitationEntity): Future[VisitationEntity] = {

    val query = visitationTable.filter(_.id === id).update(visitation)
    db.run(query)
    Future.successful(visitation)
  }

  override def delete(id: Long): Any = {
    val query = visitationTable.filter(_.id === id).delete
    db.run(query)
  }


  def archive(id: Long): Any = {
    //val query = visitationTable.filter(_.id === id).map(_.status=="archived").update()
     ???

  }
}