package utils


import play.api.Configuration

import javax.inject.{Inject, Singleton}
import scala.language.implicitConversions

@Singleton
class Urls @Inject()(configuration: Configuration) {
  private lazy val authUrl: String = configuration.get[String]("auth.url")
  lazy val validationUrl: String = s"$authUrl/v1/auth/validate"
}

object Urls {
  def apply(configuration: Configuration): Urls = new Urls(configuration)
}
