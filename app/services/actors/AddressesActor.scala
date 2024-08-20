package services.actors

import akka.actor.Actor
import akka.actor.typed.Props
import controllers.responses.VisitationResponse
import models.VisitationRequest

object AddressesActor{
  //def props:Props[AddressesActor] = Props[AddressesActor]

  // Messages
  case class GetAddresses(offset:Long,limit:Long)
  case class  GetAddress(id:Long)
  case class CreateAddress(request:VisitationRequest)
  case class DeleteAddress(id:Long)

  //Responses
  case class AddressList(visitations: Seq[VisitationResponse])
  case class AddressCreated(visitation: VisitationResponse)
  case class AddressDeleted(success: Boolean)

}

class AddressesActor extends Actor {

  import  AddressesActor._
  override def receive: Receive = {

    case GetAddresses => ???
    case GetAddress => ???
    case CreateAddress => ???
    case DeleteAddress => ???

  }
}
