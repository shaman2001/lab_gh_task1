package com.epam.lab_gh_task1;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends PageTest {


    @Test
    public void testDumbTest() {
        //driver.get(baseUrl);
        LOG.info("Run Dumb test");
        Assert.assertFalse(false);
    }
}


