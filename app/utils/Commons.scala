package utils

import org.joda.time.DateTime

import java.sql.Timestamp

object Commons {

  def getDate(entity: Option[Timestamp]): Option[DateTime] =
    entity match {
      case Some(value) => Some(new DateTime(value.getTime))
      case None => None
    }

}
