package utils

import org.slf4j
import play.api.Logger



  class CustomLog {

  def apply(): slf4j.Logger = {
    val loggerInfra: Logger = Logger("CustomLogger")
    loggerInfra.logger
  }
}

  object CustomLog extends CustomLog{
  def log():  slf4j.Logger ={
    val CustomLog = new CustomLog().apply();
    CustomLog
  }
}
