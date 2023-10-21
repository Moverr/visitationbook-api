package models.daos

import com.google.inject.Inject
import models.entities.{ProfileEntity, ProfileTable, visitationRequestEntity}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery

import javax.inject.Singleton
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ProfileDAO @Inject()( private val dbConfigProvider:DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
{

  val profiles = TableQuery[ProfileTable]
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  //todo: create
  def create(profile: ProfileEntity): Future[ProfileEntity] = {
    val query = profiles.returning(profiles) += profile
    db.run(query)
  }

  //todo: list
  def list(offset: Long, limit: Long): Future[Seq[ProfileEntity]] =   db run profiles.drop(offset).take(limit).result

  //todo: get by id
  def get(id: Long): Future[Option[ProfileEntity]] = {
    db.run(profiles.filter(_.id === id).result.headOption)
  }

  def update(id: Long, profile: ProfileEntity): Future[ProfileEntity] = {
    val query = profiles.filter(_.id === id).update(profile)
    db.run(query)
    Future.successful(profile)
  }

  def delete(id: Long): Any = {
    val query = profiles.filter(_.id === id).delete
    db.run(query)
  }


  def archive(id: Long): Future[Int] = {
    val query = profiles.filter(_.id === id).map(_.status).update("ARCHIVED")
    db.run(query)

  }


}
