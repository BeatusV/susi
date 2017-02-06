package forms

import play.api.data.Form
import play.api.data.Forms._

/**
  * Created by peter on 6-2-17.
  */


trait LoginForm {
  val loginForm:Form[LoginData] = Form(
    mapping(
      "loginEmail" -> email,
      "loginPassword" -> nonEmptyText
    )(LoginData.apply)(LoginData.unapply)
  )
}

case class LoginData(email:String, password: String)
