package controllers

import play.api.libs.json.JsObject
import play.api.libs.ws.WSResponse

import scala.concurrent.Future

trait TAPIClient {

  /*
    * Post Request
    * @Param URL
    * @Param headers
    * @Param requestBody
     */
  def postRequest(url: String, headers: Map[String, String], requestBody: JsObject): Future[WSResponse]

  /*
  * Get Request
  * @Param URL
  * @Param headers
   */
  def getRequest(url: String, headers: Map[String, String]): Future[WSResponse]
}
