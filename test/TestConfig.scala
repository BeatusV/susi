import play.api.inject.guice.GuiceApplicationBuilder

/**
  * Created by peter on 3-2-17.
  * See this file as a global configuration file to connect to the test database
  */
trait TestConfig {
  val testConfig = new GuiceApplicationBuilder().configure(
    Map("slick.dbs.default.driver" -> "slick.driver.PostgresDriver$",
      "slick.dbs.default.db.driver" -> "org.postgresql.Driver",
      "slick.dbs.default.db.url" -> "jdbc:postgresql://localhost:5432/testsusi",
      "slick.dbs.default.db.user" -> "postgres",
      "slick.dbs.default.db.password" -> "root"
    )
  ).build()
}

