package com.epam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Vladimir_Kotovich on 2/17/2017.
 */
public class UserProfilePage extends Page {


    @FindBy (xpath = ".//input[@id='user_profile_name']")
    private WebElement fProfName;

    @FindBy(xpath = ".//a[contains(@href,'mailto')]")
    private WebElement fProfEmail;

    @FindBy(xpath = ".//textarea[@id='user_profile_bio']")
    private WebElement fProfBio;

    @FindBy(xpath = ".//input[@id='user_profile_blog']")
    private WebElement fProfURL;

    @FindBy(xpath = ".//input[@id='user_profile_company']")
    private WebElement fProfCompany;

    @FindBy(xpath = ".//input[@id='user_profile_location']")
    private WebElement fProfLocation;

    @FindBy(xpath = ".//p/button[@class='btn']")
    private WebElement btnUpdProfile;

    public UserProfilePage(WebDriver webDrv) {
        super(webDrv);
        PageFactory.initElements(this.webDriver, this);
    }

    public String getProfName() {
        return this.fProfName.getAttribute("value");
    }

    public String getProfEmail() {
        return this.fProfEmail.getAttribute("value");
    }

    public String getProfBioInfo() {
        return  this.fProfBio.getAttribute("value");
    }

    public void setProfBioInfo(String val) {
        this.fProfBio.sendKeys(val);
    }

    public void  clearProfBioInfo() {
        this.fProfBio.clear();
    }

    public String getProfURLInfo() {
        return  this.fProfURL.getAttribute("value");
    }

    public void setProfURLInfo(String val) {
        this.fProfURL.sendKeys(val);
    }

    public void  clearProfURLInfo() {
        this.fProfURL.clear();
    }

    public String getProfCompany() {
        return  this.fProfCompany.getAttribute("value");
    }

    public void setProfCompany(String val) {
        this.fProfCompany.sendKeys(val);
    }

    public void  clearProfCompany() {
        this.fProfCompany.clear();
    }

    public String getProfLocation() {
        return  this.fProfLocation.getAttribute("value");
    }

    public void setfProfLocation(String val) {
        this.fProfLocation.sendKeys(val);
    }

    public void  clearProfLocation() {
        this.fProfLocation.clear();
    }

    public void btnUpdProfClick() {
        btnUpdProfile.click();
    }


}
