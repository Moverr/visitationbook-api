package controllers.responses
import org.joda.time.DateTime
import play.api.libs.json.{JsError, JsResult, JsString, JsSuccess, JsValue, Reads, Writes}

import java.text.SimpleDateFormat
import java.util.Locale
import scala.util.Try


object DateTimeWrites {
  implicit val writeDateTime: Writes[DateTime] = new Writes[DateTime] {
    private val format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US)

    private val datetimefrmat: String = "dd MMM yyyy HH:mm:ss z";

    override def writes(dateTime: DateTime): JsValue = {

      JsString(dateTime.toString(datetimefrmat))
    }
  }
}
