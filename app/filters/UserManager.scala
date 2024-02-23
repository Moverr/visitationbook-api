package filters


import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import controllers.APIClient
import models.dtos.Auth
import play.api.Logger
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{JsObject, JsResult, Json}
import play.api.libs.ws.WSResponse
import play.api.libs.ws.ahc.AhcWSClient

import scala.concurrent.{ExecutionContext, Future}

trait TUserManager {
  def isAuthenticated(bearerToken: String): Future[Boolean]
}

private class UserManager extends TUserManager {
  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global

  implicit  val system: ActorSystem = ActorSystem("my-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  private lazy val log = Logger(this.getClass).logger


  override def isAuthenticated(bearerToken: String): Future[Boolean]   = {
    log.info(s"  token  provided :    ${bearerToken} ")

    val bearer: String = validateToken(bearerToken)


    val client: APIClient = new APIClient(ws = AhcWSClient.apply())

    val basicUrl = "http://52.207.255.31:8082";
    val fullUrl = s"$basicUrl/v1/auth/validate"


    val jsonBody: JsObject = Json.toJson(Map[String, String]()).as[JsObject]
    val headers: Map[String, String] = Map("Authorization" -> bearer)

    val apiResponse: Future[WSResponse] = client.postRequest(fullUrl, headers, jsonBody)



    val result:Future[Boolean] = apiResponse.map { response =>

      val status: Boolean =  response.status match {
        case 401 => false
        case _ =>

          val body: String = response.body
          val responseBodyJson = Json.parse(body)
          val userResult: JsResult[Auth] = responseBodyJson.validate[Auth]
          println(s"${userResult.get.user.userId}")
          true
      }


      status
    }.recover {
      case ex: Throwable => println(s"An error occurred : ${ex.getMessage}")
        false
    }

    result
  }


  private def validateToken(bearerToken: String): String = {
    val bearer = bearerToken.split(" ") match {
      case Array(_, token) => s"Bearer $token"
      case _ => throw new IllegalArgumentException("Invalid bearer token format")
    }
    bearer
  }

}
