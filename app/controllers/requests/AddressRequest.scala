package controllers.requests

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Reads}

case class AddressRequest(
                           street: String,
                           city: String,
                           state: String,
                           postalCode: String,
                           country: String,
                           latitude: Option[Double],
                           longitude: Option[Double]

                         )

object AddressRequest {

  implicit val addressRequest: Reads[AddressRequest] = (
    (JsPath \ "street").read[String] and
      (JsPath \ "city").read[String] and
      (JsPath \ "state").read[String] and
      (JsPath \ "postalCode").read[String] and
      (JsPath \ "country").read[String] and
      (JsPath \ "latitude").readNullable[Double] and
      (JsPath \ "longitude").readNullable[Double]
    )(AddressRequest.apply _)

}
