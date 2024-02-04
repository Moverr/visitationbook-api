package filters
import play.libs.ws._
import scala.concurrent.ExecutionContext



import play.api.mvc.RequestHeader
import services.ClientRequestHeader
import cats.implicits._

import play.api.libs.ws.{WSClient, WSResponse}

import javax.inject.Inject
import scala.concurrent.Future
 class UserManager @Inject() (   ws: WSClient)(implicit ec: ExecutionContext) {
  protected val authorization: String = "Authorization"

  def isAuthenticated(requestHeader: RequestHeader): Boolean = {

    val authHeader = requestHeader.headers.toMap
   // val clientRequestHeaders = ClientRequestHeader(authHeader)
    println(" reached the Authentication mechanism ..")
   /* //todo: get authentication
   val authenticationHeader:String=  clientRequestHeaders.getHeaderString(authorization).getOrElse("EMPTY")
    //todo: validate token :
    val basicUrl = "http://52.207.255.31:8082";
    val fullUrl = s"$basicUrl/v1/auth"
    val username = "superadmin"
    val password = "password"
    println(" reached the Authentication mechanism ..")
    val apiResponse: Future[WSResponse] = ws.url(fullUrl).get()

    println(requestHeader.headers)
    println(" reached the Authentication mechanism ..")
    */
    true
  }



}
