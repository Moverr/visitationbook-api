package controllers.requests

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Reads}

object ProfileRequestReads {

  implicit  val profileRequestReads:Reads[ProfileRequest]=(
    (JsPath \ "user_id").readNullable[Long] and
      (JsPath \ "first_name").readNullable[String] and
      (JsPath \ "last_name").readNullable[String] and
      (JsPath \ "gender").readNullable[String] and
      (JsPath \ "profile_type").readNullable[String]
  )(ProfileRequest.apply _)

}
