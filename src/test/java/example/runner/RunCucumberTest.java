package example.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author Di
 * @since 23/03/18
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/example/features",
        glue = "example/stepdefs",
        plugin = {"pretty", "json:build/cucumber-reports/json-report/cucumber.json"})
public class RunCucumberTest {
}
