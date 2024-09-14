package services.actors

import akka.actor.{Actor, Props}
import akka.pattern.pipe
import controllers.requests.AddressRequest
import controllers.responses.AddressResponse
import models.Address
import models.daos.AddressDAO
import models.enums.StatusEnum

import java.sql.Timestamp
import javax.inject.Inject
import scala.concurrent.ExecutionContext






object AddressesActor  {

   def props(addressDAO: AddressDAO)(     executionContext: ExecutionContext): Props =
    Props(new AddressesActor(addressDAO)(executionContext))

  // Messages
  case class GetAddresses(offset: Long, limit: Long)

  case class GetAddress(id: Long)

  case class   CreateAddress(request: AddressRequest)

  case class DeleteAddress(id: Long)

  //Responses
  case class AddressList(addressResponses: Seq[AddressResponse])

  case class AddressCreated(addressResponse: AddressResponse)

  case class AddressDeleted(success: Boolean)



}



class AddressesActor @Inject()(addressDAO: AddressDAO)(implicit val executionContext: ExecutionContext) extends Actor {

  import AddressesActor._


  override def receive: Receive = {

    case GetAddresses(offset: Long, limit: Long) =>
      addressDAO.list(offset, limit)
        .map(addresses => addresses.map(response)).map(AddressList).pipeTo(sender())

    case GetAddress(id: Long) =>
      addressDAO.findById(id)
        .map(_.map(response)).map(_.map(AddressCreated))
        .pipeTo(sender())


    case CreateAddress(request: AddressRequest) =>
      addressDAO.create(populate(request))
        .map( response)
        .map(AddressCreated)
        .pipeTo(sender())


    case DeleteAddress(id:Long) =>
      addressDAO.delete(id)
        .map {
          case true => AddressDeleted(true)
          case _ => AddressDeleted(false)
        }.pipeTo(sender())


  }


  private def populate(request: AddressRequest): Address =
    Address(
      0L
      , request.street
      , request.city
      , request.state
      , request.postalCode
      , request.country
      , request.latitude.map(_.toDouble)
      , request.longitude.map(_.toDouble)
      , new Timestamp(System.currentTimeMillis())
      , Some(new Timestamp(System.currentTimeMillis()))
      , None
      , None
      , Some(StatusEnum.ACTIVE.toString)
    )




  private def response(address: Address): AddressResponse =
    AddressResponse(address.id, address.street, address.city, address.state, address.postalCode, address.country, address.latitude, address.longitude)

}
