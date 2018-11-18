package st.pages;

import st.glue.BaseGlue;
import st.utility.Log;
import st.wrappers.Elementwrappers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;

import st.wrappers.DriverFactory;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

public class BasePage extends LoadableComponent<BasePage> {

    @AndroidFindBy(id = "btn_tnc_ok")
    WebElement btnAgree;

    @AndroidFindBy(id = "btn_tnc_cancel")
    WebElement btnCloseApp;

    @AndroidFindBy(id = "welcome")
    WebElement lblWelcome;

    @AndroidFindBy(id = "skip")
    WebElement txtSkip;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Interstitial close button']")
    WebElement btnAdClose;

    private RemoteWebDriver driver;

    public BasePage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(DriverFactory.maxElementWait)), this);
        isLoaded();
    }

    public boolean clickAgree() {
        boolean isTrue = false;
        try {
            isTrue = (Elementwrappers.click(driver, btnAgree));
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Click on Agree button");
        } catch (Exception e) {
            Log.info("Error" + "Click on Agree button" + e);
        }
        return isTrue;
    }

    public boolean clickCloseApp() {
        boolean isTrue = false;
        try {
            isTrue = (Elementwrappers.click(driver, btnCloseApp));
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Click on close app button");
        } catch (Exception e) {
            Log.info("Error" + "Click on close app button" + e);
        }
        return isTrue;
    }

    public boolean clickSkip() {
        boolean isTrue = false;
        try {
            isTrue = (Elementwrappers.click(driver, txtSkip));
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Click on skip text icon");
        } catch (Exception e) {
            Log.info("Error" + "Click on skip text icon" + e);
        }
        return isTrue;
    }

    public boolean handleAdScreen() {
        boolean isTrue = false;
        try {
            if(Elementwrappers.waitForElement(driver,btnAdClose)) {
                isTrue = (Elementwrappers.click(driver, btnAdClose));
                String output = isTrue ? "Pass" : "Fail";
                Log.info(output + "Click on close icon in Ad screen");
            }
        } catch (Exception e) {
            Log.info("Error" + "Click on close icon in Ad screen" + e);
        }
        return isTrue;
    }

    public boolean initScreenHandler(RemoteWebDriver driver) throws Exception {
        final long startTime = Log.startTime();
        boolean isTrue = false;
        Log.info("Info : In initScreenHandler");
        try {

            waitForLandingScreen(driver);

            if(clickAgree()){
                Log.info("Info: Click on Skip button");
            }

            if (BaseGlue.mobilePlatform.equalsIgnoreCase("IOS")) {
                /*-- To do --*/
            } else if (BaseGlue.mobilePlatform.equalsIgnoreCase("ANDROID")) {

                while (Elementwrappers.isDisplayed(driver, lblWelcome)) {
                        if (Elementwrappers.doSwipeOnScreen((AppiumDriver) driver, "left", 200))
                            Log.info("Info: Swipe the screen - Left");
                        if(Elementwrappers.isDisplayed(driver, txtSkip))
                            isTrue = clickSkip();
                            Log.info("Info: Click on Skip button");
                            break;
                    }
                }
        }//End try
        catch (Exception e) {
            if (e.toString().contains("NoSuchElementException")) {
                Log.info("Exception :  NoSuchElementException found");
                return false;
            } else
                throw new Exception("Exception at BasePage.initScreenHandler : ", e);
        }//End catch
        finally {
            Log.info("BasePage.initScreenHandler :\t" + Log.elapsedTime(startTime));
        }//End finally

        return isTrue;
    }

    public static void waitForPageLoad(final RemoteWebDriver driver) {
        waitForPageLoad(driver, DriverFactory.maxElementWait);
    }

    public static boolean waitForPageLoad(final RemoteWebDriver driver, int maxWait) {
        Log.info("Info : Wait for page to load...");
        final JavascriptExecutor js = (JavascriptExecutor) driver;
        FluentWait<WebDriver> wait = new WebDriverWait(driver, 0).withTimeout(Duration.ofSeconds(maxWait)).pollingEvery(Duration.ofSeconds(1))
                .ignoring(StaleElementReferenceException.class).withMessage("Page Load Time Out");
        boolean docReadyState = false;
        try {
            docReadyState = (Boolean) js
                    .executeScript("return (function() {if(document.readyState!='complete'){return false;}();");
        } catch (Exception e) {

        }
        return docReadyState;
    }

    public void waitForLandingScreen(RemoteWebDriver driver) throws Exception {

        final long startTime = Log.startTime();

        try {

            WebDriverWait wait = new WebDriverWait(driver, 3);
            Log.info("Info :  Waiting for element in intro screen");
            wait.pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class).ignoring(TimeoutException.class).until(ExpectedConditions.visibilityOf(btnAgree));

        } // End try
        catch (Exception e) {
            if (e.getClass().toString().contains("NoSuchElementException")) {
                Log.info("Exception :  NoSuchElementException found");
                return;
            } else if (e.getClass().toString().contains("TimeoutException")) {
                Log.info("Exception :  TimeoutException");
                return;
            } else
                throw new Exception("Exception at BasePage.fluentWait : ", e);
        } // End catch

        finally {
            Log.info("BasePage.fluentWait :" + Log.elapsedTime(startTime));
        }// End final

    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {

    }
}