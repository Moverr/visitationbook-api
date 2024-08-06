package controllers.responses

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
