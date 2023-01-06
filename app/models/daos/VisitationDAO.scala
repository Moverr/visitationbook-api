package models.daos


import models.entities.{Visitation, VisitationTable}

import scala.concurrent.{ExecutionContext, Future}
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery



@Singleton
class VisitationDAO  @Inject()
(protected  val dbConfigProvider: DatabaseConfigProvider)(  implicit executionContext: ExecutionContext) extends TVisitationDAO {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  override lazy val visitationTable = TableQuery[VisitationTable]

  import dbConfig._

  //todo: create
  override def create(visitation:Visitation): Future[Visitation] = {
    val query = visitationTable.returning(visitationTable) += visitation
    db.run(query)
  }
  //todo: list
  override def list(offset:Long,limit:Long):Future[Seq[Visitation]] ={
    db.run(visitationTable.sortBy(_.created_at).take(limit).drop(offset).result)
  }
  //todo: get by id
  override def get(id:Long):Future[Option[Visitation]]={
    db.run(visitationTable.filter(_.id === id).result.headOption)
  }
  //todo: update
  override def update(id:Long,visitation: Visitation): Future[Either[Throwable,String]] = {

    val query =visitationTable.filter(_.id === id).update(visitationTable) = visitation
    db.run(query)
    ???

  }
  // ??
}
