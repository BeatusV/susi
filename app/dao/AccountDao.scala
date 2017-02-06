package dao

import com.google.inject.{ImplementedBy, Inject}
import org.mindrot.jbcrypt.BCrypt
import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

/**
  * Created by peter on 6-2-17.
  */

@ImplementedBy(classOf[AccountDaoSlick])
trait AccountDao {
  def create(email: String, password: String): Future[Option[DBUserInfo]]

  def exists(email: String): Future[Boolean]
}

class AccountDaoSlick @Inject()
(protected val dbConfigProvider: DatabaseConfigProvider,
 userInfoDao: UserInfoDao)
  extends AccountDao
    with DaoSlickConfig {

  import driver.api._

  private val salt: String = BCrypt.gensalt()

  private def hash(pw: String): String = BCrypt.hashpw(pw, salt)

  private def hashMan(pw: String, sl: String) = BCrypt.hashpw(pw, sl)

  private def compare(pw: String, hashed: String, sl: String): Boolean = hashMan(pw, sl) == hashed

  override def create(email: String, password: String): Future[Option[DBUserInfo]] = {
    val newUser = DBUserInfo(email, "", "")
    val newAccount = DBAccount(email, "bcrypt", salt, hash(password))
    for {
      user <- userInfoDao.exists(email)
      pwExist <- exists(email)
      createAccount <- db.run(slickAccounts += newAccount)
      createUser <- userInfoDao.insert(newUser.email, newUser.first_name, newUser.last_name)
    } yield {
      (user, pwExist, createAccount, createUser) match {
        case (true, false, _, None) => None
        case (true, true, _, None) => None
        case (false, false, i, Some(p)) =>
          if (i > 0) Some(newUser)
          else None
      }
    }
  }

  override def exists(email: String) = {
    for {
      pw <- db.run(slickAccounts.filter(_.email === email).result.headOption)
    } yield {
      pw match {
        case None => false
        case Some(p) => true
      }
    }
  }

}