package com.epam;

import com.epam.pages.UserProfilePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

/**
 * Created by Vladimir_Kotovich on 2/17/2017.
 */
public class UserProfilePageTest extends PageTest {
    private final static String NEWBIOINFO = "was born, live, not died";
    private final static String NEWURLINFO = "http://www.livejournal.com";
    private final static  String NEWCOMPANYINFO = "EPAM Systems";
    private final static String NEWLOCINFO = "Earth, Belarus, Minsk";

    protected UserProfilePage userProfPage = null;

    @BeforeClass
    public void InitPageObjects() {
        logger.info("Attempt to open UserProfilePage");
        this.userProfPage = this.homePage.ddMenuProfileClick();
        if (userProfPage == null) {
            logger.error("UserProfilePage is not created");
        }
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
        userProfPage.btnUpdProfClick();
        userProfPage.refreshPage();
        String tmpBioInfo = userProfPage.getProfBioInfo();
        Assert.assertNotEquals(tmpBioInfo, oldBioInfo);
    }

    @Parameters({"newurl-info"})
    @Test (description="Check of changing user URL availability",enabled = true)
    public void userProfURLChangeTest(String newURLInfo) {
        logger.info("Start test userProfURLChangeTest");
        String oldURLInfo = userProfPage.getProfURLInfo();
        userProfPage.setProfURLInfo(newURLInfo);
        userProfPage.btnUpdProfClick();
        userProfPage.refreshPage();
        String tmpURLInfo = userProfPage.getProfURLInfo();
        Assert.assertNotEquals(tmpURLInfo, oldURLInfo);
    }

    @Parameters({"newcompany-info"})
    @Test (description ="Check of changing user company availability", enabled = true)
    public void  userProfCompanyChangeTest(String newCompanyInfo) {
        logger.info("Start test userProfCompanyChangeTest");
        String oldCompanyInfo = userProfPage.getProfCompany();
        userProfPage.setProfCompany(newCompanyInfo);
        userProfPage.btnUpdProfClick();
        userProfPage.refreshPage();
        String tmpCompanyInfo = userProfPage.getProfCompany();
        Assert.assertNotEquals(tmpCompanyInfo, oldCompanyInfo);
    }

    @Parameters({"newloc-info"})
    @Test (description ="Check of changing user location availability", enabled = true)
    public void userProfLocationChangeTest(String newLocationInfo) {
        logger.info("Start test userProfCompanyChangeTest");
        String oldLocationInfo = userProfPage.getProfLocation();
        userProfPage.setProfLocation(newLocationInfo);
        userProfPage.btnUpdProfClick();
        userProfPage.refreshPage();
        String tmpLocationInfo = userProfPage.getProfLocation();
        Assert.assertNotEquals(tmpLocationInfo, oldLocationInfo);
    }
}
