package controllers

import play.api.mvc._
import javax.inject._

import scala.concurrent.ExecutionContext.Implicits.global
import play.api.i18n.I18nSupport
import play.api.i18n.MessagesApi
import dao.UserInfoDao
import forms.UserForm

import scala.concurrent.Future

class HomeController @Inject()
(protected val userInfoDao: UserInfoDao,
 val messagesApi: MessagesApi)
  extends Controller
    with I18nSupport
    with UserForm {

  def view = Action {
    Ok(views.html.index(userInfoForm))
  }

  def add = Action.async { implicit request =>
    userInfoForm.bindFromRequest.fold(
      error => {
        Future.successful(Ok(views.html.index(error)))
      },
      person => {
        userInfoDao.insert(person.userEmail, person.firstName, person.lastName).map {
          _ => Redirect(routes.HomeController.view())
        }
      }
    )
  }

}