package controllers


import play.api.libs.json.JsObject
import play.api.libs.ws._

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

class APIClient(ws: WSClient)(implicit ec: ExecutionContext) extends TAPIClient {

  /*
  * Post Request
  * @Param URL
  * @Param headers
  * @Param requestBody
   */

  override def postRequest(url: String, headers: Map[String, String], requestBody: JsObject): Future[WSResponse] = {

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
