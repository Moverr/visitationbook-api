package controllers


import play.api.mvc._

import java.time.LocalDate
import java.time.format._
import javax.inject._
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
    val currentTtime:LocalDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy ")
    val currentDate:String =formatter.format(currentTtime)
    //2022.11.06"
    val initialDate = "2022.11.06"
    val message:AppInfo = AppInfo(s"kitabo Booking System API",s"$initialDate - $currentDate","moverr@gmail,com")

    Ok(views.html.index(message.name)(message.author)(message.version))
  }
}
