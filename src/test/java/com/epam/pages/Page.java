package com.epam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class Page {

  protected WebDriver webDriver;


  public Page(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public String getTitle() {
    return webDriver.getTitle();
  }

  public void refreshPage() {
    this.webDriver.navigate().refresh();
    PageFactory.initElements(this.webDriver, this);
  }


}
