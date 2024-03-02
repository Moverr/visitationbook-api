package services

import controllers.requests.VisitRequest
import controllers.responses.{OfficeResponse, ProfileResponse, RequestVisitResponse}
import models.daos.RequestVisitationDAO
import models.dtos.Auth
import models.entities.{ProfileEntity, visitationRequestEntity}
import models.enums.StatusEnum
import org.joda.time.DateTime

import java.sql.Timestamp
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import scala.math.Ordered.orderingToOrdered

@Singleton
class RequestVisitationImpl @Inject()(
                                       val requestVisitationDao: RequestVisitationDAO
                                       , val profileServiceImpl: ProfileServiceImpl
                                       , val officeServiceImpl: OfficeServiceImpl
                                       , implicit val executionContext: ExecutionContext
                                     ) {

  def create(authorizedUser: Auth, request: VisitRequest): Either[Throwable, Future[RequestVisitResponse]] = {


    val timeInDate: Option[DateTime] = request.timeIn.map((x: String) => DateTime.parse(x))
    val timeOutDate: Option[DateTime] = request.timeOut.map((x: String) => DateTime.parse(x))


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
      val response = requestVisitationDao.create(visit)
      Right(response.map((record) => populate(record))(executionContext))

    }

  }

  def list(offset: Long, limit: Long): Either[Throwable, Future[Seq[RequestVisitResponse]]] = {
    val response: Future[Seq[(visitationRequestEntity, Option[ProfileEntity], Option[ProfileEntity])]] = requestVisitationDao.list(offset, limit)
    val records = response.map { record => record.map(item => populate(item)) }
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
      , entity._1.startDate.map((x: Timestamp) => x.toString)
      , entity._1.endDate.map((x: Timestamp) => x.toString)
      , entity._1.status.getOrElse("NONE")
      , entity._1.invType.getOrElse("NONE")
      , None
      , Some(entity._1.createdAt)
      , entity._1.updatedAt
    )

  }


  def populate(entity: visitationRequestEntity): RequestVisitResponse = {
    RequestVisitResponse(
      entity.id,
      profileServiceImpl.populate(None),
      profileServiceImpl.populate(None),
      officeServiceImpl.populate(entity.officeId),
      entity.startDate.map((x: Timestamp) => x.toString)
      , entity.endDate.map((x: Timestamp) => x.toString)
      , entity.status.getOrElse("NONE")
      , entity.invType.getOrElse("NONE")
      , None
      , Some(entity.createdAt)
      , entity.updatedAt
    )

  }

}
