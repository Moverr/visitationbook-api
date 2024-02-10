package models.dtos

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Reads}

case class Auth(
                 is_successful: Boolean,
                 message: String,
                 user: User,
                 auth_token: String,
                 refresh_token: String
               )


object Auth {

  implicit val authReads: Reads[Auth] = (
    (JsPath \ "is_successful").read[Boolean] and
      (JsPath \ "message").read[String] and
      (JsPath \ "user").read[User] and
      (JsPath \ "auth_token").read[String] and
      (JsPath \ "refresh_token").read[String]

    )(Auth.apply _)

}
