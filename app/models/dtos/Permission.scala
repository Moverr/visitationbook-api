package models.dtos

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Reads}

case class Permission(
                       resource: String,
                         create: String,
                         read: String,
                         update: String,
                         delete: String
                     )

object Permission {

  implicit val permissionReads: Reads[Permission] = (
    (JsPath \ "resource").read[String] and
      (JsPath \ "create").read[String] and
      (JsPath \ "read").read[String] and
      (JsPath \ "update").read[String] and
      (JsPath \ "delete").read[String]
    ) (Permission.apply _)

}

