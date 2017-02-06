package controllers

import com.google.inject.Inject
import dao.AccountDao
import forms.LoginForm
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by peter on 6-2-17.
  */
class LoginController @Inject()
(protected val accountDao: AccountDao,
 val messagesApi: MessagesApi
) extends Controller
  with I18nSupport
  with LoginForm {

  def view = Action {
    Ok(views.html.login(loginForm))
  }

//  def login = Action.async { implicit request =>
//    loginForm.bindFromRequest.fold(
//      error => {
//        Future.successful(Ok(views.html.login(error)))
//      },
//      loginData => {
//        println(loginData.email)
//        Ok(views.html.login(loginForm))
//      }
//    )
//  }

  def create = Action.async { implicit  request =>
      loginForm.bindFromRequest.fold(
        error => {
          Future.successful(Ok(views.html.login(error)))
        },
        loginData => {
          accountDao.create(loginData.email, loginData.password).map{
            case None => BadRequest(views.html.login(loginForm.withGlobalError("Something went wrong")))
            case Some(p) => Redirect(routes.LoginController.view())
          }
        }
      )
  }
}
