package services

import controllers.requests.VisitRequest
import controllers.responses.{GuestResponse, HostReponse, OfficeResponse, RequestVisitResponse, VisitResponse}
import models.daos.RequestVisitationDAO
import models.entities.visitationRequestEntity
import org.joda.time.DateTime

import java.sql.Timestamp
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import scala.math.Ordered.orderingToOrdered

@Singleton
class RequestVisitationImpl @Inject()(requestVisitationDao: RequestVisitationDAO)(implicit executionContext: ExecutionContext) {

  def create(request: VisitRequest): Either[Throwable, Future[RequestVisitResponse]] = {
    //DateTime.parse(request.timeOut)
    val timeInDate: Option[DateTime] = request.timeIn.map((x: String) => DateTime.parse(x))
    val timeOutDate: Option[DateTime] = request.timeOut.map((x: String) => DateTime.parse(x))
    //todo:validate that the user does not enter the same records twice.

    if (timeInDate.isDefined && timeOutDate.isDefined) {
      if (timeInDate.get.toDateTime().toDateTime() > timeOutDate.get.toDateTime()) {
        return Left(new RuntimeException("Time in Date is less than Time out Date"))
      }
    }

    val visit: visitationRequestEntity = visitationRequestEntity(0L, request.hostId, request.guestId, request.officeId, request.departmentId, request.invitationType, new Timestamp(System.currentTimeMillis()), None, None, None, timeInDate.map((x: DateTime) => new Timestamp(x.getMillis)), timeOutDate.map((x: DateTime) => new Timestamp(x.getMillis)))
    val response: Future[visitationRequestEntity] = requestVisitationDao.create(visit)
    Right(response.map((record: visitationRequestEntity) => populate(record))(executionContext))
  }

  def list(offset: Long, limit: Long): Future[Seq[RequestVisitResponse]] = {
    val response: Future[Seq[visitationRequestEntity]] = requestVisitationDao.list(offset, limit)
    response.map((futureResponse: _root_.scala.collection.immutable.Seq[_root_.models.entities.visitationRequestEntity]) => futureResponse.map((record: visitationRequestEntity) => populate(record)))(executionContext)
  }

  def getById(id: Long): Future[Option[RequestVisitResponse]] = {
    val response: Future[Option[visitationRequestEntity]] = requestVisitationDao.get(id)
    response.map((value: _root_.scala.Option[_root_.models.entities.visitationRequestEntity]) => value.map((optionValue: visitationRequestEntity) => populate(optionValue)))
  }

  def delete(id: Long): Future[Either[Throwable, Boolean]] = {
    val response: Future[Option[visitationRequestEntity]] = requestVisitationDao.get(id)
    response.map({
      case Some(value: visitationRequestEntity) =>
        requestVisitationDao.delete(value.id)
        Right(true)

      case None => Left(new RuntimeException("Record does not exist"))
    })

  }


  def populate(entity: visitationRequestEntity): RequestVisitResponse = {
    RequestVisitResponse(
      entity.id,
      populateHostResponse(entity.hostId),
      populateGuestResponse(entity.guestId),
      populateOfficeResponse(entity.officeId),
      entity.startDate.map((x: Timestamp) => x.toString)
      , entity.endDate.map((x: Timestamp) => x.toString)
      , "STATUS"
      , None
      , Some(entity.createdAt)
      , entity.updatedAt
    )

  }

  private def populateGuestResponse(guestID: Option[Long]): Option[GuestResponse] =
    guestID match {
      case Some(value) => Some(GuestResponse(value, "", ""))
      case None => None
    }

  private def populateHostResponse(hostID: Option[Long]): Option[HostReponse] =
    hostID match {
      case Some(value) => Some(HostReponse(value, "", ""))
      case None => None
    }

  private def populateOfficeResponse(officeID: Option[Long]): Option[OfficeResponse] = officeID match {
    case Some(value) => Some(OfficeResponse(value, ""))
    case None => None
  }




}