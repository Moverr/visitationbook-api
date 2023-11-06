package controllers.requests

case class ProfileRequest(
                           userId: Option[Long]
                           , firstName: Option[String]
                           , otherNames: Option[String]
                           , gender: Option[String]
                           , profileType: Option[String]
                         //todo: option  address
                         )
