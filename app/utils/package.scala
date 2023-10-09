import akka.http.scaladsl.model.DateTime

import java.sql.Timestamp

package object utils {

  val appName:String = ???

  def convertTimestampToDDateTime(entity: Option[Timestamp]): Option[DateTime] =
    entity match {
      case Some(value) => Some(  DateTime.apply(value.getTime))
      case None => None
    }

}
