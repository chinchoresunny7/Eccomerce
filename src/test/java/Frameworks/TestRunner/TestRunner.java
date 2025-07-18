package Frameworks.TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Frameworks/Features/",glue = "Frameworks.Steps",
monochrome = true, tags = "@Regression", plugin = {"html:Reports/cucumber/report.html"})
public class TestRunner extends AbstractTestNGCucumberTests
{
}
