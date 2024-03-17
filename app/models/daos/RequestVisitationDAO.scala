package models.daos

import models.entities.{ProfileEntity, ProfileTable, VisitationRequestTable, visitationRequestEntity}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}


@Singleton
class RequestVisitationDAO @Inject()(private val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) {

  val visitationRequets = TableQuery[VisitationRequestTable]
  val profiles = TableQuery[ProfileTable]
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  def create(visitation: visitationRequestEntity): Future[visitationRequestEntity] = {
    val query = visitationRequets.returning(visitationRequets) += visitation
    db.run(query)
  }

  def update(id: Long, visitation: visitationRequestEntity): Future[visitationRequestEntity] = {
    val query = visitationRequets.filter(_.id === id).update(visitation)
    db.run(query).map(_ => visitation)
  }


  def list(optionalOwner: Option[Long], offset: Long, limit: Long): Future[Seq[(visitationRequestEntity, Option[ProfileEntity], Option[ProfileEntity])]] = {

    var query = for {
      ((a, b), c) <- visitationRequets
        .joinLeft(profiles).on(_.hostId === _.id)
        .joinLeft(profiles).on(_._1.guestId === _.id)
    } yield (a, b, c)

    query = optionalOwner match {
      case Some(ownerID: Long) => query.filter(_._1.createdBy === ownerID)
      case None => query
    }

    db.run(query.sortBy(_._1.dateCreated.desc).drop(offset).take(limit).result)
      .recover {
        case e: Throwable => throw new RuntimeException(s"Error occurred while fetching visitation requests     ", e)
      }

  }

  def get(id: Long): Future[Option[(visitationRequestEntity, Option[ProfileEntity], Option[ProfileEntity])]] = {

    val basic = for {
      ((a, b), c) <- visitationRequets
        .joinLeft(profiles).on(_.hostId === _.id)
        .joinLeft(profiles).on(_._1.guestId === _.id)

    } yield (a, b, c)

    db.run(basic.filter(_._1.id === id).result.headOption)
      .recover {
        case _: NoSuchElementException => None
        case e: Throwable => throw new RuntimeException(s"Error occurred while fetching visitation request with id $id", e)
      }

  }

  def getById(id: Long): Future[Option[visitationRequestEntity]] = {
    val query = visitationRequets.filter(_.id === id).result.headOption
    db.run(query).recover {
      case _: NoSuchElementException => None
      case e: Throwable => throw new RuntimeException(s"Error occurred while fetching visitation request with id $id", e)
    }
  }


  def delete(id: Long): Any = {
    val query = visitationRequets.filter(_.id === id).delete
    db.run(query)
  }


  def archive(id: Long): Any = {
    //val query = visitationRequestTable.filter(_.id === id).map(_.status).update("ARCHIVED")
    //db.run(query)

  }

}
