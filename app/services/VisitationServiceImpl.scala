package services

import controllers.requests.VisitationsRequest
import controllers.responses._
import models.daos.{TVisitationDAO, VisitationDAO}
import models.entities.Visitation
import org.joda.time.{DateTime, DateTimeZone}
import services.interfaces.TVisitationService
import utils._

import java.sql.Timestamp
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class VisitationServiceImpl @Inject()(visitationDao: VisitationDAO)(implicit executionContext: ExecutionContext) extends TVisitationService {

  //todo: create
  override def create(request: VisitationsRequest):  Either[Throwable,Future[VisitationResponse]] = {
    //validate request
    val timeOutDate:DateTime =   DateTime.parse(request.timeOut);
    val timeInDate:DateTime =  DateTime.parse(request.timeIn)
    if(timeInDate.isAfter(timeOutDate)){
      return Left(new RuntimeException("Time In should be less than Time Out "))
    }

    val visit: Visitation =   Visitation(0L,request.hostId, request.guestId, request.officeId, request.departmentId, Some(new Timestamp(timeInDate.getMillis)), Some(new Timestamp(timeOutDate.getMillis)), request.status, request.timezone, new Timestamp(DateTime.now(DateTimeZone.UTC).getMillis), new Timestamp(DateTime.now(DateTimeZone.UTC).getMillis))
    val response: Future[Visitation] = visitationDao.create(visit)
    Right(response.map(record => populate(record)))
  }

  //todo: lists
  override def list(offset: Long, limit: Long): Future[Seq[VisitationResponse]] = {
    val response: Future[Seq[Visitation]] = visitationDao.list(offset, limit)
    response.map(futureResponse => futureResponse.map(record => populate(record)))
  }

  //todo: get by id
  override def getById(id: Long): Future[Option[VisitationResponse]] = {
    val response: Future[Option[Visitation]] = visitationDao.get(id)
    response.map(value => value.map(optionValue => populate(optionValue)))
  }

  override def archive(id: Long): Unit = {

    ???
  }

  def delete(id: Long): Unit = {
    visitationDao.delete(id)
  }

  override def populate(entity: Visitation): VisitationResponse =
    VisitationResponse(
      entity.id
      , entity.hostId
      , entity.guestId
      , entity.timeIn.map(x => x.toString)
      , entity.timeOut.map(x => x.toString)
      , entity.status
      , entity.timezone
      , entity.created_at
      , entity.updated_at
    )


}
