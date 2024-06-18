package allcom_cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"allcom_cucumber.stepDefinitions"},
        plugin = {"pretty", "json:build/cucumber-report/cucumber.json"})
//public class TestRunner {
//}
