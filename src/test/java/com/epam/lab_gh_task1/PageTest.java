package com.epam.lab_gh_task1;

import com.epam.lab_gh_task1.pages.HomePage;
import com.epam.lab_gh_task1.util.SessionHelper;
import com.epam.lab_gh_task1.util.WebDriverHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.IOException;

public class PageTest {

    private static DesiredCapabilities capabilities = null;

    protected WebDriver driver = null;
    protected HomePage homePage = null;
    Logger logger = Logger.getLogger("TestLogger");


    @Parameters({"browser-name"})
    @BeforeSuite
    public void initTestSuite(String browserName) throws IOException {
        if ((capabilities = SessionHelper.getBrowserCaps(browserName.toLowerCase())) == null) {
            throw new NoSuchSessionException("Required parameters can't be set");
        }
    }

    @Parameters({"user-name", "user-pass"})
    @BeforeClass
    public void initWebDriver(String userName, String userPass) {
        logger.info("trying to connect to Webdriver");
        driver = WebDriverHelper.getDriver(capabilities);
        driver.manage().window().maximize();
        homePage = SessionHelper.ensureSignIn(driver, userName, userPass);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        logger.info("all test are completed");
        WebDriverHelper.cLoseAllDrivers();
    }
}




