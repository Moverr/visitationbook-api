package controllers.requests

case class AddressRequest(
                           street: String,
                           city: String,
                           state: String,
                           postalCode: String,
                           country: String,
                           latitude: Option[Double],
                           longitude: Option[Double]

                         )
