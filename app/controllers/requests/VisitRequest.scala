package controllers.requests

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Reads}


case class VisitRequest(
                              hostId: Option[Long],
                              guestId: Option[Long],
                              officeId: Option[Long],
                              departmentId: Option[Long],
                              timeIn:Option[String],
                              timeOut: Option[String],
                              invitationType:Option[String]
                            )


