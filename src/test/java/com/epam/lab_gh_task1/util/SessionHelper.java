package com.epam.lab_gh_task1.util;

import com.epam.lab_gh_task1.pages.HomePage;
import com.epam.lab_gh_task1.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.NoSuchElementException;

public class SessionHelper {

    private static final By DROPDOWN_USERMENU_HEADER = By.xpath(".//div[contains(text(),'Signed in as')]");
    private static final By DROPDOWN_USERMENU = By.xpath("//ul[@id='user-links']/li[3]/a");
    private static final By DROPDOWN_USERMENU_SIGNOUT = By.cssSelector("button.dropdown-item.dropdown-signout");
    private static final By PLACE_FOR_CLICK = By.cssSelector("div.modal-backdrop.js-touch-events");
    private static final By DROPDOWN_USERMENU_HEADER2 = By.cssSelector("div.dropdown-header.header-nav-current-user.css-truncate");


    private static boolean isSigned(WebDriver webDriver) {
        WebElement webElement = null;
        try {
            webElement = webDriver.findElement(DROPDOWN_USERMENU_HEADER);
        } catch (Exception e) {
            System.err.println("Profile dropdown menu is not found");
        }
        return webElement != null;
    }

    private static boolean isSignedAs(WebDriver webDriver, String userName) {
        boolean result;
        String  logStr[] = null;
        try {
            webDriver.findElement(DROPDOWN_USERMENU).click();
            logStr = webDriver.findElement(DROPDOWN_USERMENU_HEADER2)
                    .getText().trim().split(" ");
            //click somewhere for closing drop down menu
            webDriver.findElement(PLACE_FOR_CLICK).click();
        } catch (Exception e) {
            System.err.println("Dropdown menu 'Sign out' not found");
        }
        result = logStr != null && logStr[logStr.length-1].equals(userName);
        return result;
    }

    private static void signOut(WebDriver webDriver) {
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

        final String CHROMEDRIVER_LIN_PATH=PropertyLoader.getProperty("cromedriver.lin.path");
        final String CHROMEDRIVER_WIN_PATH=PropertyLoader.getProperty("cromedriver.win.path");
        final String OPERADRIVER_LIN_PATH=PropertyLoader.getProperty("operadriver.lin.path");
        final String OPERADRIVER_WIN_PATH=PropertyLoader.getProperty("operadriver.win.path");
        final String GECKODRIVER_LIN_PATH=PropertyLoader.getProperty("geckodriver.lin.path");
        final String GECKODRIVER_WIN_PATH=PropertyLoader.getProperty("geckodriver.win.path");

        switch (browser) {
            case "chrome":
                if (OSRecognizer.isUnix()) {
                    System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_LIN_PATH);
                } else if (OSRecognizer.isWindows()) {
                    System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_WIN_PATH);
                } else {
                    System.out.println("OS is not supported");
                    break;
                }
                capabilities = DesiredCapabilities.chrome();
                break;
            case "opera": ////не пашет редиска
                if (OSRecognizer.isUnix()) {
                    System.setProperty("webdriver.opera.driver", OPERADRIVER_LIN_PATH);
                } else if (OSRecognizer.isWindows()) {
                    System.setProperty("webdriver.opera.driver", OPERADRIVER_WIN_PATH);
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
                    System.setProperty("webdriver.gecko.driver", GECKODRIVER_LIN_PATH);
                } else if (OSRecognizer.isWindows()) {
                    System.setProperty("webdriver.gecko.driver", GECKODRIVER_WIN_PATH);
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
