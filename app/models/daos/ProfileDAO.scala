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


  def findProfile(firstName:String,othernames:String,profileType:String):Future[Seq[ProfileEntity]]={
  val query = profiles.filter(profiles => profiles.firstName.asColumnOf[String].like(firstName)  && profiles.othernames.asColumnOf[String].like(othernames)   && profiles.profileType.asColumnOf[String].like(profileType)   )
    db.run(query.result);

}
  //todo: create
  def create(profile: ProfileEntity): Future[ProfileEntity] = {
    val query = profiles.returning(profiles) += profile
    db.run(query)
  }


  def list(offset: Long, limit: Long,profileType:String): Future[Seq[ProfileEntity]] =   {
    val query =  profiles.filter(_.profileType.asColumnOf[String].like(profileType)).drop(offset).take(limit)
    db.run(query.result)
  }

  def get(id: Long): Future[Option[ProfileEntity]] = {
    db.run(profiles.filter(_.id === id).result.headOption)
  }



  def update(id: Long, profile: ProfileEntity): Future[ProfileEntity] = {
    val query = profiles.filter(_.id === id).update(profile)
    db.run(query)
    Future.successful(profile)
  }

  def delete(id: Long): Future[Int] = {
    val query = profiles.filter(_.id === id).delete
    db.run(query)
  }


  def archive(id: Long): Future[Int] = {
    val query = profiles.filter(_.id === id).map(_.status).update(Some("ARCHIVED"))
    db.run(query)
  }


}
