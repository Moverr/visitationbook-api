package filters

import play.api.mvc.RequestHeader

case class UserManager() {
  def isAuthenticated(requestHeader: RequestHeader): Boolean = {
    true
  }

}
