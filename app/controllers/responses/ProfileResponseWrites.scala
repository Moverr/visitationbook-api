package controllers.responses

import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.{JsPath, Writes}

object ProfileResponseWrites {

  implicit  val profileResponseWrites:Writes[ProfileResponse]=(
    (JsPath \ "id").write[Long] and
      (JsPath \ "first_name").write[String] and
      (JsPath \ "last_name").write[String]

  )(unlift(ProfileResponse.unapply))
}
