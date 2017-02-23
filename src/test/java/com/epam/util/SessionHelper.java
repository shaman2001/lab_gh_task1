package com.epam.util;

import com.epam.pages.HomePage;
import com.epam.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.NoSuchElementException;

public class SessionHelper {

    private final static String GITHUB_LOGIN_URL = "https://github.com/login";
    private final static String GITHUB_HOME_URL = "https://github.com/";
    private final static String OPERA_BROWSER_LOCATION = "C:\\Program Files\\Opera\\43.0.2442.991\\opera.exe";
    private final static String CHROME_BROWSER_LOCATION = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
    private final static String CHROMEDRIVER_WIN_PATH="c:\\selenium\\chromedriver.exe";
    private final static String CHROMEDRIVER_LIN_PATH="/home/shaman/mywebdrivers/chromedriver";
    private final static String OPERADRIVER_WIN_PATH="c:\\selenium\\operadriver.exe";
    private final static String OPERADRIVER_LIN_PATH="/home/shaman/mywebdrivers/operadriver";
    private final static String GECKODRIVER_WIN_PATH="c:\\selenium\\geckodriver.exe";
    private final static String GECKODRIVER_LIN_PATH="/home/shaman/mywebdrivers/geckodriver";

    public static final By DROPDOWN_USERMENU_HEADER = By.xpath(".//div[contains(text(),'Signed in as')]");
    public static final By DROPDOWN_USERMENU = By.xpath("//ul[@id='user-links']/li[3]/a");
    public static final By DROPDOWN_USERMENU_SIGNOUT = By.cssSelector("button.dropdown-item.dropdown-signout");
    public static final By PLACE_FOR_CLICK = By.cssSelector("div.modal-backdrop.js-touch-events");



    public static boolean isSigned(WebDriver webDriver) {
        WebElement webElement = null;
        try {
            webElement = webDriver.findElement(DROPDOWN_USERMENU_HEADER);
        } catch (Exception e) {
            System.err.println("Profile dropdown menu is not found");
        }
        return webElement != null;
    }

    public static boolean isSignedAs(WebDriver webDriver, String userName) {
        boolean result;
        String  logStr[] = null;
        try {
            webDriver.findElement(DROPDOWN_USERMENU).click();
            logStr = webDriver.findElement(By.cssSelector("div.dropdown-header.header-nav-current-user.css-truncate"))
                    .getText().trim().split(" ");
            //click somewhere for closing drop down menu
            webDriver.findElement(PLACE_FOR_CLICK).click();
        } catch (Exception e) {
            System.err.println("Dropdown menu 'Sign out' not found");
        }
        result = logStr != null && logStr[logStr.length-1].equals(userName);
        return result;
    }

    public static void signOut(WebDriver webDriver) {
        WebElement ddMenu = null;
        try {
            ddMenu = webDriver.findElement(DROPDOWN_USERMENU);
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        if (ddMenu == null) {
            webDriver.get(PropertyLoader.getProperty("home.url"));
            ddMenu = webDriver.findElement(DROPDOWN_USERMENU);
        }
        ddMenu.click();
        webDriver.findElement(DROPDOWN_USERMENU_SIGNOUT).click();
    }

    public static DesiredCapabilities getBrowserCaps(String browser) {
        DesiredCapabilities capabilities = null;
        switch (browser) {
            case "chrome":
                if (OSRecognizer.isUnix()) {
                    System.setProperty("webdriver.chrome.driver",PropertyLoader.getProperty("cromedriver.lin.path"));
                } else if (OSRecognizer.isWindows()) {
                    System.setProperty("webdriver.chrome.driver",PropertyLoader.getProperty("cromedriver.win.path"));
                } else {
                    System.out.println("OS is not supported");
                    break;
                }
                capabilities = DesiredCapabilities.chrome();
                break;
            case "opera": ////не пашет редиска
                if (OSRecognizer.isUnix()) {
                    System.setProperty("webdriver.opera.driver",PropertyLoader.getProperty("operadriver.lin.path"));
                } else if (OSRecognizer.isWindows()) {
                    System.setProperty("webdriver.opera.driver",PropertyLoader.getProperty("operadriver.win.path"));
                } else {
                    System.out.println("OS is not supported");
                    break;
                }
                capabilities = DesiredCapabilities.operaBlink();
                ChromeOptions options = new ChromeOptions();
                //OperaOptions options = new OperaOptions();
                options.setBinary(PropertyLoader.getProperty("opera.browser.location")); //?
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                //capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                capabilities.setBrowserName("operablink");
                break;
            case "firefox": ////и морковка не пашет
                if (OSRecognizer.isUnix()) {
                    System.setProperty("webdriver.gecko.driver",PropertyLoader.getProperty("geckodriver.lin.path"));
                } else if (OSRecognizer.isWindows()) {
                    System.setProperty("webdriver.gecko.driver",PropertyLoader.getProperty("geckodriver.win.path"));
                } else {
                    System.out.println("OS is not supported");
                }
                capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("marionette", true);
                capabilities.setBrowserName("firefox");
                break;
            default:
                System.out.println("Browser is not supported");
            }
        return capabilities;
    }

    public static HomePage ensureSignIn (WebDriver webDriver, String userName, String userPass) {
        String githubLoginURL = PropertyLoader.getProperty("login.url");
        String githubHomeURL = PropertyLoader.getProperty("home.url");
        HomePage homePage;
        if (!isSigned(webDriver)) { //if is not signed
            webDriver.get(githubLoginURL);
            homePage = new LoginPage(webDriver).login(userName, userPass);
        } else {
            if (!isSignedAs(webDriver, userName)) { //if signed with incorrect %username%
                signOut(webDriver);
                webDriver.get(githubLoginURL);
                homePage = new LoginPage(webDriver).login(userName, userPass);
            } else { //if signed with correct %username%
                webDriver.get(githubHomeURL);
                homePage = new HomePage(webDriver);
            }
        }
        return homePage;
    }

}
