package controllers

import play.api.mvc.{BaseController, ControllerComponents}
import services.VisitationServiceImpl

import javax.inject.Inject

@Singleton
class RequestVisitationController @Inject()(
                                             val controllerComponents: ControllerComponents,
                                             val service: VisitationServiceImpl
                                           ) extends BaseController {

}
