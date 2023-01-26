package controllers.responses
import org.joda.time.DateTime
import org.joda.time.DateTimeZone.UTC
import slick.jdbc.PostgresProfile.api._

import java.sql.Timestamp

object DateTimeWrites {
  implicit def jodaDateTimeType =
    MappedColumnType.base[DateTime, Timestamp](
      dt => new Timestamp(dt.getMillis),
      ts => new DateTime(ts.getTime, UTC)
    )
}
