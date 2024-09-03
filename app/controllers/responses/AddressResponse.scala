package controllers.responses

import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.{JsPath, Writes}

case class AddressResponse(
                            id:Long,
                            street: String,
                            city: String,
                            state: String,
                            postalCode: String,
                            country: String,
                            latitude: Option[Double],
                            longitude: Option[Double]
                          )

object AddressResponse {
  implicit val addressResponseWrites:Writes[AddressResponse]=(
      (JsPath \ "id").write[Long] and
      (JsPath \ "street").write[String] and
      (JsPath \ "city").write[String] and
      (JsPath \ "state").write[String] and
      (JsPath \ "postaloode").write[String] and
      (JsPath \ "country").write[String] and
      (JsPath \ "latitude").writeNullable[Double] and
      (JsPath \ "longitude").writeNullable[Double]
    )(unlift(AddressResponse.unapply))


}


