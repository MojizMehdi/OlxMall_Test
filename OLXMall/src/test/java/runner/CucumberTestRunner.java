package runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(plugin = {"pretty","helper.MyTestListener",
        "html:target/cucumber-reports/cucumber.html","json:target/cucumber-reports/cucumber.json"},

        features = "src/test/java/features/ProductListing.feature",
        glue = {"stepDefinitions","WebSteps"},
        dryRun = false)


public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
