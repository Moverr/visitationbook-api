package filters

import akka.actor.TypedActor.dispatcher
import akka.stream.Materializer
import play.libs.ws._

import scala.concurrent.ExecutionContext
import play.api.mvc.RequestHeader
import services.ClientRequestHeader
import cats.implicits._
import controllers.{APIClient, TAPIClient}
import play.api.Play.materializer
import play.api.libs.json.{JsObject, Json}
import play.api.libs.ws.ahc.AhcWSClient
import play.api.libs.ws.{WSClient, WSResponse}

import javax.inject.Inject
import scala.concurrent.Future
import scala.concurrent.impl.Promise
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json.{JsObject, Json}
import play.api.libs.streams.Execution.Implicits.trampoline
import play.api.libs.ws.WSClient
import play.api.libs.ws.ahc.AhcWSClient

import scala.concurrent.ExecutionContext.Implicits.global


class UserManager @Inject()(implicit cc: ExecutionContext, val mat: Materializer) {

  def isAuthenticated(requestHeader: RequestHeader): Boolean = {

    val authorization: String = "Authorization"
    val wsClient: WSClient = AhcWSClient()
    val client: APIClient = new APIClient(wsClient)(cc)


    val authHeader = requestHeader.headers.toMap
    // val clientRequestHeaders = ClientRequestHeader(authHeader)
    println(" reached the Authentication mechanism ..")
    //todo: get authentication
    // val authenticationHeader:String=  clientRequestHeaders.getHeaderString(authorization).getOrElse("EMPTY")
    //todo: validate token :
    val basicUrl = "http://52.207.255.31:8082";
    val fullUrl = s"$basicUrl/v1/auth"
    val username = "superadmin"
    val password = "password"
    println(" reached the Authentication mechanism ..")

    val jsObject: JsObject = Json.obj {
      "username" -> username
      "password" -> password
    }

    val headers: Map[String, String] = Map()
    val apiResponse: Future[WSResponse] = client.postRequest(fullUrl, headers, jsObject)


    /*
    apiResponse.map { response =>
      val status: Int = response.status
      println(s" ... Status :: -----  : ${status}")
      val body: String = response.body
      println(s" ... Body :: -----  : ${body}")
    }.recover {
      case ex: Throwable => println(s"An error occured : ${ex.getMessage}")
    }
    */

    println(requestHeader.headers)
    println(" reached the Authentication mechanism ..")

    true
  }


}
