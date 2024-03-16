package controllers


import play.api.libs.json.JsObject
import play.api.libs.ws._

import javax.inject.Inject
import scala.concurrent.Future
import scala.util.Try

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


   class APIClient @Inject()(ws: WSClient) extends TAPIClient {

  /*
  * Post Request
  * @Param URL
  * @Param headers
  * @Param requestBody
   */

  override def postRequest(url: String, headers: Map[String, String], requestBody: JsObject): Future[WSResponse] = {

    println("requestBody")
    println(requestBody)
    val request: WSRequest = Try(ws.url(url)).getOrElse(throw new IllegalArgumentException("Invalid URL"))

    val requestWithHeaders: WSRequest = headers.foldLeft(request) {
      case (req, (name, value)) => req.addHttpHeaders(name -> value)
    }

    requestWithHeaders.post(requestBody)
  }


  /*
  * Get Request
  * @Param URL
  * @Param headers
   */
  override def getRequest(url: String, headers: Map[String, String]): Future[WSResponse] = {

    val request: WSRequest = Try(ws.url(url)).getOrElse(throw new IllegalArgumentException("Invalid URL"))

    def populateRequestHeaders: WSRequest = {
      val requestWithHeaders: WSRequest = headers.foldLeft(request) {
        case (req, (name, value)) => req.addHttpHeaders(name -> value)
      }
      requestWithHeaders
    }

    val requestWithHeaders: WSRequest = populateRequestHeaders

    requestWithHeaders.get()
  }


}
