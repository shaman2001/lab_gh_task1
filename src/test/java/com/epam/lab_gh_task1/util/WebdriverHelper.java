package com.epam.lab_gh_task1.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.HashMap;
import java.util.Map;

public class WebdriverHelper {

    private static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>() {{
        drivers.put("chrome",new ChromeDriver());
        drivers.put("opera",new OperaDriver());
        drivers.put("firefox",new FirefoxDriver());
    }};

    public static WebDriver getDriver (String name){
        return drivers.get(name);
    }

}
