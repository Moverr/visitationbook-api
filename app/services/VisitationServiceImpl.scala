package services

import controllers.requests.VisitationRequest
import controllers.responses._
import models.daos.VisitationDAO
import models.entities.{ProfileEntity, VisitationEntity, visitationRequestEntity}
import org.joda.time.{DateTime, DateTimeZone}

import java.sql.Timestamp
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class VisitationServiceImpl @Inject()(
                                       val visitationDao: VisitationDAO
                                      ,val profileServiceImpl: ProfileServiceImpl
                                       ,val officeServiceImpl:OfficeServiceImpl
                                     )(implicit executionContext: ExecutionContext) {

   def create(request: VisitationRequest):  Either[Throwable,Future[VisitationResponse]] = {
    val timeOutDate:DateTime =   DateTime.parse(request.timeOut)
    val timeInDate:DateTime =  DateTime.parse(request.timeIn)
    val  currentDate = DateTime.now(DateTimeZone.forID(request.timezone.getOrElse("UTC")))


    if(timeInDate.isBefore(currentDate)){
      return Left(new RuntimeException("Enter current time"))
    }
    if(timeInDate.isAfter(timeOutDate)){
      return Left(new RuntimeException("Time In should be less than Time Out "))
    }

    val visit: VisitationEntity =   VisitationEntity(0L,request.hostId, request.guestId, request.officeId, request.departmentId, Some(new Timestamp(timeInDate.getMillis)), Some(new Timestamp(timeOutDate.getMillis)), request.status.getOrElse("PENDING"), request.timezone, Some(new Timestamp(DateTime.now(DateTimeZone.UTC).getMillis)), Some(new Timestamp(DateTime.now(DateTimeZone.UTC).getMillis)))
    val response: Future[VisitationEntity] = visitationDao.create(visit)
    Right(response.map(record => populate(record)))
  }

   def list(limit: Long,offset: Long): Future[Seq[VisitationResponse]] =
     visitationDao.list(limit, offset)
        .map(futureResponse => futureResponse.map(record => populate(record)))



   def getById(id: Long): Future[Option[VisitationResponse]] = {
    val response: Future[Option[VisitationEntity]] = visitationDao.get(id)
    response.map(value => value.map(optionValue => populate(optionValue)))
  }


  def delete(id: Long): Future[Either[Throwable,Boolean]] = {
    val response: Future[Option[VisitationEntity]] = visitationDao.get(id)
    response.map {
      case Some(value) =>
        visitationDao.delete(value.id)
        Right(true)

      case None => Left(new RuntimeException("Record does not exist"))
    }

  }

   def populate(entity: VisitationEntity): VisitationResponse =
    VisitationResponse(
      entity.id
      , None
      , None
      ,None
      , entity.timeIn.map(x => x.toString)
      , entity.timeOut.map(x => x.toString)
      , entity.status
      , "-"
      , entity.timezone
      , entity.created_at
      , entity.updated_at
    )


  def populate(entity: (VisitationEntity, Option[ProfileEntity], Option[ProfileEntity])): VisitationResponse = {
    VisitationResponse(
      entity._1.id
      , profileServiceImpl.populate(entity._2)
      , profileServiceImpl.populate(entity._3)
      , officeServiceImpl.populate(None)
      , entity._1.timeIn.map((x: Timestamp) => x.toString)
      , entity._1.timeOut.map((x: Timestamp) => x.toString)
      , entity._1.status
      , "- "
      , None
      , entity._1.created_at
      , entity._1.updated_at
    )

  }


}
