package services

import play.api.mvc.Headers

import cats.implicits._


class ClientRequestHeader(var header: Headers) {


  def map(): Map[String, Seq[String]] = header.toMap

  def getHeaderString(key: String): Option[String] = header.toMap.get(key).flatMap(x => x.headOption)

  def deviceToken(): Option[String] = getHeaderString("DEVICE-ID")


}

object ClientRequestHeader {
  def apply(header: Headers): ClientRequestHeader = new ClientRequestHeader(header)
}