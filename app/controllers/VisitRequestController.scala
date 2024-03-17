package controllers

import controllers.requests.{VisitRequest, VisitationRequest}
import controllers.responses.{ErrorResponse, RequestVisitResponse}
import models.dtos.Auth
import play.api.Logger
import play.api.cache.SyncCacheApi


import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.RequestVisitationImpl

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import play.api.libs.json._

@Singleton
class VisitRequestController @Inject()(
                                        val controllerComponents: ControllerComponents
                                        , val service: RequestVisitationImpl
                                        , val cache: SyncCacheApi
                                      ) extends BaseController {

  private lazy val log = Logger(getClass).logger

  def create(): Action[AnyContent] = Action.async { implicit request =>


    try {
      val auths: Option[Auth] = cache.get("auth")

      auths match {
        case Some(auth) =>
          log.info(s" Womder match Provided Auth --- ${auth.user.userId} ")

          val json = request.body.asJson.get
          val visitationsRequest: VisitRequest = json.as[VisitRequest]
          service.create(auth, visitationsRequest)
          match {
            case Left(exception) =>
              val errorResponse = exception match {
                case e: RuntimeException => ErrorResponse(BAD_REQUEST, e.getMessage)
                case _ => ErrorResponse(INTERNAL_SERVER_ERROR, "Internal Sever Error")
              }
              Future.successful(BadRequest(Json.toJson(errorResponse)))
            case Right(result: Future[RequestVisitResponse]) => result.flatMap(response => Future.successful(Ok(Json.toJson(response))))
          }


        case None => Future.successful(Unauthorized(" User is not authorized to access this endpoint "))
      }


    }
    catch {
      case e: Exception => Future.successful(BadRequest(e.getLocalizedMessage))
    }
  }

  def list(limit: Long, offset: Long): Action[AnyContent] = Action.async { implicit request =>

    val auths: Option[Auth] = cache.get("auth")
    auths match {
      case Some(auth) => service.list(auth, offset, limit) match {
        case Left(e: Exception) => Future.successful(BadRequest(e.getLocalizedMessage))
        case Right(response: Future[Seq[RequestVisitResponse]]) => response.flatMap(value => Future.successful(Ok(Json.toJson(value))))
      }
      case None => Future.successful(Unauthorized(" User is not authorized to access this endpoint "))
    }
  }


  def getById(id: Long): Action[AnyContent] = Action.async { implicit request =>
    val response: Future[Option[RequestVisitResponse]] = service.getById(id)
    response.flatMap {
      case Some(value) => Future.successful(Ok(Json.toJson(value)))
      case None => Future.successful(BadRequest(Json.toJson(ErrorResponse(BAD_REQUEST, "Item does not exist"))))
    }
  }


  def delete(id: Long): Action[AnyContent] = Action.async { implicit request =>
    val res = service.delete(id)
    res.flatMap {
      case Left(exception) =>
        exception match {
          case e: RuntimeException => Future.successful(BadRequest(Json.toJson(ErrorResponse(BAD_REQUEST, exception.getMessage))))
          case _ => Future.successful(InternalServerError(Json.toJson(ErrorResponse(INTERNAL_SERVER_ERROR, "Internal Sever Error"))))
        }
      case Right(_) => Future.successful(Ok)
    }

  }

}
