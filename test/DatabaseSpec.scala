import java.sql.SQLException

import dao.UserInfoDaoSlick
import org.scalatestplus.play._
import play.api.db.slick.DatabaseConfigProvider
import model.UserInfo
import org.scalatest.BeforeAndAfter

import scala.concurrent.TimeoutException
import scala.concurrent.duration._
import scala.concurrent.Await


class DatabaseSpec extends PlaySpec
  with OneAppPerSuite
  with TestConfig
  with BeforeAndAfter
{

  val dbConfigprovider: DatabaseConfigProvider = app.injector.instanceOf[DatabaseConfigProvider]

  implicit override lazy val app = testConfig

  "Userdataslick" should {
    "insert data" in {
      val controller = new UserInfoDaoSlick(dbConfigprovider)
      val user = new UserInfo("testemail@home.nl", "firstName", "secondName")
      val testData = controller.insert(user.email, user.firstName, user.lastName)
      try {
        val testDataAnswer = Await.result(testData, 10.seconds)
        testDataAnswer.get.email must fullyMatch regex user.email
        testDataAnswer.get.first_name must fullyMatch regex user.firstName
        testDataAnswer.get.last_name must fullyMatch regex user.lastName
      }catch {
        case p: SQLException => print("SQL Exception: " + p.getMessage)
        case e: TimeoutException => e.printStackTrace()
      }
      try {
        val receivedData = controller.all
        val result = Await.result(receivedData, 10.seconds)
        result.last.email must fullyMatch regex user.email
        result.last.first_name must fullyMatch regex user.firstName
        result.last.last_name must fullyMatch regex user.lastName
      }catch{
        case e: TimeoutException => e.printStackTrace()
      }
    }
  }
}

