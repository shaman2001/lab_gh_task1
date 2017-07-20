package com.epam.lab_gh_task1.steps;

import com.epam.lab_gh_task1.PageTest;
import com.epam.lab_gh_task1.pages.HomePage;
import com.epam.lab_gh_task1.pages.UserProfilePage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;

public class MyStepdefs extends PageTest {

    private UserProfilePage userProfilePage = null;
    private String oldBioData;
    private static final String USER_NAME = "shaman2001-test";
    private static final String PASSWORD = "qwerty123";

    @Before("@Scenario")
    public void init() throws IOException {
        initTestSuite("chrome");
        initWebDriver(USER_NAME, PASSWORD);
    }

    @After("@Scenario")
    public void  close() {
        tearDown();
    }


    @Given("^user navigates to own profile page$")
    public void userNavigatesToOwnProfilePage() throws Throwable {
        initTestSuite("chrome");
        initWebDriver(USER_NAME, PASSWORD);
        userProfilePage = homePage.ddMenuProfileClick();
    }


    @When("^enters data in the bio text box$")
    public void entersDataInTheBioTextBox() throws Throwable {
        oldBioData = userProfilePage.getProfBioInfo();
        userProfilePage.setProfBioInfo("my new io info");
    }



    @And("^clicks Update profile button$")
    public void clicksUpdateProfileButton() throws Throwable {
        userProfilePage.btnUpdProfClick();

    }

    @Then("^confirmation popup is displayed$")
    public void confirmationPopupIsDisplayed() throws Throwable {
        WebElement popupContainer = (new WebDriverWait(driver, 5)).until(ExpectedConditions.
                presenceOfElementLocated(UserProfilePage.POPUP_CONTAINER_CONFIRM));
        Assert.assertTrue(popupContainer!=null);
    }

    @And("^user data is changed$")
    public void userDataIsChanged() throws Throwable {
        String newBioData = userProfilePage.getProfBioInfo();
        Assert.assertNotEquals(oldBioData, newBioData);

    }
}
