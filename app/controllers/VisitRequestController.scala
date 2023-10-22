package controllers

import controllers.requests.VisitRequest
import controllers.responses.ErrorRespnseWrites.ErrorResponseWrites
import controllers.responses.RequestVisitResponseWrites._
import controllers.responses.{ErrorRespnse, RequestVisitResponse}
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.RequestVisitationImpl

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


@Singleton
class VisitRequestController @Inject()(
                                             val controllerComponents: ControllerComponents,
                                             val service: RequestVisitationImpl
                                           ) extends BaseController {


  import requests.VisitRequestReads._

  def create(): Action[AnyContent] = Action.async { implicit request =>

    try {

      val json = request.body.asJson.get
      val visitationsRequest: VisitRequest =  json.as[VisitRequest]

      service.create(visitationsRequest)
      match {
        case Left(exception) =>
          exception match {
            case e: RuntimeException => Future.successful(BadRequest(Json.toJson(ErrorRespnse(BAD_REQUEST, exception.getMessage))))
            case _ => Future.successful(InternalServerError(Json.toJson(ErrorRespnse(INTERNAL_SERVER_ERROR, "Internal Sever Error"))))
          }

        case Right(result:Future[RequestVisitResponse]) => result.flatMap(response => Future.successful(Ok(Json.toJson(response))))
      }

    }
    catch {
      case e: Exception => Future.successful(BadRequest(e.getLocalizedMessage))
    }
  }

  def list(limit: Long, offset: Long): Action[AnyContent] = Action.async { implicit request =>
    val response: Future[Seq[RequestVisitResponse]] = service.list( offset,limit)
    response.flatMap(value => Future.successful(Ok(Json.toJson(value))))
  }


  def getById(id: Long): Action[AnyContent] = Action.async { implicit request =>
    val response: Future[Option[RequestVisitResponse]] = service.getById(id)
    response.flatMap {
      case Some(value) => Future.successful(Ok(Json.toJson(value)))
      case None => Future.successful(BadRequest(Json.toJson(ErrorRespnse(BAD_REQUEST, "Item does not exist"))))
    }
  }


  def delete(id: Long): Action[AnyContent] = Action.async { implicit request =>
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
