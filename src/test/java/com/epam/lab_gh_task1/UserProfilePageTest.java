package com.epam.lab_gh_task1;

import com.epam.lab_gh_task1.pages.UserProfilePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

public class UserProfilePageTest extends PageTest {

    protected UserProfilePage userProfPage = null;

    @BeforeClass
    public void jumpToUserProfilePage() {
        logger.info("Attempt to open current user profile page");
        userProfPage = super.homePage.ddMenuProfileClick();
     }

    @AfterClass
    public void removeChanges() {
        userProfPage.clearProfBioInfo();
        userProfPage.clearProfURLInfo();
        userProfPage.clearProfCompany();
        userProfPage.clearProfLocation();
        userProfPage.btnUpdProfClick();
    }

    @Parameters({"newbio-info"})
    @Test (description="Check of changing user bio availability",enabled = true)
    public void userProfBioChangeTest(String newBioInfo) {
        logger.info("Start test userProfBioChangeTest");
        String oldBioInfo = userProfPage.getProfBioInfo();
        userProfPage.setProfBioInfo(newBioInfo);
        userProfPage.updateAndWaitConfirmation();
        String tmpBioInfo = userProfPage.getProfBioInfo();
        Assert.assertNotEquals(tmpBioInfo, oldBioInfo);
    }

    @Parameters({"newurl-info"})
    @Test (description="Check of changing user URL availability",enabled = true)
    public void userProfURLChangeTest(String newURLInfo) {
        logger.info("Start test userProfURLChangeTest");
        String oldURLInfo = userProfPage.getProfURLInfo();
        userProfPage.setProfURLInfo(newURLInfo);
        userProfPage.updateAndWaitConfirmation();
        String tmpURLInfo = userProfPage.getProfURLInfo();
        Assert.assertNotEquals(tmpURLInfo, oldURLInfo);
    }

    @Parameters({"newcompany-info"})
    @Test (description ="Check of changing user company availability", enabled = true)
    public void  userProfCompanyChangeTest(String newCompanyInfo) {
        logger.info("Start test userProfCompanyChangeTest");
        String oldCompanyInfo = userProfPage.getProfCompany();
        userProfPage.setProfCompany(newCompanyInfo);
        userProfPage.updateAndWaitConfirmation();
        String tmpCompanyInfo = userProfPage.getProfCompany();
        Assert.assertNotEquals(tmpCompanyInfo, oldCompanyInfo);
    }

    @Parameters({"newloc-info"})
    @Test (description ="Check of changing user location availability", enabled = true)
    public void userProfLocationChangeTest(String newLocationInfo) {
        logger.info("Start test userProfCompanyChangeTest");
        String oldLocationInfo = userProfPage.getProfLocation();
        userProfPage.setProfLocation(newLocationInfo);
        userProfPage.updateAndWaitConfirmation();
        String tmpLocationInfo = userProfPage.getProfLocation();
        Assert.assertNotEquals(tmpLocationInfo, oldLocationInfo);
    }
}
