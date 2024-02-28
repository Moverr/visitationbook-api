package controllers.responses

import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.{JsPath, Writes}



case  class HostReponse (
                    id: Long
                    , firstName: String
                    , lastName: String
                  )

object HostReponse {
  implicit val hostResponseWrites:Writes[HostReponse]=(
    (JsPath \ "id").write[Long] and
      (JsPath \ "firstName").write[String] and
      (JsPath \ "lastName").write[String]
    )(unlift(HostReponse.unapply))


}

