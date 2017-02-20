package com.epam;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.epam.pages.HomePage;
import com.epam.pages.LoginPage;
import com.epam.util.OSRecognizer;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import ru.stqa.selenium.factory.WebDriverFactory;
import static com.epam.util.SessionHelper.*;

/**
 * Base class for TestNG-based test classes
 */
public class PageTest {
  private final static String GITHUB_LOGIN_URL = "https://github.com/login";
  private final static String GITHUB_HOME_URL = "https://github.com/";
  private final static String CHROMEDRIVER_WIN_PATH="c:\\selenium\\chromedriver.exe";
  private final static String CHROMEDRIVER_LIN_PATH="/home/shaman/mywebdrivers/chromedriver";

  protected static DesiredCapabilities capabilities = null;

  protected WebDriver driver = null;
  protected HomePage homePage = null;
  protected Logger logger = Logger.getLogger("TestLogger");


  @Parameters({"browser-name"})
  @BeforeSuite
  public void initTestSuite(String browserName) throws IOException {
    if ((capabilities = getBrowserCaps(browserName.toLowerCase())) == null) {
        //System.err.println("");
        throw new NoSuchSessionException("Required parameters can't be set");
    }
  }

  @Parameters({"user-name", "user-pass"})
  @BeforeClass
  public void initWebDriver(String userName, String userPass) {
    logger.info("trying to connect to Webdriver");
    driver = WebDriverFactory.getDriver(capabilities);
    if (driver == null) {
      logger.error("Connection to driver failed");
      return;
    }
//    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    if (!isSigned(driver)) { //if is not signed
      logger.info("trying to move to github login page");
      driver.get(GITHUB_LOGIN_URL);
      logger.info("trying to login github");
      homePage = new LoginPage(driver).login(userName, userPass);
    } else {
      if (!isSignedAs(driver, userName)) { //if signed with incorrect %username%
        logout(driver);
        logger.info("trying to move to github login page");
        driver.get(GITHUB_LOGIN_URL);
        logger.info("trying to login github");
        homePage = new LoginPage(driver).login(userName, userPass);
      } else { //if signed with correct %username%
        driver.get(GITHUB_HOME_URL);
        homePage = new HomePage(driver);
      }
    }
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    logger.info("all test are completed");
    WebDriverFactory.dismissAll();
  }
}




