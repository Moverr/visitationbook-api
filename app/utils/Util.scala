package utils


import cats.implicits._
import models.dtos.Auth
import models.enums.PermissionLevelEnum
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

import java.sql.Timestamp

class Util {
  def extractOwner(authorizedUser: Auth, RESOURCE: String): Option[Long] = {
    authorizedUser.user.roles
      .flatMap(_.permissions.find(_.resource.equalsIgnoreCase(RESOURCE)))
      .headOption
      .filterNot(_.read.equalsIgnoreCase(PermissionLevelEnum.NONE.toString))
      .map(_ => authorizedUser.user.userId)
  }


  def parseDateTime(str: String): Either[Throwable, DateTime] =
    Either.catchNonFatal(DateTime.parse(str, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")))

  def getTimeStamp(dateTime: DateTime): Timestamp = new Timestamp(dateTime.getMillis)

  def getTimeStamp(dateTime: Option[DateTime]): Option[Timestamp] = dateTime.map(getTimeStamp)


}

object Util {
  implicit def apply(): Util = new Util()

  def extractOwner(authorizedUser: Auth, RESOURCE: String): Option[Long] = apply().extractOwner(authorizedUser, RESOURCE)

  /* Parse Date time */
  def parseDateTime(str: String): Either[Throwable, DateTime] = apply().parseDateTime(str)

  def getTimeStamp(dateTime: DateTime): Timestamp = apply().getTimeStamp(dateTime)

  def getTimeStamp(dateTime: Option[DateTime]): Option[Timestamp] = apply().getTimeStamp(dateTime)


}




