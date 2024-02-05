package filters


import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import controllers.APIClient
import play.api.libs.json.{JsObject, Json}
import play.api.libs.ws.WSResponse
import play.api.libs.ws.ahc.AhcWSClient
import play.api.mvc.RequestHeader

import scala.concurrent.{ExecutionContext, Future}

class UserManager {
  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global

  def isAuthenticated(requestHeader: RequestHeader): Boolean = {

    val authorization: String = "Authorization"
    val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()(system)

    val client: APIClient =  new  APIClient(ws = AhcWSClient.apply() )


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

      val myMap: Map[String, String] = Map(
        "username" -> username,
        "password" -> password
      )

      val jsonObject: JsObject = Json.toJson(myMap).as[JsObject]

      println(jsonObject)



    println("Full jsObject ")
    println(jsObject)
    val headers: Map[String, String] = Map()+("Content-Type"->"application/json")
    val apiResponse: Future[WSResponse] = client.postRequest(fullUrl, headers, jsonObject)


    apiResponse.map { response =>
      val status: Int = response.status
      println(s" ... Status :: -----  : ${status}")
      val body: String = response.body
      println(s" ... Body :: -----  : ${body}")
    }.recover {
      case ex: Throwable => println(s"An error occured : ${ex.getMessage}")
    }


    println(requestHeader.headers)
    println(" reached the Authentication mechanism ..")

    true
  }


}
