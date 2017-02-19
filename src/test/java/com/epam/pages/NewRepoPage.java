package com.epam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;



/**
 * Created by shaman on 18.02.17.
 */
public class NewRepoPage extends Page {
    private final static String REPO_NAME = "test_repo";

    @FindBy(id = "repository_name")
    private WebElement fNewRepoName;

    @FindBy(xpath = "//*[@id=\"new_repository\"]/div[4]/button")
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
        (new WebDriverWait(webDriver, 10)).until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(".//strong[@itemprop='name']")));

        result = webDriver.findElement(By.xpath(".//strong[@itemprop='name']"))
                .getText().equals(repo_name);
        //webDriver.navigate().back();
        return result;
    }

}
