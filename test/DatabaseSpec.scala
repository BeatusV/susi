import dao.UserInfoDaoSlick
import org.scalatestplus.play._
import play.api._
import play.api.db.slick.DatabaseConfigProvider
import play.api.inject.guice.GuiceApplicationBuilder



class DatabaseSpec extends PlaySpec
  with OneAppPerSuite {

  implicit override lazy val app = new GuiceApplicationBuilder().configure(
    Map("slick.dbs.default.driver" -> "slick.driver.PostgresDriver$",
      "slick.dbs.default.db.driver" -> "org.postgresql.Driver",
      "slick.dbs.default.db.url" -> "jdbc:postgresql://localhost:5432/testsusi",
      "slick.dbs.default.db.user" -> "postgres",
      "slick.dbs.default.db.password" -> "root"
    )

  ).build()
  val dbConfigprovider: DatabaseConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]
  "Testing controller" should {
    "blbalbblaba" in {
      val controller = new UserInfoDaoSlick(dbConfigprovider)
      val test = controller.insert("Mooie Email", "Tweede Email", "Derde emaoil")

    }
  }
}