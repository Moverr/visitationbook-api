package utils

import org.slf4j.LoggerFactory
import play.api.Logger

object CustomLog {

  // Using lazy initialization to ensure Logger is only instantiated when needed
  lazy val logger: org.slf4j.Logger = LoggerFactory.getLogger("CustomLogger")

  // Optionally, you can provide methods to log different levels
  def debug(message: => String): Unit = {
    if (logger.isDebugEnabled)
      logger.debug(message)
  }

  def info(message: => String): Unit = {
    if (logger.isInfoEnabled)
      logger.info(message)
  }

  def warn(message: => String): Unit = {
    if (logger.isWarnEnabled)
      logger.warn(message)
  }

  def error(message: => String): Unit = {
    if (logger.isErrorEnabled)
      logger.error(message)
  }

}
