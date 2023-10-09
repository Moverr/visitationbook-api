package controllers.responses

import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.{JsPath, Writes}

object HostResponseWrites {

  implicit  val hostResponseWrites:Writes[HostReponse]=(
    (JsPath \ "id").write[Option[Long]] and
      (JsPath \ "first_name").write[Option[String]] and
      (JsPath \ "last_name").write[Option[String]]

  )(unlift(HostReponse.unapply))
}
