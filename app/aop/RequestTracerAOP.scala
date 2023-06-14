package aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.{Around, Aspect}
import org.slf4j
import play.api.Logger
import utils.CustomLog


@Aspect
class RequestTracerAOP {




  @Around("execution(@controllers * )")
  private def   traceRequest(jointPoint: ProceedingJoinPoint):Object={
    val logger  =  CustomLog.log()

    val methodName: String = jointPoint.getSignature.toString
    val inputArgs:String = jointPoint.getArgs.toString

    //todo: add timestamp
      logger.info(s"request received in the method "+methodName+" with arguments {} "+inputArgs)
    val output:Object = jointPoint.proceed()

    logger.info(
      "request   returned from method "+methodName+", with following arguments "+inputArgs+", with following output "+output
    )

    output
  }

}
