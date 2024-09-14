package services

import controllers.requests.AddressRequest
import controllers.responses.AddressResponse
import services.interfaces.AddressService

import java.util.Optional
import javax.inject.Singleton
import scala.concurrent.Future

@Singleton
class AddressServiceImpl extends  AddressService {

  override def create(request: AddressRequest): Future[Either[Throwable, AddressResponse]] = ???

  override def list(offset: Int, limit: Int): Future[Either[Throwable, Seq[AddressResponse]]] = ???

  override def getById(id: Int): Future[Either[Throwable, Optional[AddressResponse]]] = ???

  override def remove(id: Int): Future[Either[Throwable, None.type]] = ???

  override def delete(id: Int): Future[Either[Throwable, None.type]] = ???
}
