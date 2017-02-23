package com.epam;

import com.epam.pages.HomePage;
import com.epam.util.PropertyLoader;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import ru.stqa.selenium.factory.WebDriverFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.epam.util.SessionHelper.ensureSignIn;
import static com.epam.util.SessionHelper.getBrowserCaps;

public class PageTest {
//    private final static String GITHUB_LOGIN_URL = "https://github.com/login";
//    private final static String GITHUB_HOME_URL = "https://github.com/";

    protected static DesiredCapabilities capabilities = null;

    protected WebDriver driver = null;
    protected HomePage homePage = null;
    protected Logger logger = Logger.getLogger("TestLogger");


    @Parameters({"browser-name"})
    @BeforeSuite
    public void initTestSuite(String browserName) throws IOException {
        PropertyLoader.loadProperties();
        if ((capabilities = getBrowserCaps(browserName.toLowerCase())) == null) {
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
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        homePage = ensureSignIn(driver, userName, userPass);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        logger.info("all test are completed");
        WebDriverFactory.dismissAll();
    }
}




