package runner;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/featuresFiles",
        glue= {"stepDefinations"},
        //tags= "@AddGetDeletePlace",
		plugin={
				"json:target/jsonReports/cucumber-report.json",
				"html:target/htmlReports/cucumber-report.html"
		}
)
public class TestRunner {

}