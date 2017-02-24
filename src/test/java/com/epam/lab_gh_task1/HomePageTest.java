package com.epam.lab_gh_task1;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends PageTest {


//  @BeforeMethod
//  public void initPageObjects() {
//    homePage = PageFactory.initElements(driver, HomePage.class);
//  }

    @Test
    public void testDumbTest() {
        //driver.get(baseUrl);
        logger.info("Run Dumb test");
        Assert.assertFalse(false);
    }
}


