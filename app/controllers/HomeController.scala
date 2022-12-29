package controllers

import javax.inject._
import play.api._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  case class AppInfo(name:String,version:String,author:String)
  def index() = Action { implicit request: Request[AnyContent] =>
    val message:AppInfo = AppInfo("Booking System","2022.11.06","moverr@gmail,com")

    Ok(views.html.index(message.name)(message.author)(message.version))
  }
}
