package st.runner;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(
        strict = false,
        features = "Features",
        glue = {"st.glue"},
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:report/report.html"},
        format = {"html:target/site/cucumber-pretty", "json:target/cucumber.json"},
        tags = {"@Login_OpenMainArticle"})



public class TestRunner {

}
