package services

import controllers.requests.VisitationRequest
import controllers.responses._
import models.daos.{TVisitationDAO, VisitationDAO}
import models.entities.Visitation
import org.joda.time.{DateTime, DateTimeZone}
import utils._

import java.sql.Timestamp
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class VisitationService @Inject()(visitationDao:VisitationDAO)(implicit executionContext: ExecutionContext)  extends TVisitationService {

  //todo: create
  override def create(request:VisitationRequest): Future[VisitationResponse] ={
    val visit:Visitation = new Visitation(0L, Some(request.hostId),Some(request.guestId),Some( new Timestamp( request.timeIn.getMillis)),Some( new Timestamp( request.timeOut.getMillis)),request.status,request.timezone,   new Timestamp(DateTime.now(DateTimeZone.UTC).getMillis), new Timestamp(DateTime.now(DateTimeZone.UTC).getMillis))
    val response:Future[Visitation] = visitationDao.create(visit)
    response.map(record=>populate(record))
  }
  //todo: lists
  override def list(offset:Long,limit:Long): Future[Seq[VisitationResponse]] ={
    val response:Future[Seq[Visitation]] = visitationDao.list(offset,limit)
    response.map(futureResponse=>futureResponse.map(record=>populate(record)))
  }
  //todo: get by id
  override def getById(id:Long):Future[Option[VisitationResponse]]={
    val response:Future[Option[Visitation]] = visitationDao.get(id)
    response.map(value=>value.map(optionValue=>populate(optionValue)))
  }

  override def archive(id:Long): Unit ={

    ???
  }

  def delete(id:Long): Unit ={
    visitationDao.delete(id)
  }
  override def populate(entity:Visitation):VisitationResponse=
    VisitationResponse (
     entity.id
     , entity.hostId
     , entity.guestId
     ,  entity.timeIn
     ,   entity.timeOut
     , entity.status
     , entity.timezone
     ,  entity.created_at
     , entity.updated_at
   )


}
