package utils


import models.dtos.Auth
import models.enums.PermissionLevelEnum
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

import java.sql.Timestamp
class Util {
  def extractOwner(authorizedUser: Auth,RESOURCE:String): Option[Long] = {
    authorizedUser.user.roles
      .flatMap(_.permissions.find(_.resource.equalsIgnoreCase(RESOURCE)))
      .headOption
      .filter(_.read.equalsIgnoreCase(PermissionLevelEnum.MINE.toString))
      .map(_ => authorizedUser.user.userId)
  }

  import cats.implicits._
  private def parseDateTime(str: String):  Either[Throwable, DateTime] = {
    Either.catchNonFatal(DateTime.parse(str, DateTimeFormat.forPattern("your_date_time_format_here")))

  }


}

object  Util{
 implicit def apply(): Util =new Util()
  def extractOwner(authorizedUser: Auth,RESOURCE:String): Option[Long] = apply().extractOwner(authorizedUser,RESOURCE)


    /* Parse Date time */
  def parseDateTime(str: String):  Either[Throwable, DateTime] = apply().parseDateTime(str)

}




