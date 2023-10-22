package controllers

import com.google.inject.Singleton
import controllers.requests.ProfileRequest
import controllers.responses.GuestResponseWrites.guestWrites
import controllers.responses.{ErrorRespnse, ProfileResponse}
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.ProfileServiceImpl

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


@Singleton
class ProfileController @Inject()(val controllerComponents: ControllerComponents)(val serviceImpl: ProfileServiceImpl)
  extends BaseController {


  import requests.ProfileRequestReads._
  import responses.ErrorRespnseWrites._

  def create: Action[AnyContent] = Action.async { implicit request =>
    val json = request.body.asJson.get
    val profileRequest: ProfileRequest = json.as[ProfileRequest]

    val result: Either[Throwable, Future[ProfileResponse]] = serviceImpl.create(profileRequest)

    result match {
      case Left(exception) =>
        exception match {
          case e: RuntimeException => Future.successful(BadRequest(Json.toJson(ErrorRespnse(BAD_REQUEST, exception.getMessage))))
          case _ => Future.successful(InternalServerError(Json.toJson(ErrorRespnse(INTERNAL_SERVER_ERROR, "Internal Sever Error"))))
        }

      case Right(result) => result.flatMap(response => Future.successful(Ok(Json.toJson(response))))

    }

  }

  // val x :(Int,Int)=>Int=(a,b)=>a+b

}
