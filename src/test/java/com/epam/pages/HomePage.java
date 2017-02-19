package com.epam.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Alert;

/**
 * Sample page
 */
public class HomePage extends Page {
    private final static String USERNAME = "shaman2001";

  @FindBy(xpath = ".//img[@class='avatar']")
  private WebElement ddMenuView;

  @FindBy(xpath = ".//a[contains(text(),'Your profile')]")
  private WebElement ddMenuProfile;

  @FindBy(xpath = ".//a[contains(text(),'Settings')]")
  private WebElement ddMenuSettings;

  @FindBy(css = "a.btn.btn-sm.btn-primary")
  private WebElement btnNewRepo;

  @FindBy(xpath = ".//span[@class='repo']")
  private List<WebElement> listRepos;

  public HomePage(WebDriver webDrv) {
    super(webDrv);
    PageFactory.initElements(this.webDriver, this);
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
      boolean result = false;
      WebElement repo_link;
    //click repo link if exists
      if ((repo_link = getRepoLink(repo_name)) != null) {
          repo_link.click();
          //wait for settings tab to be clickable and click it
          (new WebDriverWait(webDriver, 10)).until(ExpectedConditions
                    .presenceOfElementLocated(By.cssSelector("a:nth-child(8)")));
          //By.cssSelector("a:nth-child(8)").findElement(webDriver).click();
          webDriver.findElement(By.xpath(".//a[@class='js-selected-navigation-item reponav-item'][contains(@href, 'settings')]")).click();
          //wait for "delete repo page" load
          (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.
                  presenceOfElementLocated(By.xpath(".//button[@type='button'][3]")));
          //if rename field contains not correct repo name then exit with return false
          if (!webDriver.findElement(By.id("rename_field")).getAttribute("value").equals(repo_name)) {
              return false;
          }
          //click button "Delete this repository"
          webDriver.findElement(By.xpath(".//button[@type='button'][3]")).click();
          //pass the verify procedure
          String myWindowHandle = webDriver.getWindowHandle();
          webDriver.switchTo().window(myWindowHandle);
          WebElement fVerify = webDriver.findElement(By.cssSelector("#facebox>div>div>form>p>input"));
          //alert.sendKeys(USERNAME + "/" + repo_name);
          //alert.accept();
          String str = USERNAME + "/" + repo_name;
//          new Actions(this.webDriver).sendKeys(fVerify, str).build().perform();
          fVerify.sendKeys(str);
          fVerify.submit();
          PageFactory.initElements(this.webDriver, this);
          result = getRepoLink(repo_name) == null;
      }
      return result;
  }

  public WebElement getRepoLink(String repo_name) {
      WebElement result = null;
//      int len = listRepos.size();
      for (WebElement el: listRepos) {
//          String str = el.getText();
          if (el.getText().equals(repo_name)) {
              result = el;
              break;
          }
      }
      return result;
  }



}
