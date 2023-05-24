package controllers.requests

case class RequestVisitation(
                              hostId: Option[Long],
                              guestId: Option[Long],
                              officeId: Option[Long],
                              departmentId: Option[Long],
                              timeIn: String,
                              timeOut: String,
                              invitationType:Option[String]
                            )
