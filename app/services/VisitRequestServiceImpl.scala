package services

import controllers.requests.VisitRequest
import controllers.responses.VisitResponse
import models.daos.RequestVisitationDAO
import models.entities.visitationRequestEntity
import org.joda.time.DateTime

import java.sql.Timestamp
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import scala.math.Ordered.orderingToOrdered
@Singleton
class VisitRequestServiceImpl @Inject()(requestVisitationDao: RequestVisitationDAO)(implicit executionContext: ExecutionContext){

  def create(request: VisitRequest): Either[Throwable, Future[VisitResponse]] = {
      //DateTime.parse(request.timeOut)
    val timeInDate: Option[DateTime]  = request.timeIn.map(x => DateTime.parse(x))
    val timeOutDate: Option[DateTime] = request.timeOut.map(x => DateTime.parse(x))
    //todo:validate that the user does not enter the same records twice.

    if(timeInDate.isDefined && timeOutDate.isDefined){
      if(timeInDate.get.toDateTime().toDateTime() > timeOutDate.get.toDateTime()){
        return Left(new RuntimeException("Time in Date is less than Time out Date"))
      }
    }

    val visit: visitationRequestEntity = visitationRequestEntity(0L, request.hostId, request.guestId, request.officeId, request.departmentId,request.invitationType, new Timestamp(System.currentTimeMillis()),None,None,None, timeInDate.map(x=>new Timestamp(x.getMillis)),timeOutDate.map(x=>new Timestamp(x.getMillis)) )
    val response: Future[visitationRequestEntity] = requestVisitationDao.create(visit)
    Right(response.map(record => populate(record)))
  }

  def list(offset: Long, limit: Long): Future[Seq[VisitResponse]] = {
    val response: Future[Seq[visitationRequestEntity]] = requestVisitationDao.list(offset, limit)
    response.map(futureResponse => futureResponse.map(record => populate(record)))
  }

  def getById(id: Long): Future[Option[VisitResponse]] = {
    val response: Future[Option[visitationRequestEntity]] = requestVisitationDao.get(id)
    response.map(value => value.map(optionValue => populate(optionValue)))
  }


  def delete(id: Long): Future[Either[Throwable, Boolean]] = {
    val response: Future[Option[visitationRequestEntity]] = requestVisitationDao.get(id)
    response.map {
      case Some(value) =>
        requestVisitationDao.delete(value.id)
        Right(true)

      case None => Left(new RuntimeException("Record does not exist"))
    }

  }

  def populate(entity: visitationRequestEntity): VisitResponse = {
     val response:VisitResponse  = VisitResponse(entity.id,entity.hostId,entity.guestId,entity.officeId,entity.departmentId,entity.startDate.map(x=>x.toString),entity.endDate.map(x=>x.toString),entity.invType);
    response
  }

}
