package services

import controllers.requests.ProfileRequest
import controllers.responses.ProfileResponse
import models.daos.ProfileDAO
import models.entities.ProfileEntity

import java.sql.Timestamp
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ProfileServiceImpl  @Inject()(profileDAO: ProfileDAO)(implicit executionContext: ExecutionContext)  {



  def create(request: ProfileRequest): Either[Throwable, Future[ProfileResponse]] = {
    val profile: ProfileEntity = ProfileEntity(0L, request.userId, request.firstName, request.otherNames, request.gender, request.profileType, new Timestamp(System.currentTimeMillis()), None,None,None,Some("ACTIVE"))
    val response: Future[ProfileEntity] = profileDAO.create(profile)
    Right(response.map((record: ProfileEntity) => populate(record))(executionContext))
  }

  def list(limit: Long, offset: Long): Future[Seq[ProfileResponse]] = {
    val response:  Future[Seq[ProfileEntity]]  = profileDAO.list(offset, limit)

    response.map {
      record => record.map(item => populate(item))
    }

  }


  def getById(id: Long): Future[Option[ProfileResponse]] = {
    val response: Future[Option[ProfileEntity]] = profileDAO.get(id)
    response.map((value: _root_.scala.Option[_root_.models.entities.ProfileEntity]) => value.map((optionValue: ProfileEntity) => populate(optionValue)))
  }

  def delete(id: Long): Future[Either[Throwable, Boolean]] = {
    val response: Future[Option[ProfileEntity]] = profileDAO.get(id)
    response.map({
      case Some(value: ProfileEntity) =>
        profileDAO.delete(value.id)
        Right(true)

      case None => Left(new RuntimeException("Record does not exist"))
    })

  }


  def archive(id: Long): Future[Either[Throwable, Boolean]] = {
    val response: Future[Option[ProfileEntity]] = profileDAO.get(id)
    response.map({
      case Some(value: ProfileEntity) =>
        profileDAO.archive(value.id)
        Right(true)

      case None => Left(new RuntimeException("Record does not exist"))
    })
  }


  def populate(entity: ProfileEntity): ProfileResponse =
    ProfileResponse(
      entity.id,
      entity.firstname.getOrElse("N/A"),
      entity.othernames.getOrElse("N/A")
    )



  def populate(entity: Option[ProfileEntity]): Option[ProfileResponse] =
    entity match {
      case Some(value) => Some(populate(value))
      case None => None
    }
}
