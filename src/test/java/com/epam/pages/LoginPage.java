package com.epam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Vladimir_Kotovich on 2/17/2017.
 */
public class LoginPage extends Page{

    @FindBy (name = "login")
    private WebElement fUName;

    @FindBy (name = "password")
    private WebElement fUPass;

    public LoginPage (WebDriver webDrv) {
        super(webDrv);
        PageFactory.initElements(this.webDriver, this);
    }

    public HomePage login (String username, String password) {
        fUName.clear();
        fUName.sendKeys(username);
        fUPass.clear();
        fUPass.sendKeys(password);
        fUPass.submit();
        return new HomePage(this.webDriver);
    }
}
