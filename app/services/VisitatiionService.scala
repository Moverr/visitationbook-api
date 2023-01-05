package services

import controllers.requests.VisitationRequest
import controllers.responses.VisitationResponse
import models.daos.TVisitationDAO
import models.entities.Visitation

import java.sql.Timestamp
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class VisitatiionServic @Inject()(visitationDao:TVisitationDAO)(  implicit executionContext: ExecutionContext)  {

  //todo: create
  def create(request:VisitationRequest): Future[Either[VisitationResponse,Throwable]] ={
    val visit:Visitation = new Visitation(0l, Some(request.hostId),Some(request.guestId),Some( new Timestamp( request.timeIn.getMillis)),Some( new Timestamp( request.timeOut.getMillis)),request.status,request.timezone,  Timestamp, Timestamp)
    val x:Future[Visitation] = visitationDao.add(visit)
      ???

  }
  //todo: list
  def list(offset:Integer,limit:Integer): Future[Seq[VisitationResponse]] ={
    ???
  }
  //todo: get by id
  def getById(id:Integer):Future[Option[VisitationResponse]]={
  ???
  }

  def archive(id:Integer): Unit ={
    ???
  }
  def populate(entity:Visitation):VisitationResponse={
      ???
  }
}
