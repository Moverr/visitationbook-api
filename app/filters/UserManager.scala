package filters

import play.api.mvc.RequestHeader

case class UserManager() {
  def isAuthenticated(requestHeader: RequestHeader): Boolean = {

    println(requestHeader)
    println(" reached the Authentication mechanism ..")
    true
  }

}
