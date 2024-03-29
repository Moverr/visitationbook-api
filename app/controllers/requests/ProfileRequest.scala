package controllers.requests

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Reads}


case class ProfileRequest(
                           userId: Option[Long]
                           , firstName: Option[String]
                           , otherNames:  Option[String]
                           , gender: Option[String]
                           , profileType: Option[String]

                         )


object ProfileRequest {

  implicit val profileRequestReads: Reads[ProfileRequest] = (
    (JsPath \ "user_id").readNullable[Long] and
      (JsPath \ "first_name").readNullable[String] and
      (JsPath \ "other_names").readNullable[String] and
      (JsPath \ "gender").readNullable[String] and
      (JsPath \ "profile_type").readNullable[String]
    )(ProfileRequest.apply _)

}

