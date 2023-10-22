package controllers.requests

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Reads}

/*
 the host and guest must be declared prior.
  if  not create profiles for the host and guest
 */

case class VisitRequest(
                              hostId: Long,
                              guestId: Long,
                              officeId: Option[Long],
                              departmentId: Option[Long],
                              timeIn:Option[String],
                              timeOut: Option[String],
                              invitationType:Option[String]
                            )


