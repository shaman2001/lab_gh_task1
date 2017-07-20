package com.epam.lab_gh_task1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewRepoPage extends Page {

    private static final By REPONAME_LINK = By.xpath(".//strong[@itemprop='name']");
    private static final By BTN_NEW_REPO = By.xpath("//button[@type='submit' and normalize-space(text())='Create repository']");

    @FindBy(css = "input#repository_name")
    private WebElement fNewRepoName;

    @FindBy(xpath = "//button[@type='submit' and normalize-space(text())='Create repository']")
    private WebElement btnNewRepoCreate;
    //button[href*="w3schools"]

    @FindBy(css = "input#repository_auto_init")
    private WebElement chboxAutoInit;



    public NewRepoPage(WebDriver webDrv) {
        super(webDrv);
    }

    public void btnNewRepoCreateClick() {
        //btnNewRepoCreate.click();
        clickOnElementWithJS(btnNewRepoCreate);
    }

    public void setRepoName(String val) {
        fNewRepoName.sendKeys(val);
    }

    public void setAutoInitChBox() {
        if (!chboxAutoInit.isSelected()) {
            chboxAutoInit.click();
        }
    }

    public boolean createNewRepo(String repo_name) {
        boolean result = false;
        this.setRepoName(repo_name);
        this.setAutoInitChBox();
        (new WebDriverWait(webDriver, 15)).until(ExpectedConditions
                .elementToBeClickable(BTN_NEW_REPO)).click();
        WebElement txtBoxRepoName = (new WebDriverWait(webDriver, 15)).until(ExpectedConditions
                .presenceOfElementLocated(REPONAME_LINK));

        //result = txtBoxRepoName.getText().equals(repo_name);
        return txtBoxRepoName.getText().equals(repo_name);
    }

}
