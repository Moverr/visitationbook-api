package aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.{Around, Aspect}
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

@Aspect
class RequestTracerAOP {

  val logger = Logger(LoggerFactory.getLogger("TheLoggerName"))


  @Around()
  def traceRequest(jointPoint: ProceedingJoinPoint):Object={
    val methodName: String = jointPoint.getSignature.toString
    val inputArgs:String = jointPoint.getArgs.toString

    logger.debug("request received in the method {} with arguments {} ",methodName,inputArgs)
    val output:Object = jointPoint.proceed()

    logger.trace(
      "request   returned from method {}, with following arguments {}, with following output {}"
      ,methodName
      ,inputArgs
      ,output
    )

    output
  }

}
