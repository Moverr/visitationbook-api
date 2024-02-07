package filters


import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import controllers.APIClient
import models.dtos.{Auth, AuthReads._}
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{JsObject, JsResult, Json}
import play.api.libs.ws.WSResponse
import play.api.libs.ws.ahc.AhcWSClient

import scala.concurrent.{ExecutionContext, Future}


class UserManager {
  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global



  def isAuthenticated(bearerToken: String): Boolean   = {

    val bearer = bearerToken.split(" ") match {
      case Array(_, token) => s"Bearer $token"
      case _ => throw new IllegalArgumentException("Invalid bearer token format")
    }

    println(s" token result $bearer ")
    val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()(system)

    val client: APIClient = new APIClient(ws = AhcWSClient.apply())

    val basicUrl = "http://52.207.255.31:8082";
    val fullUrl = s"$basicUrl/v1/auth/validate"


    val jsonObject: JsObject = Json.toJson(Map[String, String]()).as[JsObject]

    val headers: Map[String, String] = Map("Authorization" -> bearer)

    println(s" Header  result $headers ")
    val apiResponse: Future[WSResponse] = client.postRequest(fullUrl, headers, jsonObject)



    val result = apiResponse.map { response =>
      val status: Int = response.status
      println(s" ... Status :: -----  : ${status}")
      val body: String = response.body
      println(s" ... Body :: -----  : ${body}")

    //  val jsonBody: JsValue = Json.toJson(response.body).as[JsObject]
      println(s" ... Json Body :: -----  : $body")
      val json = Json.parse(body)
      val userResult: JsResult[Auth] = json.validate[Auth]

      true
    }.recover {
      case ex: Throwable => println(s"An error occured : ${ex.getMessage}")
        false
    }


    //println(" reached the Authentication mechanism ..")

    true

  }


}
