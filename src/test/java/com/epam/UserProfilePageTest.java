package com.epam;

import com.epam.pages.UserProfilePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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

    @Test (description="Check of changing user bio availability",enabled = true)
    public void userProfBioChangeTest() {
        logger.info("Start test userProfBioChangeTest");
        String oldBioInfo = userProfPage.getProfBioInfo();
        userProfPage.setProfBioInfo(NEWBIOINFO);
        userProfPage.btnUpdProfClick();
        String newBioInfo = userProfPage.getProfBioInfo();
        Assert.assertNotEquals(newBioInfo, oldBioInfo);
    }

    @Test (description="Check of changing user URL availability",enabled = true)
    public void userProfURLChangeTest() {
        logger.info("Start test userProfURLChangeTest");
        String oldURLInfo = userProfPage.getProfURLInfo();
        userProfPage.setProfURLInfo(NEWURLINFO);
        userProfPage.btnUpdProfClick();
        String newURLInfo = userProfPage.getProfURLInfo();
        Assert.assertNotEquals(newURLInfo, oldURLInfo);
    }
    @Test (description ="Check of changing user company availability", enabled = true)
    public void  userProfCompanyChangeTest() {
        logger.info("Start test userProfCompanyChangeTest");
        String oldCompanyInfo = userProfPage.getProfCompany();
        userProfPage.setProfCompany(NEWCOMPANYINFO);
        userProfPage.btnUpdProfClick();
        String newCompanyInfo = userProfPage.getProfCompany();
        Assert.assertNotEquals(newCompanyInfo, oldCompanyInfo);
    }
    @Test (description ="Check of changing user location availability", enabled = true)
    public void userProfLocationChangeTest() {
        logger.info("Start test userProfCompanyChangeTest");
        String oldLocationInfo = userProfPage.getProfLocation();
        userProfPage.setfProfLocation(NEWLOCINFO);
        userProfPage.btnUpdProfClick();
        String newLocationInfo = userProfPage.getProfLocation();
        Assert.assertNotEquals(newLocationInfo, oldLocationInfo);
    }
}
