package com.epam;

import java.io.IOException;

import com.epam.pages.HomePage;
import com.epam.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import ru.stqa.selenium.factory.WebDriverFactory;

import com.epam.util.PropertyLoader;

/**
 * Base class for TestNG-based test classes
 */
public class PageTest {
  private final static String USERNAME = "shaman2001";
  private final static String PASSWORD = "qwerty123";
  private final static String GITHUB_LOGIN_URL = "https://github.com/login";
  private final static String GITHUB_HOME_URL = "https://github.com/";
  private final static String CHROMEDRIVER_EXE_PATH="c:\\selenium\\chromedriver.exe";

  protected static String gridHubUrl;
  protected static String baseUrl;
  protected static DesiredCapabilities capabilities;

  protected WebDriver driver;
  protected HomePage homePage = null;
  protected Logger logger = Logger.getLogger("TestLogger");

  @BeforeSuite
  public void initTestSuite() throws IOException {
    System.setProperty("webdriver.chrome.driver",CHROMEDRIVER_EXE_PATH);
    capabilities = DesiredCapabilities.chrome();
    logger.info("trying to connect to Webdriver");
    driver = WebDriverFactory.getDriver(capabilities);
    if (driver == null) {
      logger.error("Connection to driver failed");
    }
    logger.info("trying to move to github login page");
    driver.get(GITHUB_LOGIN_URL);
    logger.info("trying to login github");
    homePage = new LoginPage(driver).login(USERNAME, PASSWORD);
  }

  @BeforeClass
  public void initWebDriver() {
    driver.get(GITHUB_HOME_URL);
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    logger.info("all test are completed");
//    driver.quit();
//    driver.close();
    WebDriverFactory.dismissAll();
  }
}
