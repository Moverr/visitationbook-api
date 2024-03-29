package controllers.responses

import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.{JsPath, Writes}

case class ProfileResponse(
                            id:Long
                            , firstName:String
                            , otherNames:String
                        )

object ProfileResponse {

  implicit  val profileResponseWrites:Writes[ProfileResponse]=(
    (JsPath \ "id").write[Long] and
      (JsPath \ "first_name").write[String] and
      (JsPath \ "other_names").write[String]

    )(unlift(ProfileResponse.unapply))
}


