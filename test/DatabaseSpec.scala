import dao.UserInfoDaoSlick
import org.scalatestplus.play._
import play.api._
import play.api.db.slick.DatabaseConfigProvider
import play.api.inject.guice.GuiceApplicationBuilder



class DatabaseSpec extends PlaySpec
  with OneAppPerSuite
  with TestConfig {

  implicit override lazy val app = testConfig
  val dbConfigprovider: DatabaseConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]
  "Testing controller" should {
    "blbalbblaba" in {
      val controller = new UserInfoDaoSlick(dbConfigprovider)
      val test = controller.insert("Msdfasd2453djflsdail", "Tweede Email", "Derde emaoil")

    }
  }
}

