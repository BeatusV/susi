package dao

import com.google.inject.{ImplementedBy, Inject}
import model.UserInfo
import play.api.db.slick.DatabaseConfigProvider

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by peter on 22-1-17.
  */
@ImplementedBy(classOf[UserInfoDaoSlick])
trait UserInfoDao {
  def all: Future[Seq[DBUserInfo]]
  def insert(email:String, firstName:String, lastName:String): Future[Option[DBUserInfo]]
}

class UserInfoDaoSlick @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends UserInfoDao with DaoSlickConfig {
  import driver.api._
  override def all: Future[Seq[DBUserInfo]] = {
    db.run(slickUserInfos.result)
  }
  override def insert(email:String, firstName:String, lastName:String) = {
    val newUserInfo = DBUserInfo(email, firstName, lastName)
    for {
      create <- db.run(slickUserInfos += newUserInfo)
    } yield {
      if (create > 0) Some(newUserInfo)
      else None
    }
  }
}
