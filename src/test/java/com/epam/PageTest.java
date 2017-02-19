package com.epam;

import java.io.IOException;

import com.epam.pages.HomePage;
import com.epam.pages.LoginPage;
import com.epam.util.OSRecognizer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import java.util.NoSuchElementException;

import ru.stqa.selenium.factory.WebDriverFactory;

import com.epam.util.PropertyLoader;

import static com.epam.util.OSRecognizer.*;


/**
 * Base class for TestNG-based test classes
 */
public class PageTest {
  private final static String USERNAME = "shaman2001";
  private final static String PASSWORD = "qwerty123";
  private final static String GITHUB_LOGIN_URL = "https://github.com/login";
  private final static String GITHUB_HOME_URL = "https://github.com/";
  private final static String CHROMEDRIVER_WIN_PATH="c:\\selenium\\chromedriver.exe";
  private final static String CHROMEDRIVER_LIN_PATH="/home/shaman/mywebdrivers/chromedriver";

  protected static String gridHubUrl;
  protected static String baseUrl;
  protected static DesiredCapabilities capabilities = null;

  protected WebDriver driver = null;
  protected HomePage homePage = null;
  protected Logger logger = Logger.getLogger("TestLogger");

  @BeforeSuite
  public void initTestSuite() throws IOException {
    if (OSRecognizer.isUnix()) {
      System.setProperty("webdriver.chrome.driver",CHROMEDRIVER_LIN_PATH);
    } else if (OSRecognizer.isWindows()) {
      System.setProperty("webdriver.chrome.driver",CHROMEDRIVER_WIN_PATH);
    } else {
      System.out.println("OS is not supported");
    }
  }

  @BeforeClass
  public void initWebDriver() {
    capabilities = DesiredCapabilities.chrome();
    logger.info("trying to connect to Webdriver");
    driver = WebDriverFactory.getDriver(capabilities);
    if (driver == null) {
      logger.error("Connection to driver failed");
    }
    if (!isSigned(driver)) { //if is not signed
      logger.info("trying to move to github login page");
      driver.get(GITHUB_LOGIN_URL);
      logger.info("trying to login github");
      homePage = new LoginPage(driver).login(USERNAME, PASSWORD);
    } else {
      if (!isSignedAs(driver, USERNAME)) { //if is not signed as %username%
        logout(driver);
        logger.info("trying to move to github login page");
        driver.get(GITHUB_LOGIN_URL);
        logger.info("trying to login github");
        homePage = new LoginPage(driver).login(USERNAME, PASSWORD);
      } else {
        driver.get(GITHUB_HOME_URL);
        homePage = new HomePage(driver);
      }
    }
  }

//  @AfterClass
//  public void stopBrowser() {
//    if (driver != null) {
//      driver.close();
//    }
//  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    logger.info("all test are completed");
    WebDriverFactory.dismissAll();
  }

  private boolean isSigned(WebDriver webDriver) {
    WebElement webElement = null;
    try {
      webElement = webDriver.findElement(By.xpath(".//div[contains(text(),'Signed in as')]"));
    } catch (Exception e) {
      logger.error("Pprofile dropdown menu is not found");
    }
    return webElement != null;
  }

  private boolean isSignedAs(WebDriver webDriver, String userName) {
    boolean result = false;
    String  logStr[] = null;
    try {
      webDriver.findElement(By.xpath("//ul[@id='user-links']/li[3]/a")).click();
      logStr = webDriver.findElement(By.cssSelector("div.dropdown-header.header-nav-current-user.css-truncate"))
                                        .getText().trim().split(" ");
      //click somewhere for closing drop down menu
      webDriver.findElement(By.cssSelector("div.modal-backdrop.js-touch-events")).click();
    } catch (Exception e) {
      logger.error("Dropdown menu 'Sign out' not found");
    }
    result = logStr != null && logStr[logStr.length-1].equals(userName);
    return result;
  }

  private void logout(WebDriver webDriver) {
    WebElement ddMenu = null;
    try {
      ddMenu = webDriver.findElement(By.xpath("//ul[@id='user-links']/li[3]/a"));
    } catch (NoSuchElementException e) {
      System.err.println(e.getMessage());
    }
    if (ddMenu == null) {
      webDriver.get(GITHUB_HOME_URL);
      ddMenu = webDriver.findElement(By.xpath("//ul[@id='user-links']/li[3]/a"));
    }
    ddMenu.click();
    webDriver.findElement(By.cssSelector("button.dropdown-item.dropdown-signout")).click();
  }
}




