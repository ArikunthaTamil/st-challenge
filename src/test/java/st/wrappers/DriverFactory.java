package st.wrappers;

import st.glue.BaseGlue;
import st.pages.BasePage;
import st.pages.LoginMobilePage;
import st.utility.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;

/**
 * This class is used to create drivers based on the input from the config
 * properties files
 */

public class DriverFactory extends Elementwrappers {

    public static RemoteWebDriver driver = null;
    public static int maxElementWait = 4;
    public static String platformName;

    /**
     * This method initialize and returns the driver
     *
     * @throws Exception
     */
    public RemoteWebDriver initialize() throws Exception {
        if (driver == null) {
            Log.info("Info : Initializing Driver - New");

            this.driver = getDriver();
            if (System.getProperty("platform").equalsIgnoreCase("Desktop")) {
                createNewInstanceDriver();
                Log.info("Info : Desktop Driver initialized");
            } else {
                createNewMobileInstanceDriver();
                BaseGlue.mobilePlatform = DriverFactory.getMobilePlatform(driver);
                Log.info("Info : Mobile Driver initialized");
            }
        } else {
            Log.info("Info - Initializing Driver : Existing");
            this.driver = getDriver();
        }
        return driver;
    }

    /**
     * This method creates the driver based on the input from the
     * config.properties files
     *
     * @throws Exception
     */
    private void createNewInstanceDriver() throws Exception {
        String platform = System.getProperty("platform");
        String browser = System.getProperty("browser");
        String osName = System.getProperty("operatingSystem");
        String mainURL = System.getProperty("mainURL");

        if (platform.equalsIgnoreCase("Desktop")) {
            Log.info("Info : Is Desktop? - Yes");
            Log.info("Info : Browser - " + browser);
            DesiredCapabilities cap = new DesiredCapabilities();
            switch (browser) {
                case "Chrome":
                    try {
                        String chromeDriver = (osName.equalsIgnoreCase("windows") ? "chromedriver.exe" : "chromedriver");
                        System.setProperty("webdriver.chrome.driver", "BrowserDrivers//" + chromeDriver);
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.addArguments("--window-size=1920,1080");
                        chromeOptions.setExperimentalOption("useAutomationExtension", false);

                        Log.info("Info : Chrome options are set:/n" + chromeOptions);

                        driver = new ChromeDriver(chromeOptions);
                        driver.get(mainURL);
                        waitForLoad(driver);
                    } catch (Exception e) {
                        Log.info("Error : Invoke Chrome driver - " + e);
                        throw new Exception("Error : Invoke Chrome driver - " + e);
                    }break;
                case "Safari":
                    try {
                        driver = new SafariDriver();
                        driver.manage().window().maximize();
                        driver.get(mainURL);
                        waitForLoad(driver);
                    } catch (Exception e) {
                        Log.info("Error : Invoke Safari driver - " + e);
                        throw new Exception("Error : Invoke Safari driver - " + e);
                    }break;
                 }
             }
          }

    /**
     * This method creates the remote driver for mobile device based on the
     * input from the config.properties files
     *
     * @throws Exception
     */

    @SuppressWarnings("rawtypes")
    private void createNewMobileInstanceDriver() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("newCommandTimeout", "1200000");
        platformName = System.getProperty("platformName");
        Boolean isRealDevice = System.getProperty("isRealDevice").equalsIgnoreCase("yes") ? true : false;
        Boolean isNative = System.getProperty("isNative").equalsIgnoreCase("yes") ? true : false;
        Boolean isHeadless = System.getProperty("isHeadless").equalsIgnoreCase("yes") ? true : false;
        Boolean isWeb = System.getProperty("isWeb").equalsIgnoreCase("yes") ? true : false;

        Log.info("Info : Is Desktop? - No");
        Log.info("Info : Platform name - " + platformName);

