package controllers.requests

case class ProfileRequest(
                           userId: Option[Long]
                           , firstName: Option[String]
                           , lastName: Option[String]
                           , gender: Option[String]
                           , profileType: Option[String]
                         )
