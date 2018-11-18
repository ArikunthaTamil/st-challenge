package st.glue;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import st.utility.*;
import st.wrappers.TestDataValue;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.cucumber.listener.Reporter;
import com.relevantcodes.extentreports.ExtentTest;
import st.utility.Log;
import st.utility.PropertyFileReader;
import st.utility.TestData;
import st.wrappers.DriverFactory;
import st.wrappers.Elementwrappers;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class BaseGlue extends DriverFactory {

    public static RemoteWebDriver driver = null;
    public static Scenario scenario;
    public static String platform;
    public static String mobilePlatform = null;
    public static String timestampToAppend;
    public static HashMap<String, String> testDataHM;
    public static String testcaseId="";
    public static ExtentTest test;
    public static boolean isContextInWebView=false;
    public static String appiumManualStart;

    boolean isBeforeScenario=false;

    @Before
    public void beforeScenario(Scenario scenario) throws Exception {
        try {
            this.scenario = scenario;
            PropertyFileReader.setSystemProperties();
            timestampToAppend = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
            System.setProperty("timestampToAppend", timestampToAppend);
            DOMConfigurator.configure("log4j.xml");
            Log.startTestCase("Name");
            Log.info("Info : SCENARIO Started - " + scenario.getName());
            testcaseId = scenario.getName().substring(scenario.getName().indexOf("\"")+1, scenario.getName().indexOf("-")-2);
            Log.info("Info : Testcase Id - " + testcaseId);
            testDataHM = TestData.testData();
            TestDataValue.setTestData(testcaseId);
            platform = System.getProperty("platform");
            appiumManualStart = System.getProperty("appiumManualStart");
            Reporter.loadXMLConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
            Reporter.addScenarioLog(scenario.getName());
            boolean isTrue=AppiumServer.startServer();
            if (isTrue) {
                driver = initialize();
                Thread.sleep(10000);
                isBeforeScenario=true;
            }
        } catch (Exception e) {
            Log.info("Error : Before Scenario - "+e);
        }
    }

    @After
    public void afterScenario(Scenario scenario) throws Exception {
        try {
            Log.info("Info : After scenario");
            this.scenario = scenario;
            Log.info("Info : RESULT - " + scenario.getStatus() + " -- Scenario : " + scenario.getName());
            String screenShotName = Elementwrappers.screenshot(driver, scenario.getName(), scenario.getStatus());
            Reporter.addScreenCaptureFromPath(screenShotName);
            isContextInWebView=false;
            destroyDriver();
            Log.stopTestCase("End Test Case");
        } catch (Exception e) {
            Log.info("Error : After Scenario - "+e);
        }
    }

}
