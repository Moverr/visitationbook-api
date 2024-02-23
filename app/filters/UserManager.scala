package filters

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import controllers.APIClient
import models.dtos.Auth
import play.api.Logger
import play.api.libs.json.{JsObject, JsResult, Json}
import play.api.libs.ws.{WSClient, WSResponse}
import play.api.libs.ws.ahc.AhcWSClient

import scala.concurrent.{ExecutionContext, Future}

trait UserManager {
  def isAuthenticated(bearerToken: String): Future[Boolean]
}

class DefaultUserManager(wsClient: WSClient)(implicit ec: ExecutionContext, system: ActorSystem, materializer: ActorMaterializer) extends UserManager {

  private val log = Logger(getClass)

  override def isAuthenticated(bearerToken: String): Future[Boolean] = {
    log.info(s"Token provided: $bearerToken")

    val bearer: String = validateToken(bearerToken)
    val client: APIClient = new APIClient(wsClient)

    val basicUrl = "http://52.207.255.31:8082"
    val fullUrl = s"$basicUrl/v1/auth/validate"

    val jsonBody: JsObject = Json.obj()
    val headers: Map[String, String] = Map("Authorization" -> bearer)

    val apiResponse: Future[WSResponse] = client.postRequest(fullUrl, headers, jsonBody)

    apiResponse.map { response =>
      response.status match {
        case 401 =>
          log.warn("Unauthorized request")
          false
        case _ =>
          val body: String = response.body
          val responseBodyJson = Json.parse(body)
          val userResult: JsResult[Auth] = responseBodyJson.validate[Auth]
          userResult.fold(
            errors => {
              log.error(s"Failed to validate user: ${errors.mkString(", ")}")
              false
            },
            auth => {
              log.info(s"User ${auth.user.userId} authenticated successfully")
              true
            }
          )
      }
    }.recover {
      case ex: Throwable =>
        log.error(s"An error occurred: ${ex.getMessage}")
        false
    }
  }

  private def validateToken(bearerToken: String): String = {
    bearerToken.split(" ") match {
      case Array(_, token) => s"Bearer $token"
      case _ => throw new IllegalArgumentException("Invalid bearer token format")
    }
  }
}
