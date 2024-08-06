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




}
