import dao.UserInfoDaoSlick
import org.scalatestplus.play._
import play.api.db.slick.DatabaseConfigProvider
import model.UserInfo
import play.api.t




class DatabaseSpec extends PlaySpec
  with OneAppPerSuite
  with TestConfig {

  implicit override lazy val app = testConfig
  val dbConfigprovider: DatabaseConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]
  "The database" should {
    "insert data" in {
      val controller = new UserInfoDaoSlick(dbConfigprovider)
      val user = new UserInfo("testemail@home.nl", "firstName", "secondName")
      val testData = controller.insert(user.email, user.firstName, user.lastName)

      val recievedData = controller.all
      recievedData.result(10.Seconds)
    }
  }
}

