package services

import controllers.requests.VisitRequest
import controllers.responses.RequestVisitResponse
import models.daos.RequestVisitationDAO
import models.dtos.Auth
import models.entities.{ProfileEntity, visitationRequestEntity}
import models.enums.StatusEnum
import org.joda.time.{DateTime, DateTimeZone}
import play.api.Logger
import utils.Util.{extractOwner, getTimeStamp}

import java.sql.Timestamp
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class RequestVisitationImpl @Inject()(
                                       val requestVisitationDao: RequestVisitationDAO
                                       , val profileServiceImpl: ProfileServiceImpl
                                       , val officeServiceImpl: OfficeServiceImpl
                                       , implicit val executionContext: ExecutionContext
                                     ) {

  private val RESOURCE: String = "VISITATIONREQUESTS"
  private lazy val log = Logger(getClass).logger

  def create(authorizedUser: Auth, request: VisitRequest): Either[Throwable, Future[RequestVisitResponse]] = {

    val dataResponse = extractOwner(authorizedUser, RESOURCE) match {
      case Some(ownerId) =>

        val timeInDate: DateTime = new DateTime(request.timeIn, DateTimeZone.UTC)
        val timeOutDate: DateTime = new DateTime(request.timeOut, DateTimeZone.UTC)

        val currentDate: DateTime = new DateTime(DateTimeZone.UTC)

        log.info(s" Current  time ${currentDate} ")
        log.info(s" Starting time ${timeInDate} ")
        log.info(s" ending  time ${timeOutDate} ")

        if (timeInDate.isAfter(timeOutDate)) {
          Left(new RuntimeException("Invalid time range"))
        }
        else {
          val visit: visitationRequestEntity = visitationRequestEntity(
            0L
            , Some(request.hostId)
            , Some(request.guestId)
            , request.officeId
            , request.departmentId
            , request.invitationType
            , new Timestamp(System.currentTimeMillis())
            , Some(ownerId)
            , None
            , None
            , Some(getTimeStamp(timeInDate))
            , Some(getTimeStamp(timeOutDate))
            , Some(StatusEnum.PENDING.toString)
          )
          val response = requestVisitationDao.create(visit)

          Right(response.map(populate))
        }

      case None => Left(new RuntimeException("User is not authorized "))
    }
    dataResponse
  }


  def update(authorizedUser: Auth, id: Long, request: VisitRequest): Either[Throwable, Future[RequestVisitResponse]] = {

    val resp = extractOwner(authorizedUser, RESOURCE)

    resp match {
      case Some(value) =>
      val response = requestVisitationDao.getById(id)
     val responseData =  response.flatMap {
          case Some(entity:visitationRequestEntity) => ???
          case None => ???
        }

      case None => ???
    }


    /*
    val response = requestVisitationDao.getById(id)
      .flatMap {
        case Some(entity:visitationRequestEntity) =>

          ???
        //Left(new RuntimeException("Time in Date is less than Time out Date"))
        case None =>
          ???
        //Left(new RuntimeException("Time in Date is less than Time out Date"))
      }

    Right(response.recover {
      case e: Throwable => throw new RuntimeException("Error occurred during visit update", e)
    })
    */

    /*

  visitationRequest.map {
      case Some(existingRequest) =>
        val timeInDate: Option[DateTime] = request.timeIn.map(DateTime.parse)
        val timeOutDate: Option[DateTime] = request.timeOut.map(DateTime.parse)

        if ((timeInDate.isDefined && timeOutDate.isDefined) && (timeInDate.get.toDateTime().toDateTime() > timeOutDate.get.toDateTime())) {
          Left(new RuntimeException("Time in Date is less than Time out Date"))
        }
        else {

          val visit: visitationRequestEntity = visitationRequestEntity(
            0L
            , Some(request.hostId)
            , Some(request.guestId)
            , request.officeId
            , request.departmentId
            , request.invitationType
            , new Timestamp(System.currentTimeMillis())
            , Some(authorizedUser.user.userId)
            , None
            , None
            , timeInDate.map((x: DateTime) => new Timestamp(x.getMillis))
            , timeOutDate.map((x: DateTime) => new Timestamp(x.getMillis))
            , Some(StatusEnum.PENDING.toString)
          )

          val responseData = for {
            response <- requestVisitationDao.update(id, visit)
          } yield populate(response)

          Right(responseData)
        }



      case None => Left(new RuntimeException("Time in Date is less than Time out Date"))
    }

    */


  }


  def list(authorizedUser: Auth, offset: Long, limit: Long): Either[Throwable, Future[Seq[RequestVisitResponse]]] = {

    log.info("List Information ")

    val ownerId: Option[Long] = extractOwner(authorizedUser, RESOURCE)

    val response: Future[Seq[(visitationRequestEntity, Option[ProfileEntity], Option[ProfileEntity])]] =
      requestVisitationDao.list(ownerId, offset, limit)

    val records: Future[Seq[RequestVisitResponse]] = response.map(_.map(populate))
    Right(records)
  }

  def getById(id: Long): Future[Option[RequestVisitResponse]] = {
    val response: Future[Option[(visitationRequestEntity, Option[ProfileEntity], Option[ProfileEntity])]] = requestVisitationDao.get(id)
    response.map((value) => value.map((optionValue) => populate(optionValue)))
  }

  def delete(id: Long): Future[Either[Throwable, Boolean]] = {
    val response = requestVisitationDao.get(id)
    response.map({
      case Some(value) =>
        requestVisitationDao.delete(value._1.id)
        Right(true)
      case None => Left(new RuntimeException("Record does not exist"))
    })
  }


  def populate(entity: (visitationRequestEntity, Option[ProfileEntity], Option[ProfileEntity])): RequestVisitResponse = {
    RequestVisitResponse(
      entity._1.id
      , profileServiceImpl.populate(entity._2)
      , profileServiceImpl.populate(entity._3)
      , officeServiceImpl.populate(None)
      , entity._1.startDate.map((x: Timestamp) => x.toLocalDateTime.toString)
      , entity._1.endDate.map((x: Timestamp) => x.toLocalDateTime.toString)
      , entity._1.status.getOrElse("NONE")
      , entity._1.invType.getOrElse("NONE")
      , None
      , Some(entity._1.createdAt.toLocalDateTime.toString)
      , entity._1.updatedAt.map(x=>x.toLocalDateTime.toString)
    )

  }

  def populate(entity: visitationRequestEntity): RequestVisitResponse = {
    RequestVisitResponse(
      entity.id,
      profileServiceImpl.populate(None),
      profileServiceImpl.populate(None),
      officeServiceImpl.populate(entity.officeId),
      entity.startDate.map((x: Timestamp) => x.toLocalDateTime.toString)
      , entity.endDate.map((x: Timestamp) => x.toLocalDateTime.toString)
      , entity.status.getOrElse("NONE")
      , entity.invType.getOrElse("NONE")
      , None
      , Some(entity.createdAt.toLocalDateTime.toString)
      , entity.updatedAt.map(x=>x.toLocalDateTime.toString)
    )

  }


}
