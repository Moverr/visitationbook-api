package controllers.responses

import play.api.libs.json.JsPath

import org.joda.time.DateTime
import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{JsPath, Writes}



sealed trait Error
case class ErrorRespnse(code:String,message:String) extends Error
object ErrorRespnseWrites{
  implicit val ErrorRespnseWrites: Writes[ErrorRespnse] = (
    (

        (JsPath \ "code").write[String] and
        (JsPath \ "v").write[String]

      ) (unlift(ErrorRespnse.unapply))
    )
}