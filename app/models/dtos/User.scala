package models.dtos

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Reads}

case class User(
                 userId: Long,
                 username: String,
                 isActive: Boolean,
                 status: Option[String],
                 roles: Seq[Role],
                 projects: Option[String]
               )

object User {
  implicit val userReads: Reads[User] = (
    (JsPath \ "user_id").read[Long] and
      (JsPath \ "username").read[String] and
      (JsPath \ "is_active").read[Boolean] and
      (JsPath \ "status").readNullable[String] and
      (JsPath \ "roles").read[Seq[Role]] and
      (JsPath \ "projects").readNullable[String]
    )(User.apply _)
}
