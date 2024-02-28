package controllers.responses

import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.{JsPath, Writes}





case class OfficeResponse(id:Long,name:String)
object OfficeResponseWrites {
  implicit  val officeResponse:Writes[OfficeResponse]=(
    (JsPath \ "id").write[Long] and
      (JsPath \ "first_name").write[String]
    )(unlift(OfficeResponse.unapply))

}

