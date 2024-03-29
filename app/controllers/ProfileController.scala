package controllers

import com.google.inject.Singleton
import controllers.requests.ProfileRequest
import controllers.responses.{ErrorResponse, ProfileResponse}
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.ProfileServiceImpl

import javax.inject.Inject
import scala.annotation.unused
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


@Singleton
class ProfileController @Inject()(val controllerComponents: ControllerComponents)(val serviceImpl: ProfileServiceImpl)
  extends BaseController {


  def create: Action[AnyContent] = Action.async { implicit request =>

    println("Blessed day")
    val json = request.body.asJson.get
    val profileRequest: ProfileRequest = json.as[ProfileRequest]

    val result: Either[Throwable, Future[ProfileResponse]] = serviceImpl.create(profileRequest)

    result match {
      case Left(exception) =>
        exception match {
          case e: RuntimeException => Future.successful(BadRequest(Json.toJson(ErrorResponse(BAD_REQUEST, exception.getMessage))))
          case _ => Future.successful(InternalServerError(Json.toJson(ErrorResponse(INTERNAL_SERVER_ERROR, "Internal Sever Error"))))
        }

      case Right(result) => result.flatMap(response => Future.successful(Ok(Json.toJson(response))))

    }

  }

  //todo: fetch all
  def list(limit: Long, offset: Long,profileType:String): Action[AnyContent] = Action.async { implicit request =>
    val response: Future[Seq[ProfileResponse]] = serviceImpl.list(limit, offset,profileType)
    response.flatMap(value => Future.successful(
      Ok(Json.toJson(value))
    ))
  }

  def getById(id: Long): Action[AnyContent] = Action.async { implicit request =>
    val response: Future[Option[ProfileResponse]] = serviceImpl.getById(id)
    response.flatMap {
      case Some(value) => Future.successful(Ok(Json.toJson(value)))
      case None => Future.successful(BadRequest(Json.toJson(ErrorResponse(BAD_REQUEST, "Item does not exist"))))
    }
  }



  @unused
  def delete(id: Long): Action[AnyContent] = Action.async { implicit request =>
    val res = serviceImpl.delete(id);
    res.flatMap {
      case Left(exception) =>
        exception match {
          case e: RuntimeException => Future.successful(BadRequest(Json.toJson(ErrorResponse(BAD_REQUEST, exception.getMessage))))
          case _ => Future.successful(InternalServerError(Json.toJson(ErrorResponse(INTERNAL_SERVER_ERROR, "Internal Sever Error"))))
        }
      case Right(_) => Future.successful(Ok)
    }

  }


  def archive(id: Long): Action[AnyContent] = Action.async { implicit request =>
    val res = serviceImpl.archive(id);
    res.flatMap {
      case Left(exception) =>
        exception match {
          case e: RuntimeException => Future.successful(BadRequest(Json.toJson(ErrorResponse(BAD_REQUEST, exception.getMessage))))
          case _ => Future.successful(InternalServerError(Json.toJson(ErrorResponse(INTERNAL_SERVER_ERROR, "Internal Sever Error"))))
        }
      case Right(_) => Future.successful(Ok)
    }

  }


  def validate: Action[AnyContent] = Action.async { implicit request =>
    val json = request.body.asJson.get
    val profileRequest: ProfileRequest = json.as[ProfileRequest]

    val result: Future[Option[ProfileResponse]] = serviceImpl.validate(profileRequest)

    result.flatMap {
      case Some(response) => Future.successful(Ok(Json.toJson(response)))
      case None => Future.successful(BadRequest(Json.toJson(ErrorResponse(BAD_REQUEST, "Profile does not exist"))))
    }

  }


}
