package com.epam.lab_gh_task1.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class Page {

    protected WebDriver webDriver;


    public Page(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
        System.out.println("Open page with title: " + getTitle());
    }

    public String getTitle() {
        return webDriver.getTitle();
    }

    public void refreshPage() {
        this.webDriver.navigate().refresh();
    }

    public void clickOnElementWithJS(WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", webElement);
    }


}
