package controllers

import controllers.requests.VisitationRequest
import controllers.requests.VisitationRequestReads.visitationRequestReads
import play.api.mvc.{BaseController, ControllerComponents, Result}

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future

@Singleton
class VisitationController @Inject()(val controllerComponents: ControllerComponents) extends BaseController{

  def create() = Action.async  { implicit  request =>

    val json = request.body.asJson.get
    // clelan way of getting info from thhe clients
    val record:VisitationRequest = json.as[VisitationRequest]



    ???

  }

  def list(): Unit ={

  }

  def archive(): Unit ={

  }

}