        switch (platformName) {
            case "Android":
                try {
                    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
                    cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
                    cap.setCapability(MobileCapabilityType.NO_RESET, System.getProperty("noResetAppData"));
                    cap.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
                    cap.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, true);
                    cap.setCapability("isHeadless", isHeadless);
                    cap.setCapability(MobileCapabilityType.DEVICE_NAME, System.getProperty("deviceName"));
                    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, System.getProperty("platformVersion"));
                    cap.setCapability("resetKeyboard", true);
                    cap.setCapability("unicodeKeyboard", false);
                    cap.setCapability(AndroidMobileCapabilityType.RECREATE_CHROME_DRIVER_SESSIONS, true);

                    if (!isRealDevice) {
                        cap.setCapability(AndroidMobileCapabilityType.AVD, System.getProperty("avdName"));
                        cap.setCapability(AndroidMobileCapabilityType.AVD_LAUNCH_TIMEOUT, "200000");
                        cap.setCapability(AndroidMobileCapabilityType.AVD_READY_TIMEOUT, "200000");
                        cap.setCapability(MobileCapabilityType.ORIENTATION, System.getProperty("orientation"));
                    } else {
                        cap.setCapability(MobileCapabilityType.UDID, System.getProperty("udid"));
                    }

                    if (isNative) {
                        cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, System.getProperty("appPackage"));
                        cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, System.getProperty("appActivity"));
                        cap.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "com.sph.straitstimes.views.activities.TncActivity");
                        cap.setCapability(AndroidMobileCapabilityType.APP_WAIT_DURATION, 30000);
                        cap.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/Apps/Android/" + System.getProperty("appAndroid"));
                    } else {
                        cap.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME);
                    }

                    Log.info("Info : Android desired capabilities are set:/n" + cap);

                    driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

                    Log.info("Info : Android driver invoked with context: " + ((AppiumDriver) driver).getContext());

                } catch (Exception e) {
                    Log.info("Error : Invoke android driver - " + e);
                    throw new Exception("Error : Invoke android driver - " + e);
                }
                break;
            case "iOS":
                try {
                    cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
                    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
                    cap.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, true);
                    cap.setCapability(MobileCapabilityType.NO_RESET, true);
                    cap.setCapability(MobileCapabilityType.FULL_RESET, false);
                    cap.setCapability(MobileCapabilityType.SUPPORTS_FINDING_BY_CSS, true);
                    cap.setCapability(IOSMobileCapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
                    cap.setCapability(IOSMobileCapabilityType.NATIVE_WEB_TAP, true);
                    cap.setCapability(IOSMobileCapabilityType.AUTO_DISMISS_ALERTS, true);
                    cap.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, true);
                    cap.setCapability(IOSMobileCapabilityType.SIMPLE_ISVISIBLE_CHECK, false);
                    cap.setCapability("isHeadless", isHeadless);
                    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, System.getProperty("platformVersion"));
                    cap.setCapability(MobileCapabilityType.DEVICE_NAME, System.getProperty("deviceName"));

                    if (isRealDevice) {
                        cap.setCapability(MobileCapabilityType.UDID, System.getProperty("udid"));
                        cap.setCapability(IOSMobileCapabilityType.BUNDLE_ID, System.getProperty("bundleId"));
                        cap.setCapability(IOSMobileCapabilityType.START_IWDP, true);
                    }

                    if (isNative) {
                        cap.setCapability(MobileCapabilityType.UDID, System.getProperty("udid"));
                        cap.setCapability(IOSMobileCapabilityType.START_IWDP, true);
                        cap.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/Apps/iOS/" + System.getProperty("appIos"));
                    }

                    if (isWeb) {
                        cap.setCapability(MobileCapabilityType.AUTO_WEBVIEW, true);
                        cap.setCapability(CapabilityType.BROWSER_NAME, MobileBrowserType.SAFARI);
                        cap.setCapability(IOSMobileCapabilityType.SAFARI_ALLOW_POPUPS, true);
                        cap.setCapability(IOSMobileCapabilityType.SAFARI_OPEN_LINKS_IN_BACKGROUND, true);
                        cap.setCapability(IOSMobileCapabilityType.SAFARI_IGNORE_FRAUD_WARNING, true);
                    }

                    Log.info("Info : iOS desired capabilities are set: " + cap);

                    driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

                    Log.info("Info : iOS driver invoked with context: " + ((AppiumDriver) driver).getContext());

                } catch (Exception e) {
                    Log.info("Error : Invoke iOS driver - " + e);
                    throw new Exception("Error : Invoke iOS driver - " + e);
                }
                break;
        }
    }

    /**
     * This method returns the created driver
     */

    public RemoteWebDriver getDriver() {
        return driver;
    }

    /**
     * This method used to close the opened session of the driver
     *
     * @throws InterruptedException
     */

    public void destroyDriver() throws InterruptedException {
        Log.info("Info : Driver quits...");
        // Thread.sleep(10000);
        driver.quit();
        driver = null;

    }

    /**
     * Method to switch current driver context to Native
     *
     * @param remoteWebDriver - Driver instance
     * @return boolean
     */
    public static boolean switchDriverContextToNative(RemoteWebDriver remoteWebDriver) {

        try {
            if (BaseGlue.isContextInWebView==false && ((AppiumDriver) remoteWebDriver).getContext().toUpperCase().contains("NATIVE_APP")){
                Log.info("Info : Current driver context already set to Native_App - " + ((AppiumDriver) remoteWebDriver).getTitle());
                return true;
            }
            Set<String> contextNames = ((AppiumDriver) remoteWebDriver).getContextHandles();
            Log.info("Info : Current driver context name - " + ((AppiumDriver) remoteWebDriver).getContext());
            for (String contextName : contextNames) {
                if (contextName.toUpperCase().contains("NATIVE_APP")) {
                    ((AppiumDriver) remoteWebDriver).context(contextName);
                    Log.info("Info : Driver context set to - " + contextName);
                    return true;
                } else {
                    Log.info("Info : Looping side switchDriverContextToNative - "+contextName);
                }
            }
            Log.info("Fail : To set the context to native");
        } catch (Exception e) {
            Log.error("Error : Failed to set the driver context to native - "+e);
            return false;
        }

        return false;
    }

    /**
     * Method to switch current driver context to Native
     *
     * @param remoteWebDriver - Driver instance
     * @param title - Webview title
     * @return boolean
     */
    public static boolean switchDriverContextToLocatedWebview(RemoteWebDriver remoteWebDriver,String title) {

        try {
            Set<String> contextNames = ((AppiumDriver) remoteWebDriver).getContextHandles();
            Log.info("Info : Total contexts - "+contextNames.size());
            for (String contextName : contextNames) {
                if (contextName.toUpperCase().contains("WEBVIEW")) {
                    ((AppiumDriver) remoteWebDriver).context(contextName);
                    Log.info("Info : Trying with context - " + contextName);
                    Log.info("Info : Title - " + remoteWebDriver.getTitle());
                    if (remoteWebDriver.getTitle().equalsIgnoreCase(title)) {
                        Log.info("Info : Switch Webview to - " + contextName);
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            Log.error("Error : Failed to set the driver context - "+e);
            return false;
        }
        return false;
    }

    public static void waitForLoad(WebDriver driver) {
        int waitFor = 2;
        Log.info("Info : Wait for page to load");
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                String pageLoadStatus = (String) ((JavascriptExecutor) driver).executeScript("return document.readyState");
                Log.info("Info : Page Load Status - " + pageLoadStatus);
                return pageLoadStatus.equals("complete") || pageLoadStatus.equals("interactive");
            }
        };
        Log.info("Info : Maximum wait for - " + waitFor);
        WebDriverWait wait = new WebDriverWait(driver, waitFor);
        wait.until(pageLoadCondition);
    }

    public static String getMobilePlatform(RemoteWebDriver remoteWebDriver) {
        try {
            Log.info("Info : Get mobile platform : " + ((AppiumDriver) driver).getPlatformName());
            return ((AppiumDriver) driver).getPlatformName().toUpperCase();
        } catch (Exception e) {
            Log.info("Fail : Failed to get the mobile platform : " + e);
            return "";
        }
    }

    public static boolean switchToWindowId(RemoteWebDriver driver, String windowId) {
        try {
            if (System.getProperty("platform").equals("Desktop")) {
                driver.switchTo().window(windowId);
                return true;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}