package services

import controllers.requests.VisitRequest
import controllers.responses.VisitResponse
import models.daos.RequestVisitationDAO
import models.entities.visitationRequestEntity
import org.joda.time.DateTime

import java.sql.Timestamp
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
@Singleton
class VisitRequestServiceImpl @Inject()(requestVisitationDao: RequestVisitationDAO)(implicit executionContext: ExecutionContext){

  def create(request: VisitRequest): Either[Throwable, Future[VisitResponse]] = {
    val timeOutDate: Option[DateTime] = request.timeIn.map(x=>DateTime.parse(x))
    //DateTime.parse(request.timeOut)
    val timeInDate: Option[DateTime]  = request.timeOut.map(x => DateTime.parse(x))


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
    ???
  }

}
