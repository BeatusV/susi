package dao
import scala.concurrent.Future
import javax.inject.Inject

import model._
import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile


/**
  * Created by peter on 22-1-17.
  */
case class DBUserInfo
(
  email: String,
  first_name: String,
  last_name: String
)

trait TableDefinitions{
  protected val driver: JdbcProfile
  import driver.api._

  class UserInfos(tag: Tag) extends Table[DBUserInfo](tag, "user_info") {
    def email = column[String]("email")
    def first_name = column[String]("first_name")
    def last_name = column[String]("last_name")
    def * = (email, first_name, last_name) <>(DBUserInfo.tupled, DBUserInfo.unapply)
  }
  val slickUserInfos = TableQuery[UserInfos]
}

trait DaoSlickConfig extends TableDefinitions with HasDatabaseConfigProvider[JdbcProfile]