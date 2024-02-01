package filters

import play.api.mvc.RequestHeader
import services.ClientRequestHeader

case class UserManager() {
  def isAuthenticated(requestHeader: RequestHeader): Boolean = {

    val authHeader = requestHeader.headers;
    println(requestHeader.headers)
    println(" reached the Authentication mechanism ..")
    true
  }

}
