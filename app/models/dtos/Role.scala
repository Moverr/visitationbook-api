package models.dtos

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Reads}

case class Role( name: String,  permissions: Seq[Permission] )

object Role {

  implicit val roleReads: Reads[Role] = (
    (JsPath \ "name").read[String] and (JsPath \ "permissions").read[Seq[Permission]]
    )(Role.apply _)

}
