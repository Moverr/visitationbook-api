package controllers

import akka.actor.ActorRef
import akka.util.Timeout
import controllers.requests.AddressRequest
import controllers.responses.{AddressResponse, ErrorResponse}
import play.api.libs.json.{JsError, JsSuccess, Json}
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.actors.AddressesActor.{AddressCreated, AddressList, CreateAddress, GetAddresses}
import shapeless.Lazy.apply

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future.never.recover
import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AddressController @Inject()
(val controllerComponents: ControllerComponents, addressesActor: ActorRef)(implicit val ec: ExecutionContext) extends BaseController {

  implicit val timeout: Timeout = 5.seconds


  def create(): Action[AnyContent] = Action.async { implicit request =>
    request.body.asJson match {
      case Some(json) =>
        json.validate[AddressRequest] match {
          case JsSuccess(addressRequest, _) =>
            // information : ask the actor and wait for response
            addressesActor ! CreateAddress(addressRequest)
              .map {
                case AddressCreated(addressResponse) => Future.successful(Ok(Json.toJson(addressResponse)))
                case _ => InternalServerError(Json.toJson(ErrorResponse(500, "Failed to create response")))
              }
            recover {
              case ex: Exception =>
                InternalServerError(Json.toJson(ErrorResponse(500, s"An error occurred: ${ex.getMessage}")))
            }


          case JsError(errors) => Future.successful(BadRequest(Json.obj("message" -> "Invalid JSON", "details" -> JsError.toJson(errors))))
        }


      case None => Future.successful(BadRequest(Json.obj("message" -> "Expected JSON request body")))
    }

  }


  def list(offset: Long, limit: Long): Action[AnyContent] = Action.async { implicit request =>
    addressesActor ! GetAddresses(offset, limit)
      .map {
        case AddressList(addressResponses: Seq[AddressResponse]) => Ok(Json.toJson(addressResponses))
        case _ => NotFound(Json.toJson(ErrorResponse(500, "Failed to create response")))
      }
    recover {
      case ex: Exception => InternalServerError(Json.toJson(ErrorResponse(500, ex.getMessage))
    }

  }


  def get(id: Long): Action[AnyContent] = Action.async { implicit request =>
    addressesActor ! get(id)
      .map{
        result =>
          result match {
            case
          }
      }
    ???
  }


  def delete(id: Long): Action[AnyContent] = Action.async { implicit request =>
    ???
  }

}
