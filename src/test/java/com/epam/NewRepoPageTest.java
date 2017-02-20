package com.epam;

import com.epam.pages.HomePage;
import com.epam.pages.NewRepoPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by shaman on 19.02.17.
 */
public class NewRepoPageTest extends PageTest{

    private static final String NEW_REPO_NAME = "test_repo";
    private final static String GITHUB_HOME_URL = "https://github.com/";

    protected NewRepoPage newRepoPage = null;

    @BeforeClass
    public void InitPageObjects() {
        this.newRepoPage = this.homePage.btnNewRepClick();
        if (newRepoPage == null) {
            logger.error("New repository page is not created");
        }
    }

    @Parameters({"newrepo-name"})
    @AfterClass
    public void removeChanges(String newRepoName) {
        this.driver.get(GITHUB_HOME_URL);
        homePage = new HomePage(driver);
        homePage.delRepo(newRepoName);
    }

    @Parameters({"newrepo-name"})
    @Test (description="Check of creating new repository availability",enabled = true)
    public void CreateNewRepTest(String newRepoName) {
        logger.info("Start test CreateNewRepTest");
        Assert.assertTrue(newRepoPage.createNewRepo(newRepoName));
    }

}
