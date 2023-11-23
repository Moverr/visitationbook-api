package services.interfaces

import controllers.requests.VisitationRequest
import controllers.responses.VisitationResponse
import models.entities.VisitationEntity

import scala.concurrent.Future

trait TVisitationService {

  //todo: create
  def create(request: VisitationRequest): Either[Throwable, Future[VisitationResponse]]

  //todo: lists
  def list(offset: Long, limit: Long): Future[Seq[VisitationResponse]]

  //todo: get by id
  def getById(id: Long): Future[Option[VisitationResponse]]

  def archive(id: Long): Unit

  def populate(entity: VisitationEntity): VisitationResponse
}
