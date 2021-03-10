package petStoreWithBDD;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/petStoreWithBDD"}, plugin = {"json:target/cucumber.json", "pretty", "html:target/cucumber-reports"}, glue = {"petStoreWithBDD"}, dryRun = false, monochrome = true)
public class petStoreTestRunner {
}
