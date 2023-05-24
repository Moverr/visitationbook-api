package models.daos

import controllers.requests.RequestVisitation
import models.entities.{VisitationRequestTable, visitationRequest}
import play.api.db.slick.DatabaseConfigProvider

import javax.inject.Inject
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

import scala.concurrent.{ExecutionContext, Future}

class VisitationRequestDAO    @Inject()(private val dbConfigProvider: DatabaseConfigProvider)   (implicit executionContext: ExecutionContext)
{

  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  lazy val table = TableQuery[VisitationRequestTable]

  import dbConfig._

  //todo: create
  def create(req:RequestVisitation): Future[visitationRequest] ={
    ???
  }
  //todo; update
  def update(): Future[visitationRequest] = {
    ???
  }
  //todo: list
  def list(): Future[Seq[visitationRequest]] = {
    ???
  }
  //todo: get
  //todo: archive

  }
