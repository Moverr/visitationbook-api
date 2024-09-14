package services.interfaces

import controllers.requests.AddressRequest
import controllers.responses.AddressResponse

import java.util.Optional
import scala.concurrent.Future
import scala.util.Try

trait AddressService {

  //todo: create
  def create(request:AddressRequest):Future[Either[Throwable,AddressResponse]]
  //todo: list
  def list(offset:Int=0,limit:Int=5):Future[Either[Throwable,Seq[AddressResponse]]]
  //todo: get by id
  def getById(id:Int):Future[Either[Throwable,Optional[AddressResponse]]]
  //todo: remove
  def remove(id:Int):Future[Either[Throwable,None.type ]]
  //todo: delete
  def delete(id:Int):Future[Either[Throwable,None.type ]]
}
