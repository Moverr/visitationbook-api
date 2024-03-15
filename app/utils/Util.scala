package utils


import cats.implicits._
import models.dtos.Auth
import models.enums.PermissionLevelEnum
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
class Util {
  def extractOwner(authorizedUser: Auth,RESOURCE:String): Option[Long] = {
    authorizedUser.user.roles
      .flatMap(_.permissions.find(_.resource.equalsIgnoreCase(RESOURCE)))
      .headOption
      .filter(_.read.equalsIgnoreCase(PermissionLevelEnum.MINE.toString))
      .map(_ => authorizedUser.user.userId)
  }


  private def parseDateTime(str: String):  Either[Throwable, DateTime] =
    Either.catchNonFatal(DateTime.parse(str, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")))

}

object  Util{
 implicit def apply(): Util =new Util()
  def extractOwner(authorizedUser: Auth,RESOURCE:String): Option[Long] = apply().extractOwner(authorizedUser,RESOURCE)


    /* Parse Date time */
  def parseDateTime(str: String):  Either[Throwable, DateTime] = apply().parseDateTime(str)

}




