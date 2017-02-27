package com.epam.lab_gh_task1.pages;

import com.epam.lab_gh_task1.util.PropertyLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;


public class HomePage extends Page {
//    private final static String USERNAME = "shaman2001";

//    private static final By SETTINGS_TABLE_LOCATOR = By.cssSelector("a:nth-child(8)");
    private static final By SETTINGS_TABLE_LOCATOR1 = By.xpath(".//a[@class='js-selected-navigation-item reponav-item'][contains(@href, 'settings')]");
    private static final By BUTTON_DELETE_THIS_REPOSITORY = By.xpath("//button[contains(text(),'Delete this repository')]");
    private static final By FIELD_ENTER_REPO_NAME = By.cssSelector("#facebox>div>div>form>p>input");

    @FindBy(xpath = ".//img[@class='avatar']") //for chrome
    //@FindBy(xpath = "//a[@class='header-nav-link name tooltipped tooltipped-sw js-menu-target']") //for firefox
    private WebElement ddMenuView;

    //@FindBy(xpath = ".//a[contains(text(),'Your profile')]") //for chrome
    @FindBy(xpath = "//a[@class='dropdown-item'][1]") //for firefox
    private WebElement ddMenuProfile;


    //@FindBy(xpath = ".//a[contains(text(),'Settings')]") //for chrome
    @FindBy(xpath = "//a[@class='dropdown-item'][6]") //for firefox
    private WebElement ddMenuSettings;

    @FindBy(css = "a.btn.btn-sm.btn-primary")
    private WebElement btnNewRepo;

    @FindBy(xpath = ".//span[@class='repo']")
    private List<WebElement> listRepos;

    public HomePage(WebDriver webDrv) {
        super(webDrv);
        //this.webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        PageFactory.initElements(this.webDriver, this);
        (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.
                        elementToBeClickable(ddMenuView));
    }

    public UserProfilePage ddMenuProfileClick() {
        ddMenuView.click();
        ddMenuSettings.click();
        return new UserProfilePage(this.webDriver);
    }

    public NewRepoPage btnNewRepClick() {
        btnNewRepo.click();
        return new NewRepoPage(this.webDriver);
    }

    public boolean delRepo(String repo_name) {
        //click repo link if exists
        if (isRepoExists(repo_name)) {
            getExistingRepo(repo_name).click();
            //wait for settings tab to be present and click it
            (new WebDriverWait(webDriver, 3)).until(ExpectedConditions
                    .presenceOfElementLocated(SETTINGS_TABLE_LOCATOR1));
            webDriver.findElement(SETTINGS_TABLE_LOCATOR1).click();
            //wait for "delete repo page" load
            (new WebDriverWait(webDriver, 3)).until(ExpectedConditions.
                    presenceOfElementLocated(BUTTON_DELETE_THIS_REPOSITORY));
            webDriver.findElement(BUTTON_DELETE_THIS_REPOSITORY).click();
            //pass the verify procedure
            String confirmationPopup = webDriver.getWindowHandle();
            webDriver.switchTo().window(confirmationPopup);
            WebElement fVerify = webDriver.findElement(FIELD_ENTER_REPO_NAME);
            String str = PropertyLoader.getProperty("user.name") + "/" + repo_name;
            fVerify.sendKeys(str);
            fVerify.submit();
            PageFactory.initElements(this.webDriver, this);
            //webDriver.switchTo().defaultContent();
        }
        return isRepoExists(repo_name);
    }

    private boolean isRepoExists(String name){
        return !(getExistingRepo(name) == null);
    }

    public WebElement getExistingRepo(String repo_name) {
        WebElement result = null;
        for (WebElement el : listRepos) {
            if (el.getText().equals(repo_name)) {
                result = el;
                break;
            }
        }
        return result;
    }


}
