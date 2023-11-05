package services

import controllers.requests.VisitRequest
import controllers.responses.{OfficeResponse, ProfileResponse, RequestVisitResponse}
import models.daos.RequestVisitationDAO
import models.entities.{ProfileEntity, visitationRequestEntity}
import org.joda.time.DateTime

import java.sql.Timestamp
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import scala.math.Ordered.orderingToOrdered

@Singleton
class RequestVisitationImpl @Inject()(
                                       val requestVisitationDao: RequestVisitationDAO
                                       , val profileServiceImpl: ProfileServiceImpl
                                       , implicit val executionContext: ExecutionContext
                                     ) {

  def create(request: VisitRequest): Either[Throwable, Future[RequestVisitResponse]] = {

    //DateTime.parse(request.timeOut)
    val timeInDate: Option[DateTime] = request.timeIn.map((x: String) => DateTime.parse(x))
    val timeOutDate: Option[DateTime] = request.timeOut.map((x: String) => DateTime.parse(x))
    //todo:validate that the user does not enter the same records twice.


      if ( (timeInDate.isDefined && timeOutDate.isDefined) && (timeInDate.get.toDateTime().toDateTime() > timeOutDate.get.toDateTime())) {
          Left(new RuntimeException("Time in Date is less than Time out Date"))
      }
    else{


          val visit: visitationRequestEntity = visitationRequestEntity(0L, Some(request.hostId), Some(request.guestId), request.officeId, request.departmentId, request.invitationType, new Timestamp(System.currentTimeMillis()), None, None, None, timeInDate.map((x: DateTime) => new Timestamp(x.getMillis))
            , timeOutDate.map((x: DateTime) => new Timestamp(x.getMillis))
            , Some("PENDING")
          )
          val response = requestVisitationDao.create(visit)
          Right(response.map((record) => populate(record))(executionContext))

      }

  }

  def list(offset: Long, limit: Long): Future[Seq[RequestVisitResponse]] = {
    val response: Future[Seq[(visitationRequestEntity, Option[ProfileEntity], Option[ProfileEntity])]] = requestVisitationDao.list(offset, limit)

    response.map {
      record => record.map(item => populate(item))
    }

  }





  private def populateOfficeResponse(officeID: Option[Long]): Option[OfficeResponse] = officeID match {
    case Some(value) => Some(OfficeResponse(value, ""))
    case None => None
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
      , populateOfficeResponse(None)
      , entity._1.startDate.map((x: Timestamp) => x.toString)
      , entity._1.endDate.map((x: Timestamp) => x.toString)
      , entity._1.status.getOrElse(" - ")
      , entity._1.invType.getOrElse(" - ")
      , None
      , Some(entity._1.createdAt)
      , entity._1.updatedAt
    )

  }


  def populate(entity: visitationRequestEntity): RequestVisitResponse = {
    RequestVisitResponse(
      entity.id,
      populateProfile(None),
      populateProfile(None),
      populateOfficeResponse(entity.officeId),
      entity.startDate.map((x: Timestamp) => x.toString)
      , entity.endDate.map((x: Timestamp) => x.toString)
      , entity.status.getOrElse("-")
      , entity.invType.getOrElse(" - ")
      , None
      , Some(entity.createdAt)
      , entity.updatedAt
    )

  }

}
