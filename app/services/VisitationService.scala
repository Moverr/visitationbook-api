package services

import controllers.requests.VisitationRequest
import controllers.responses.VisitationResponse
import models.daos.TVisitationDAO
import models.entities.Visitation

import java.sql.Timestamp
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class VisitationService @Inject()(visitationDao:TVisitationDAO)(implicit executionContext: ExecutionContext)  extends TVisitationService {

  //todo: create
  override def create(request:VisitationRequest): Future[VisitationResponse] ={
    val visit:Visitation = new Visitation(0l, Some(request.hostId),Some(request.guestId),Some( new Timestamp( request.timeIn.getMillis)),Some( new Timestamp( request.timeOut.getMillis)),request.status,request.timezone,  Timestamp, Timestamp)
    val response:Future[Visitation] = visitationDao.create(visit)
    response.map(record=>populate(record))
  }
  //todo: lists
  override def list(offset:Long,limit:Long): Future[Seq[VisitationResponse]] ={
    val response:Future[Seq[Visitation]] = visitationDao.list(offset,limit)
    response.map(r=>r.map(x=>populate(x)))
  }
  //todo: get by id
  override def getById(id:Long):Future[Option[VisitationResponse]]={
    val response:Future[Option[Visitation]] = visitationDao.get(id)
    response.map(value=>value.map(optionValue=>populate(optionValue)))
  }

  override def archive(id:Integer): Unit ={
    ???
  }
  override def populate(entity:Visitation):VisitationResponse={
    val respnse:VisitationResponse = new VisitationResponse()
    respnse
  }
}
