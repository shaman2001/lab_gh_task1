package com.epam.lab_gh_task1;

import com.epam.lab_gh_task1.pages.UserProfilePage;
import org.testng.Assert;
import org.testng.annotations.*;

public class UserProfilePageTest extends PageTest {

    protected UserProfilePage userProfPage = null;

    @BeforeClass
    public void jumpToUserProfilePage() {
        LOG.info("Attempt to open current user profile page");
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
    public void userProfBioChangeTest(@Optional("was born, live, not died")String newBioInfo) {
        LOG.info("Start test userProfBioChangeTest");
        String oldBioInfo = userProfPage.getProfBioInfo();
        userProfPage.setProfBioInfo(newBioInfo);
        userProfPage.updateAndWaitConfirmation();
        String tmpBioInfo = userProfPage.getProfBioInfo();
        Assert.assertNotEquals(tmpBioInfo, oldBioInfo);
    }

    @Parameters({"newurl-info"})
    @Test (description="Check of changing user URL availability",enabled = true)
    public void userProfURLChangeTest(@Optional("http://www.livejournal.com") String newURLInfo) {
        LOG.info("Start test userProfURLChangeTest");
        String oldURLInfo = userProfPage.getProfURLInfo();
        userProfPage.setProfURLInfo(newURLInfo);
        userProfPage.updateAndWaitConfirmation();
        String tmpURLInfo = userProfPage.getProfURLInfo();
        Assert.assertNotEquals(tmpURLInfo, oldURLInfo);
    }

    @Parameters({"newcompany-info"})
    @Test (description ="Check of changing user company availability", enabled = true)
    public void  userProfCompanyChangeTest(@Optional("EPAM Systems") String newCompanyInfo) {
        LOG.info("Start test userProfCompanyChangeTest");
        String oldCompanyInfo = userProfPage.getProfCompany();
        userProfPage.setProfCompany(newCompanyInfo);
        userProfPage.updateAndWaitConfirmation();
        String tmpCompanyInfo = userProfPage.getProfCompany();
        Assert.assertNotEquals(tmpCompanyInfo, oldCompanyInfo);
    }

    @Parameters({"newloc-info"})
    @Test (description ="Check of changing user location availability", enabled = true)
    public void userProfLocationChangeTest(@Optional("Earth, Belarus, Minsk") String newLocationInfo) {
        LOG.info("Start test userProfCompanyChangeTest");
        String oldLocationInfo = userProfPage.getProfLocation();
        userProfPage.setProfLocation(newLocationInfo);
        userProfPage.updateAndWaitConfirmation();
        String tmpLocationInfo = userProfPage.getProfLocation();
        Assert.assertNotEquals(tmpLocationInfo, oldLocationInfo);
    }
}
