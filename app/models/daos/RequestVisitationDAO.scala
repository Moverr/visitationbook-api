package models.daos

import controllers.requests.VisitRequest
import models.entities.{VisitationRequestTable, visitationRequestEntity}
import play.api.db.slick.DatabaseConfigProvider

import javax.inject.{Inject, Singleton}
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.impl.Promise


@Singleton
class RequestVisitationDAO    @Inject()(private val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
{

  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  lazy val visitationRequestTable = TableQuery[VisitationRequestTable]

  import dbConfig._

  //todo: create
   def create(visitation: visitationRequestEntity): Future[visitationRequestEntity] = {
    val query = visitationRequestTable.returning(visitationRequestTable) += visitation
    db.run(query)
  }

  //todo: lists
    def list(offset: Long, limit: Long): Future[Seq[visitationRequestEntity]] = {
    //    val query = for{
    //      (visitation,host,guest)<-
    //    }
    db.run(visitationRequestTable.sortBy(_.dateCreated).take(offset).drop(limit).result)
  }

  //todo: get by id
  def get(id: Long): Future[Option[visitationRequestEntity]] = {
    db.run(visitationRequestTable.filter(_.id === id).result.headOption)
  }

  //todo: update
    def update(id: Long, visitation: visitationRequestEntity): Future[visitationRequestEntity] = {

    val query = visitationRequestTable.filter(_.id === id).update(visitation)
    db.run(query)
    Future.successful(visitation)
  }

    def delete(id: Long): Any = {
    val query = visitationRequestTable.filter(_.id === id).delete
    db.run(query)
  }


  def archive(id: Long): Any = {
    //val query = visitationRequestTable.filter(_.id === id).map(_.status).update("ARCHIVED")
    //db.run(query)

  }

  }
