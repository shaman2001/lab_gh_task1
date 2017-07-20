package com.epam.lab_gh_task1;

import com.epam.lab_gh_task1.pages.HomePage;
import com.epam.lab_gh_task1.util.SessionHelper;
import com.epam.lab_gh_task1.util.WebDriverHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.IOException;

public class PageTest {

    private static DesiredCapabilities capabilities = null;

    protected WebDriver driver = null;
    protected HomePage homePage = null;
    public final static Logger LOG = LogManager.getLogger(LogManager.getRootLogger());


    @Parameters({"browser-name"})
    @BeforeSuite
    public void initTestSuite(@Optional("chrome") String browserName) throws IOException {
        if ((capabilities = SessionHelper.getBrowserCaps(browserName.toLowerCase())) == null) {
            throw new NoSuchSessionException("Required parameters can't be set");
        }
    }

    @Parameters({"user-name", "user-pass"})
    @BeforeClass
    public void initWebDriver(@Optional("shaman2001-test") String userName, @Optional("qwerty123") String userPass) {
        LOG.info("trying to connect to Webdriver");
        driver = WebDriverHelper.getDriver(capabilities);
        driver.manage().window().maximize();
        homePage = SessionHelper.ensureSignIn(driver, userName, userPass);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        LOG.info("all test are completed");
        WebDriverHelper.cLoseAllDrivers();
    }
}




