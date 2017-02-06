package model

/**
  * Created by peter on 22-1-17.
  */
case class UserInfo(email:String, firstName:String, lastName:String)
case class PasswordInfo(
                       email: String,
                       hasher: String,
                       salt: String,
                       password: String
                       )

