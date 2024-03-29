package models.daos


import models.entities.{ProfileEntity, ProfileTable, VisitationEntity, VisitationTable, visitationRequestEntity}

import scala.concurrent.{Await, ExecutionContext, Future}
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

import scala.concurrent.impl.Promise



@Singleton
class VisitationDAO  @Inject()
(protected  val dbConfigProvider: DatabaseConfigProvider)(  implicit val executionContext: ExecutionContext) extends TVisitationDAO {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  val visitationTable = TableQuery[VisitationTable]
  val profiles = TableQuery[ProfileTable]


  import dbConfig._

  //todo: create
  override def create(visitation: VisitationEntity): Future[VisitationEntity] = {
    val query = visitationTable.returning(visitationTable) += visitation
    db.run(query)
  }

  //todo: lists
  override def list( limit: Long,offset: Long): Future[Seq[(VisitationEntity, Option[ProfileEntity], Option[ProfileEntity])]] = {

    val query = for {
      ((a, b), c) <- visitationTable
        .joinLeft(profiles).on(_.hostId === _.id)
        .joinLeft(profiles).on(_._1.guestId === _.id)
    } yield (a, b, c)

    db run query.sortBy(_._1.created_at.desc).drop(offset).take(limit).result
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
    val query = visitationTable.filter(_.id === id).map(_.status).update("ARCHIVED")
    db.run(query)

  }
}