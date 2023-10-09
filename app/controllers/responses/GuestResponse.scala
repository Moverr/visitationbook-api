package controllers.responses

case class GuestResponse(
                          id:Option[Long]
                          ,firstName:Option[String]
                          ,lastName:Option[String]
                        )
