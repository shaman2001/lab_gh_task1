package com.epam.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.NoSuchElementException;

/**
 * Created by shaman on 20.02.17.
 */
public class SessionHelper {

    private final static String GITHUB_HOME_URL = "https://github.com/";
    private final static String CHROMEDRIVER_WIN_PATH="c:\\selenium\\chromedriver.exe";
    private final static String CHROMEDRIVER_LIN_PATH="/home/shaman/mywebdrivers/chromedriver";
    private final static String OPERADRIVER_WIN_PATH="c:\\selenium\\operadriver.exe";
    private final static String OPERADRIVER_LIN_PATH="/home/shaman/mywebdrivers/operadriver";
    private final static String GECKODRIVER_WIN_PATH="c:\\selenium\\geckodriver.exe";
    private final static String GECKODRIVER_LIN_PATH="/home/shaman/mywebdrivers/geckodriver";

    public static boolean isSigned(WebDriver webDriver) {
        WebElement webElement = null;
        try {
            webElement = webDriver.findElement(By.xpath(".//div[contains(text(),'Signed in as')]"));
        } catch (Exception e) {
            System.err.println("Profile dropdown menu is not found");
            //logger.error("Profile dropdown menu is not found");
        }
        return webElement != null;
    }

    public static boolean isSignedAs(WebDriver webDriver, String userName) {
        boolean result = false;
        String  logStr[] = null;
        try {
            webDriver.findElement(By.xpath("//ul[@id='user-links']/li[3]/a")).click();
            logStr = webDriver.findElement(By.cssSelector("div.dropdown-header.header-nav-current-user.css-truncate"))
                    .getText().trim().split(" ");
            //click somewhere for closing drop down menu
            webDriver.findElement(By.cssSelector("div.modal-backdrop.js-touch-events")).click();
        } catch (Exception e) {
            System.err.println("Dropdown menu 'Sign out' not found");
            //logger.error("Dropdown menu 'Sign out' not found");
        }
        result = logStr != null && logStr[logStr.length-1].equals(userName);
        return result;
    }

    public static void logout(WebDriver webDriver) {
        WebElement ddMenu = null;
        try {
            ddMenu = webDriver.findElement(By.xpath("//ul[@id='user-links']/li[3]/a"));
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        if (ddMenu == null) {
            webDriver.get(GITHUB_HOME_URL);
            ddMenu = webDriver.findElement(By.xpath("//ul[@id='user-links']/li[3]/a"));
        }
        ddMenu.click();
        webDriver.findElement(By.cssSelector("button.dropdown-item.dropdown-signout")).click();
    }

    public static DesiredCapabilities getBrowserCaps(String browser) {
        DesiredCapabilities capabilities = null;
        switch (browser) {
            case "chrome":
                if (OSRecognizer.isUnix()) {
                    System.setProperty("webdriver.chrome.driver",CHROMEDRIVER_LIN_PATH);
                } else if (OSRecognizer.isWindows()) {
                    System.setProperty("webdriver.chrome.driver",CHROMEDRIVER_WIN_PATH);
                } else {
                    System.out.println("OS is not supported");
                    break;
                }
                capabilities = DesiredCapabilities.chrome();
                break;
            case "opera":
                if (OSRecognizer.isUnix()) {
                    System.setProperty("webdriver.opera.driver",OPERADRIVER_LIN_PATH);
                } else if (OSRecognizer.isWindows()) {
                    System.setProperty("webdriver.opera.driver",OPERADRIVER_WIN_PATH);
                } else {
                    System.out.println("OS is not supported");
                    break;
                }
                capabilities = DesiredCapabilities.operaBlink();
                break;
            case "firefox":
                if (OSRecognizer.isUnix()) {
                    System.setProperty("webdriver.gecko.driver",GECKODRIVER_LIN_PATH);
                } else if (OSRecognizer.isWindows()) {
                    System.setProperty("webdriver.gecko.driver",GECKODRIVER_WIN_PATH);
                } else {
                    System.out.println("OS is not supported");
                }
                capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("marionette", true);
                //capabilities.setBrowserName("firefox");
                break;
            default:
                System.out.println("Browser is not supported");
            }
        return capabilities;
    }

}
