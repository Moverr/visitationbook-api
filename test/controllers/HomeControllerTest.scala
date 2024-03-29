package controllers

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.test.FakeRequest
import play.api.test.Helpers._



class HomeControllerTest extends AnyFunSpec with GuiceOneAppPerSuite   {
describe("home controller"){
  val controller = new HomeController(stubControllerComponents())

  describe("Get Endpint"){
      it ("should return 200 ok response"){
        val home = controller.index().apply(FakeRequest(GET, "/"))
        status(home) mustBe OK
      }
  }
}


}