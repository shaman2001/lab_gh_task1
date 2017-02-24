package com.epam.lab_gh_task1;

import com.epam.lab_gh_task1.pages.NewRepoPage;
import com.epam.lab_gh_task1.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NewRepoPageTest extends PageTest {

    //    private static final String NEW_REPO_NAME = "test_repo";
    private final static String GITHUB_HOME_URL = "https://github.com/";

    protected NewRepoPage newRepoPage = null;

    @BeforeClass
    public void jumpToNewRepoPage() {
        logger.info("Attempt to open Create new repository page");
        newRepoPage = this.homePage.btnNewRepClick();
    }

    @Parameters({"newrepo-name"})
    @AfterClass
    public void removeChanges(String newRepoName) {
        this.driver.get(GITHUB_HOME_URL);
        homePage = new HomePage(driver);
        homePage.delRepo(newRepoName);
    }

    @Parameters({"newrepo-name"})
    @Test(description = "Check of creating new repository availability", enabled = true)
    public void createNewRepTest(String newRepoName) {
        logger.info("Start test CreateNewRepTest");
        Assert.assertTrue(newRepoPage.createNewRepo(newRepoName));
    }

}
