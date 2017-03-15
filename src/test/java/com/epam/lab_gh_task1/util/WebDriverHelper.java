package com.epam.lab_gh_task1.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class WebDriverHelper {

    private static Map<String, WebDriver> driverList = new HashMap<>();


    public static WebDriver getDriver(DesiredCapabilities cap) {
        WebDriver driver;
        String browserName = cap.getBrowserName();
        switch (browserName) {
            case "chrome":
                if (!isAllreadyRuns(browserName)) {
                    driver = new ChromeDriver(cap);
                    driverList.put("chrome", driver);
                }
                break;
            case "firefox":
                if (!isAllreadyRuns(browserName)) {
                    driver = new FirefoxDriver(cap);
                    driverList.put("firefox", driver);
                }
                break;
            case "opera":
                if (!isAllreadyRuns(browserName)) {
                    driver = new OperaDriver(cap);
                    driverList.put("opera", driver);
                }
                break;
        }
        return driverList.get(browserName);
    }

    private static boolean isAllreadyRuns(String driverName) {
        boolean result = false;
        for (String key: driverList.keySet()) {
            if (key.equals(driverName)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static void cLoseAllDrivers() {
        for (WebDriver wd: driverList.values()) {
           wd.quit();
           wd = null;
        }
    }

}
