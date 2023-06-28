package controllers

import controllers.requests.VisitationsRequest
import controllers.requests.VisitationRequestReads.visitationRequestReads
import controllers.responses.ErrorRespnseWrites.ErrorResponseWrites
import controllers.responses.VisitationResponseWrites._
import controllers.responses.{ErrorRespnse, VisitationResponse}
import play.api.libs.json.Json
import play.api.mvc.{BaseController, ControllerComponents, WrappedRequest}
import services.VisitationServiceImpl

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


@Singleton
class VisitationController @Inject()(
                                      val controllerComponents: ControllerComponents,
                                      val service: VisitationServiceImpl
                                    ) extends BaseController {

  def create() = Action.async { implicit request =>

    try {

      val json = request.body.asJson.get
      val visitationsRequest: VisitationsRequest = json.as[VisitationsRequest]
      service.create(visitationsRequest)
      match {
        case Left(exception) =>
          exception match {
            case e: RuntimeException => Future.successful(BadRequest(Json.toJson(ErrorRespnse(BAD_REQUEST, exception.getMessage))))
            case _ => Future.successful(InternalServerError(Json.toJson(ErrorRespnse(INTERNAL_SERVER_ERROR, "Internal Sever Error"))))
          }

        case Right(result) => result.flatMap(response => Future.successful(Ok(Json.toJson(response))))
      }

    }
    catch {
      case e: Exception => Future.successful(BadRequest(e.getLocalizedMessage))
    }
  }

  def list(limit: Long, offset: Long) = Action.async { implicit request =>
    val response: Future[Seq[VisitationResponse]] = service.list(limit, offset)
    response.flatMap(value => Future.successful(Ok(Json.toJson(value))))
  }


  def getById(id: Long) = Action.async { implicit request =>
    val response: Future[Option[VisitationResponse]] = service.getById(id)
    response.flatMap(
      value =>
        value match {
          case Some(value) => Future.successful(Ok(Json.toJson(value)))
          case None => Future.successful(BadRequest(Json.toJson(ErrorRespnse(BAD_REQUEST, "Item does not exist"))))
        }

    )
  }


  def delete(id: Long) = Action.async { implicit request =>
    val res = service.delete(id);
    res.flatMap {
      case Left(exception) =>
        exception match {
          case e: RuntimeException => Future.successful(BadRequest(Json.toJson(ErrorRespnse(BAD_REQUEST, exception.getMessage))))
          case _ => Future.successful(InternalServerError(Json.toJson(ErrorRespnse(INTERNAL_SERVER_ERROR, "Internal Sever Error"))))
        }
      case Right(value) => Future.successful(Ok)
    }

  }

}
