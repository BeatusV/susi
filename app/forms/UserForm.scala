package forms

import play.api.data.Form
import play.api.data.Forms._

trait UserForm {
  val userInfoForm:Form[UserInfoData] = Form(
    mapping(
      "userEmail" -> email,
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText
    )(UserInfoData.apply)(UserInfoData.unapply)
  )
}

case class UserInfoData(userEmail:String, firstName: String, lastName:String)