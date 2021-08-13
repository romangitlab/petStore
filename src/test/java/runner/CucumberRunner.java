package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/features"}, plugin = {"json:target/cucumber.json", "pretty"},
        glue = {"steps"},
        publish = true
)
public class CucumberRunner extends AbstractTestNGCucumberTests {
}
