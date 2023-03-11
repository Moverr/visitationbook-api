package aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.{Around, Aspect}


@Aspect
class RequestTracerAOP {


  @Around("execution(@)")
  def traceRequest(jointPoint: ProceedingJoinPoint):Object={
    val methodName: String = jointPoint.getSignature.toString
    val inputArgs:String = jointPoint.getArgs.toString

   println(s"request received in the method "+methodName+" with arguments {} "+inputArgs)
    val output:Object = jointPoint.proceed()

    println(
      "request   returned from method "+methodName+", with following arguments "+inputArgs+", with following output "+output
    )

    output
  }

}
