package services

import controllers.requests.{VisitRequest, VistationRequest}
import controllers.responses.VisitationResponse
import models.daos.RequestVisitationDAO
import models.entities.{VisitationEntity, visitationRequestEntity}
import org.joda.time.{DateTime, DateTimeZone}

import java.sql.Timestamp
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class RequestVisitationServiceImpl @Inject()(requestVisitationDao: RequestVisitationDAO)(implicit executionContext: ExecutionContext){

  def create(request: VisitRequest): Either[Throwable, Future[VisitationResponse]] = {
    val timeOutDate: DateTime = DateTime.parse(request.timeOut)
    val timeInDate: DateTime = DateTime.parse(request.timeIn)

    if (timeInDate.isAfter(timeOutDate)) {
      return Left(new RuntimeException("Time In should be less than Time Out "))
    }

    val visit: visitationRequestEntity = visitationRequestEntity(0L, request.hostId, request.guestId, request.officeId, request.departmentId,request.invitationType, new Timestamp(System.currentTimeMillis()),None,None,None,Some(new Timestamp(timeInDate.getMillis)),Some(new Timestamp(timeInDate.getMillis)) )
    val response: Future[visitationRequestEntity] = requestVisitationDao.create(visit)
    Right(response.map(record => populate(record)))
  }

  def list(offset: Long, limit: Long): Future[Seq[VisitationResponse]] = {
    val response: Future[Seq[visitationRequestEntity]] = requestVisitationDao.list(offset, limit)
    response.map(futureResponse => futureResponse.map(record => populate(record)))
  }

  def getById(id: Long): Future[Option[VisitationResponse]] = {
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

  def populate(entity: visitationRequestEntity): VisitationResponse = {
    ???
  }

}
