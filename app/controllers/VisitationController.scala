package controllers

import controllers.requests.VisitationRequest
import controllers.requests.VisitationRequestReads.visitationRequestReads
import controllers.responses.ErrorRespnseWrites.ErrorRespnseWrites
import controllers.responses.{ErrorRespnse, VisitationResponse}
import controllers.responses.VisitationResponseWrites._
import play.api.libs.json.Json
import play.api.mvc.{BaseController, ControllerComponents}
import services.{TVisitationService, VisitationService}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


@Singleton
class VisitationController @Inject()(
                                      val controllerComponents: ControllerComponents,
                                      val service: VisitationService
                                    ) extends BaseController {

  def create() = Action.async { implicit request =>

    val json = request.body.asJson.get
    val record: VisitationRequest = json.as[VisitationRequest]
    service.create(record)
      .flatMap(response => Future.successful(Ok(Json.toJson(response))))
  }

  def list(limit:Long  = 20, offset:Long= 0)  =Action.async { implicit request =>
    val response: Future[Seq[VisitationResponse]] = service.list(limit, offset)
    response.flatMap(value=>Future.successful(Ok(Json.toJson(value))))
  }


  def getById(id: Long ) = Action.async { implicit request =>
    val response: Future[Option[VisitationResponse]] = service.getById(id)
    response.flatMap(
      value =>
        value match {
          case Some(value) => Future.successful(Ok(Json.toJson(value)))
          case None => Future.successful(BadRequest(Json.toJson(ErrorRespnse("ses","Item Not Fund"))))
        }

    )
  }


  def archive(): Unit = {

  }

}
