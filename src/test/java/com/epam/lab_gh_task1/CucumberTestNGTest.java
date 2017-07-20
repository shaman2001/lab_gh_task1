package com.epam.lab_gh_task1;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(plugin={"json:target/cucumber-report.json",
                        "html:cucumber-report"},
                features = "src/test/resources/cucumber/githubtests.feature",
                tags = {"@SmokeTest"},
                glue = {"com.epam.lab_gh_task1.steps"})
public class CucumberTestNGTest extends AbstractTestNGCucumberTests {

}
