package services.interfaces

import controllers.requests.VistationRequest
import controllers.responses.VisitationResponse
import models.entities.VisitationEntity

import scala.concurrent.Future

trait TVisitationService {

  //todo: create
  def create(request: VistationRequest): Either[Throwable, Future[VisitationResponse]]

  //todo: lists
  def list(offset: Long, limit: Long): Future[Seq[VisitationResponse]]

  //todo: get by id
  def getById(id: Long): Future[Option[VisitationResponse]]

  def archive(id: Long): Unit

  def populate(entity: VisitationEntity): VisitationResponse
}
