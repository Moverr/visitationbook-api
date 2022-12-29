package controllers.requests

import org.joda.time.DateTime
import play.api.libs.json.{JsError, JsResult, JsString, JsSuccess, JsValue, Reads}

import java.text.SimpleDateFormat
import java.util.Locale
import scala.util.Try

object DateTimeReads{
  implicit val readDateTime: Reads[DateTime] = new Reads[DateTime] {
    private val format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US)

    override def reads(json: JsValue): JsResult[DateTime] = json match {

      case JsString(d) => Try(format.parse(d)).map(t => JsSuccess(new DateTime(t))).getOrElse(error(json))
      case _ => error(json)
    }

    private def error(json: JsValue) = JsError(s"Unable to parse $json into a DateTime with format EEE, dd MMM yyyy HH:mm:ss z ")
  }

}