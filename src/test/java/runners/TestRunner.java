package runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		 features = "src/features", 
		glue = {"stepDefinitions", "hooks"},
		plugin = {"pretty",
				"html:target/cucumber-reports/CucumberReport.html",
				},
		monochrome = true
		)

public class TestRunner extends AbstractTestNGCucumberTests{
	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios(){
		return super.scenarios();
	}
	
}
