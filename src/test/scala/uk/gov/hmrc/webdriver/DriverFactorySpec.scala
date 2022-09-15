package uk.gov.hmrc.webdriver

import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.edge.EdgeOptions
import org.openqa.selenium.firefox.FirefoxOptions
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DriverFactorySpec extends AnyWordSpec with Matchers {

  trait Setup {
    val driverFactory: DriverFactory = new DriverFactory
  }

  "DriverFactory" should {

    "return default Chrome options" in new Setup {
      val options: ChromeOptions = driverFactory.chromeOptions(None)

      options.asMap().get("browserName")                 shouldBe "chrome"
      options.asMap().get("goog:chromeOptions").toString shouldBe "{args=[start-maximized], extensions=[]}"
    }

    "return merged Chrome options" in new Setup {
      val capabilities = new ChromeOptions

      capabilities.setAcceptInsecureCerts(true)
      capabilities.setHeadless(true)

      val options: ChromeOptions = driverFactory.chromeOptions(Some(capabilities))

      options.asMap().get("acceptInsecureCerts")         shouldBe true
      options.asMap().get("browserName")                 shouldBe "chrome"
      options.asMap().get("goog:chromeOptions").toString shouldBe "{args=[start-maximized, --headless], extensions=[]}"
    }

    "return default Edge options" in new Setup {
      val options: EdgeOptions = driverFactory.edgeOptions(None)

      options.asMap().get("browserName")             shouldBe "MicrosoftEdge"
      options.asMap().get("ms:edgeOptions").toString shouldBe "{args=[start-maximized], extensions=[]}"
    }

    "return merged Edge options" in new Setup {
      val capabilities = new EdgeOptions

      capabilities.setAcceptInsecureCerts(true)
      capabilities.setHeadless(true)

      val options: EdgeOptions = driverFactory.edgeOptions(Some(capabilities))

      options.asMap().get("acceptInsecureCerts")     shouldBe true
      options.asMap().get("browserName")             shouldBe "MicrosoftEdge"
      options.asMap().get("ms:edgeOptions").toString shouldBe "{args=[start-maximized, --headless], extensions=[]}"
    }

    "return default Firefox options" in new Setup {
      val options: FirefoxOptions = driverFactory.firefoxOptions(None)

      options.asMap().get("browserName")                 shouldBe "firefox"
      options.asMap().get("moz:firefoxOptions").toString shouldBe "{}"
    }

    "return merged Firefox options" in new Setup {
      val capabilities = new FirefoxOptions

      capabilities.setAcceptInsecureCerts(true)
      capabilities.setHeadless(true)

      val options: FirefoxOptions = driverFactory.firefoxOptions(Some(capabilities))

      options.asMap().get("acceptInsecureCerts")         shouldBe true
      options.asMap().get("browserName")                 shouldBe "firefox"
      options.asMap().get("moz:firefoxOptions").toString shouldBe "{args=[-headless]}"
    }

  }

}
