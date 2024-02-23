package aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.{Around, Aspect}
import play.api.Logger


@Aspect
class RequestTracerAOP {
  private val log = Logger(getClass)

  @Around("execution(@controllers * )")
  private def traceRequest(jointPoint: ProceedingJoinPoint): Object = {


    val methodName: String = jointPoint.getSignature.toString
    val inputArgs: String = jointPoint.getArgs.mkString("Array(", ", ", ")")

    log.info(s"request received in the method " + methodName + " with arguments {} " + inputArgs)
    val output: Object = jointPoint.proceed()

    log.info(
      "request   returned from method " + methodName + ", with following arguments " + inputArgs + ", with following output " + output
    )

    output
  }

}
