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

    @FindBy(css = "input#repository_name")
    private WebElement fNewRepoName;

    @FindBy(xpath = "//*[@id='new_repository']/div[4]/button")
    private WebElement btnNewRepoCreate;

    @FindBy(css = "input#repository_auto_init")
    private WebElement chboxAutoInit;



    public NewRepoPage(WebDriver webDrv) {
        super(webDrv);
        PageFactory.initElements(this.webDriver, this);
    }

    public void btnNewRepoCreateClick() {
        btnNewRepoCreate.click();
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
        this.btnNewRepoCreateClick();
        (new WebDriverWait(webDriver, 2)).until(ExpectedConditions
                .presenceOfElementLocated(REPONAME_LINK));

        result = webDriver.findElement(REPONAME_LINK)
                .getText().equals(repo_name);
        return result;
    }

}
