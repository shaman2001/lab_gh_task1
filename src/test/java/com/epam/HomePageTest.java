package com.epam;

import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.pages.HomePage;

public class HomePageTest extends PageTest {



//  @BeforeMethod
//  public void initPageObjects() {
//    homePage = PageFactory.initElements(driver, HomePage.class);
//  }

  @Test
  public void testDumbTest() {
    //driver.get(baseUrl);
    Assert.assertFalse(false);
  }
}


