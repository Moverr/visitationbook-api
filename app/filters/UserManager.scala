package filters


import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import controllers.APIClient
import jdk.jshell.spi.ExecutionControl.InternalException
import models.dtos.Auth
import play.api.{Configuration, Logger}
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{JsObject, JsResult, Json}
import play.api.libs.ws.WSResponse
import play.api.libs.ws.ahc.AhcWSClient
import utils.Urls

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

trait TUserManager {
  def isAuthenticated(bearerToken: String): Future[Either[Throwable, Auth]]
}

private class UserManager @Inject()(val urls: Urls) extends TUserManager {
  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global

  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  private lazy val log = Logger(this.getClass).logger


  override def isAuthenticated(bearerToken: String): Future[Either[Throwable, Auth]] = {


    log.info(s"  token  provided :    ${bearerToken} ")
    val bearer: String = validateToken(bearerToken)
    val client: APIClient = new APIClient(ws = AhcWSClient.apply())
    val emptyJsonBody: JsObject = Json.toJson(Map[String, String]()).as[JsObject]
    val headers: Map[String, String] = Map("Authorization" -> bearer)


    val apiResponse: Future[WSResponse] = client.postRequest(urls.validationUrl, headers, emptyJsonBody)

    val response: Future[Either[Throwable, Auth]] = apiResponse.map { response =>

      val authResponse: Either[Throwable, Auth] = response.status match {
        case 401 => Left(new IllegalArgumentException("User is not authorized"))
        case _ =>

          val responseBodyJson = Json.parse(response.body)
          log.info(s"  Response from the auth service :    ${responseBodyJson} ")

          val auth: Auth = responseBodyJson.validate[Auth].get

          //todo: validate that user has rights to the next controller

          log.info(s"${auth}")
          Right(auth)
      }

      authResponse
    }.recover {
      case ex: Throwable =>
        log.debug(s"An error occurred : ${ex.getMessage}")
        Left(new InternalException(ex.getMessage) )
    }

    response
  }


  private def validateToken(bearerToken: String): String = {
    val bearer = bearerToken.split(" ") match {
      case Array(_, token) => s"Bearer $token"
      case _ => throw new IllegalArgumentException("Invalid bearer token format")
    }
    bearer
  }

}
