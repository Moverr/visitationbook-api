package services.interfaces

import controllers.requests.VisitationsRequest
import controllers.responses.VisitationResponse
import models.entities.Visitation

import scala.concurrent.Future

trait TVisitationService {

  //todo: create
  def create(request: VisitationsRequest): Either[Throwable, Future[VisitationResponse]]

  //todo: lists
  def list(offset: Long, limit: Long): Future[Seq[VisitationResponse]]

  //todo: get by id
  def getById(id: Long): Future[Option[VisitationResponse]]

  def archive(id: Long): Unit

  def populate(entity: Visitation): VisitationResponse
}
