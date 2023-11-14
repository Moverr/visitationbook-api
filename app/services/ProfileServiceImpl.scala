package services

import controllers.requests.ProfileRequest
import controllers.responses.ProfileResponse
import models.daos.ProfileDAO
import models.entities.ProfileEntity

import java.sql.Timestamp
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ProfileServiceImpl  @Inject()(profileDAO: ProfileDAO)(implicit executionContext: ExecutionContext)  {


  def validate(request: ProfileRequest):   Future[Option[ProfileResponse]] = {
    val response: Future[Option[ProfileEntity]] = profileDAO.findProfile(request.firstName.getOrElse(""),request.otherNames.getOrElse(""),request.profileType.getOrElse(""))
     val _response = response.flatMap {
       case Some(value) => Future.successful(Some(populate(value)))
       case None => Future.successful(None)
     }
    _response
  }

  def create(request: ProfileRequest): Either[Throwable, Future[ProfileResponse]] = {

    val response: Future[Option[ProfileResponse]] = validate(request)
    val resultFuture: Future[ProfileResponse] =  response.flatMap {
      case Some(value) => Future.successful(value)
      case None =>
        val profile: ProfileEntity = ProfileEntity(
          0L, request.userId, request.firstName, request.otherNames, request.gender,
          request.profileType, new Timestamp(System.currentTimeMillis()), None, None, None, Some("ACTIVE")
        )
        profileDAO.create(profile).map(populate)
    }
    Right(resultFuture)

  }

  def list(limit: Long, offset: Long,profileType:String): Future[Seq[ProfileResponse]] = {
    val response:  Future[Seq[ProfileEntity]]  = profileDAO.list(offset, limit,profileType)
    response.map {
      record => record.map(populate)
    }
  }


  def getById(id: Long): Future[Option[ProfileResponse]] = {
    val response: Future[Option[ProfileEntity]] = profileDAO.get(id)
    response.map((value: _root_.scala.Option[_root_.models.entities.ProfileEntity]) => value.map(populate))
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
