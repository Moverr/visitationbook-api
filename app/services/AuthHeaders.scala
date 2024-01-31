package services

import play.api.mvc.Headers

class AuthHeaders(var header:Headers) {
   def map() : Map[String, Seq[String]] = header.toMap
     def getHeaderString(key:String): Option[String] =header.toMap.get(key).flatMap(x=>x.headOption)
   def deviceToken():Option[String] = getHeaderString("DEVICE-ID")
}
object AuthHeaders{
  def apply(header: Headers): AuthHeaders = new AuthHeaders(header)
}