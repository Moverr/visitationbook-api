package controllers.requests


import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import play.api.libs.json.{JsValue, Reads}

import java.sql.Timestamp

case class CustomTimeStamp(timestamp: DateTime)
object CustomTimeStamps {
  val dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
  implicit val reads: Reads[CustomTimeStamp] =
    (json: JsValue) => (json \ "timestamp").validate[String]
      .map(t => CustomTimeStamp(DateTime.parse(t, DateTimeFormat.forPattern(dateFormat))))
}


