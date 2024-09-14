package models.daos

import models.{Address, AddressTable}
import models.entities.{ProfileEntity, ProfileTable, VisitationTable}
import play.api.db
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted
import slick.lifted.TableQuery

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AddressDAO @Inject()(private val dbConfigProvider:DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
{


  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  val addressTable =  lifted.TableQuery[AddressTable]

  def create(address: Address): Future[Address] = {
    val query = addressTable.returning(addressTable) += address
    db.run(query)
  }

  def findById(id: Long): Future[Option[Address]] = {
    db.run(addressTable.filter(_.id === id).result.headOption)
  }

  def list(offset: Long, limit: Long): Future[Seq[Address]] = {
    db.run(addressTable.drop(offset).take(limit).result)
  }

  def delete(id: Long): Future[Int] = {
    db.run(addressTable.filter(_.id === id).delete)
  }



}
