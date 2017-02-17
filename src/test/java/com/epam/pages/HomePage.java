package com.epam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Sample page
 */
public class HomePage extends Page {

  @FindBy(xpath = ".//img[@class='avatar']")
  private WebElement ddMenuView;

  @FindBy(xpath = ".//a[contains(text(),'Your profile')]")
  private WebElement ddMenuProfile;

  @FindBy(xpath = ".//a[contains(text(),'Settings')]")
  private WebElement ddMenuSettings;



  public HomePage(WebDriver webDrv) {
    super(webDrv);
    PageFactory.initElements(this.webDriver, this);
  }

  public UserProfilePage ddMenuProfileClick() {
    ddMenuView.click();
    ddMenuSettings.click();
    return new UserProfilePage(this.webDriver);
  }




}
