package aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.{Around, Aspect}
import org.slf4j.LoggerFactory

@Aspect
class RequestTracerAOP {
  val log = LoggerFactory.getLogger(classOf[RequestTracerAOP])

  @Around()
  def traceRequest(jointPoint: ProceedingJoinPoint):Object={
    val methodName: String = jointPoint.getSignature.toString
    val inputArgs:String = jointPoint.getArgs.toString

     log.debug("request received in the method {} with arguments {} ",methodName,inputArgs)
    val output:Object = jointPoint.proceed()

    log.trace(
      "request   returned from method {}, with following arguments {}, with following output {}"
      ,methodName
      ,inputArgs
      ,output
    )

    output
  }

}
