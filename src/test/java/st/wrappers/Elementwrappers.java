package st.wrappers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import st.utility.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;


public class Elementwrappers {

    static int maxElementWait = 20;

    public static boolean enterText(AppiumDriver driver, WebElement ele, String value) {
        boolean isTrue = false;
        try {
            ele.clear();
            ele.sendKeys(value);
            Log.info("Pass : [EW] Enter text - " + ele.toString() + "; Value - " + value);
            isTrue = true;
        } catch (Exception e) {
            Log.info("Error : Enter text - " + e);
        }
        return isTrue;
    }

    public static boolean enterText(AppiumDriver driver, By by, String value) {
        boolean isTrue = false;
        try {
            WebElement ele = driver.findElement(by);
            ele.clear();
            ele.sendKeys(value);
            Log.info("Pass : [EW] Enter text - " + ele.toString() + "; Value - " + value);
            isTrue = true;
        } catch (Exception e) {
            Log.info("Error : Enter text - " + e);
        }
        return isTrue;
    }

    public static boolean doSwipeOnScreen(AppiumDriver driver, String swipeDirection) throws Exception {

        final long startTime = Log.startTime();
        boolean isTrue = false;

        try {

            Dimension size = driver.manage().window().getSize();
            int startY = (int) (size.height * 0.6);
            int endY = (int) (size.height * 0.75);
            int startX = (int) (size.width * 0.75);
            int endX = (int) (size.width * 0.125);

            int duration = 3000;

            switch (swipeDirection.toLowerCase()) {

                case "left": {
                    new TouchAction((AppiumDriver) driver).press(PointOption.point(startX, startY))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration))).moveTo(PointOption.point(endX, endY))
                            .release().perform();
                    Log.info("Pass : [EW] Do swipe on screen towards direction \t:" + swipeDirection);
                    break;
                }
                case "right": {
                    new TouchAction((AppiumDriver) driver).press(PointOption.point(endX, startY))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration))).moveTo(PointOption.point(startX, endY))
                            .release().perform();
                    Log.info("Pass : [EW] Do swipe on screen towards direction \t:" + swipeDirection);
                    break;
                }
                case "up": {
                    new TouchAction((AppiumDriver) driver).press(PointOption.point(startX, endY))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration))).moveTo(PointOption.point(endX, endY/2))
                            .release().perform();
                    Log.info("Pass : [EW] Do swipe on screen towards direction \t:" + swipeDirection);
                    break;
                }
                case "down": {
                    new TouchAction((AppiumDriver) driver).press(PointOption.point(endX, endY/2))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration))).moveTo(PointOption.point(endX, endY))
                            .release().perform();
                    Log.info("Pass : [EW] Do swipe on screen towards direction \t:" + swipeDirection);
                    break;
                }
                default:{
                    new TouchAction((AppiumDriver) driver).press(PointOption.point(startX, startY))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000))).moveTo(PointOption.point(endX, endY))
                            .release().perform();
                    Log.info("Pass : [EW] Do swipe on screen towards direction \t:" + swipeDirection);
                    break;
                }
            }

            isTrue = true;

        }//End try

        catch (Exception e) {
            throw new Exception("Exception at ElementWrapper.doSwipeOnScreen \t: " + e.getMessage(), e);
        }//End catch

        finally	{
            Log.info("ElementWrapper.doSwipeOnScreen\t"+ Log.elapsedTime(startTime));
        }//End finally

        return isTrue;
    }

    public static boolean doSwipeOnScreen(AppiumDriver driver, String swipeDirection, int duration) throws Exception {

        final long startTime = Log.startTime();
        boolean isTrue = false;

        try {

            Dimension size = driver.manage().window().getSize();
            int startY = (int) (size.height * 0.6);
            int endY = (int) (size.height * 0.75);
            int startX = (int) (size.width * 0.75);
            int endX = (int) (size.width * 0.125);

            switch (swipeDirection.toLowerCase()) {

                case "left": {
                    new TouchAction((AppiumDriver) driver).press(PointOption.point(startX, startY))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration))).moveTo(PointOption.point(endX, endY))
                            .release().perform();
                    Log.info("Pass : [EW] Do swipe on screen towards direction \t:" + swipeDirection);
                    break;
                }
                case "right": {
                    new TouchAction((AppiumDriver) driver).press(PointOption.point(endX, startY))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration))).moveTo(PointOption.point(startX, endY))
                            .release().perform();
                    Log.info("Pass : [EW] Do swipe on screen towards direction \t:" + swipeDirection);
                    break;
                }
                case "up": {
                    new TouchAction((AppiumDriver) driver).press(PointOption.point(startX, endY))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration))).moveTo(PointOption.point(endX, endY/2))
                            .release().perform();
                    Log.info("Pass : [EW] Do swipe on screen towards direction \t:" + swipeDirection);
                    break;
                }
                case "down": {
                    new TouchAction((AppiumDriver) driver).press(PointOption.point(endX, endY/2))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration))).moveTo(PointOption.point(endX, endY))
                            .release().perform();
                    Log.info("Pass : [EW] Do swipe on screen towards direction \t:" + swipeDirection);
                    break;
                }
                default:{
                    new TouchAction((AppiumDriver) driver).press(PointOption.point(startX, startY))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000))).moveTo(PointOption.point(endX, endY))
                            .release().perform();
                    Log.info("Pass : [EW] Do swipe on screen towards direction \t:" + swipeDirection);
                    break;
                }
            }

            isTrue = true;

        }//End try

        catch (Exception e) {
            throw new Exception("Exception at ElementWrapper.doSwipeOnScreen \t: " + e.getMessage(), e);
        }//End catch

        finally	{
            Log.info("ElementWrapper.doSwipeOnScreen\t"+ Log.elapsedTime(startTime));
        }//End finally

        return isTrue;
    }

    public static boolean waitForElement(RemoteWebDriver driver, WebElement ele) {
        return waitForElement(driver, ele, maxElementWait);
    }

    public static boolean waitForElement(RemoteWebDriver driver, WebElement ele, int maxElementWait) {
        WebDriverWait wait = new WebDriverWait(driver, maxElementWait);
        boolean elementState = false;
        try {

            // WebDriver Wait
            wait.withTimeout(Duration.ofSeconds(maxElementWait))
                    .pollingEvery(Duration.ofSeconds(1));
            WebElement waitElement = wait.until(ExpectedConditions.elementToBeClickable(ele));
            Log.info("Info : [EW] Waiting for element - " + ele.toString());

            if (waitElement.isDisplayed()) {
                Log.info("Pass : [EW] Element found - " + ele.toString());
                elementState = true;
            } else {
                Log.info("Fail : [EW] Element not found - " + ele.toString());
            }
        } catch (Exception e) {
            Log.info("Error : [EW] - Wait for element - "+e);
            elementState = false;
        }
        return elementState;
    }

    public static boolean clickWithJs(RemoteWebDriver driver, WebElement element, boolean isWaitRequired) {
        boolean isTrue=false;
        String logMsg = " : [EW] Element clicked by JS - "+element.toString();
        try {
            isTrue = isWaitRequired ? Elementwrappers.waitForElement(driver, element) : false;
            // (new WebDriverWait(driver, maxElementWait)).until(ExpectedConditions.elementToBeClickable(element));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            Log.info("Pass"+logMsg);
            isTrue = true;
        } catch (Exception e) {
            Log.info("Error"+logMsg+" - "+e);
        }
        return isTrue;
    }
    public static boolean click(RemoteWebDriver driver, WebElement element) {
        boolean isTrue = false;
        try {
            Elementwrappers.waitForElement(driver, element);
            element.click();
            Log.info("Pass : [EW] Element clicked - "+element.toString());
            isTrue = true;
        } catch (NoSuchElementException e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            Log.info("Pass : [EW] Element clicked by js (NSE) - "+element.toString());
            isTrue = true;
        } catch (StaleElementReferenceException e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            Log.info("Pass : [EW] Element clicked by js (Stale) - "+element.toString());
            isTrue = true;
        } catch (WebDriverException e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            Log.info("Pass : [EW] Element clicked by js (WDE) - "+element.toString());
            isTrue = true;
        } catch (Exception e) {
            Log.error("Error : Click - " + e);
        }
        return isTrue;
    }

    public static boolean sendKeys(RemoteWebDriver driver, By by, String inputData) {
        boolean isTrue = false;
        WebElement ele = null;
        ele = driver.findElement(by);
        try {
            (new WebDriverWait(driver, maxElementWait)).until(ExpectedConditions.elementToBeClickable(by));
            ele = driver.findElement(by);
            if (waitForElement(driver, ele)) {
                ele.clear();
                ele.sendKeys(inputData);
                if (ele.getAttribute("value").equalsIgnoreCase(inputData) || ele.getText().equalsIgnoreCase(inputData))
                    isTrue = true;
                if (!isTrue)
                    Log.info("Info : [EW] Send Keys - Fail");
                else
                    Log.info("Info : [EW] Send Keys - Pass Value is " + inputData);

            }
        } catch (StaleElementReferenceException e) {
            ele.clear();
            ele.sendKeys(inputData);
            if (ele.getAttribute("value").equalsIgnoreCase(inputData) || ele.getText().equalsIgnoreCase(inputData))
                isTrue = true;
            if (!isTrue)
                Log.info("Info : [EW] Send Keys - Fail");
            driver.findElement(by).sendKeys(inputData);
        } catch (Exception e) {
            Log.error("Error - Send Keys : " + e);
        }
        return isTrue;
    }

    public static boolean sendKeys(RemoteWebDriver driver, WebElement ele, String inputData) {
        boolean isTrue = false;
        try {
            (new WebDriverWait(driver, maxElementWait)).until(ExpectedConditions.elementToBeClickable(ele));
            if (waitForElement(driver, ele)) {
                ele.clear();
                ele.sendKeys(inputData);
                isTrue = true;
                if (!isTrue)
                    Log.info("Info : [EW] Send Keys - Fail : " + inputData);
            }
        } catch (StaleElementReferenceException e) {
            ele.sendKeys(inputData);
            isTrue = true;
            if (!isTrue)
                Log.info("Info : [EW] Send Keys - Fail : " + inputData);
        } catch (Exception e) {
            Log.error("Error - Send Keys with value : " + inputData + " - " + e);
        }
        return isTrue;
    }

    public static boolean isDisplayed(RemoteWebDriver driver, WebElement ele) {
        boolean isDisplayed = false;
        try {
            Elementwrappers.waitForElement(driver, ele);
            isDisplayed = ele.isDisplayed();
            String output = isDisplayed ? "Pass" : "Fail";
        } catch (StaleElementReferenceException e) {
            // retry the find element
            isDisplayed = ele.isDisplayed();
        } catch (Exception e) {
            Log.info("Error : Element identified is not clickable: " + e);
        }
        return isDisplayed;
    }

    public static boolean isDisplayed(RemoteWebDriver driver, By by) {
        boolean isDisplayed = false;
        try {
            Elementwrappers.waitForElement(driver, driver.findElement(by));
            isDisplayed = driver.findElement(by).isDisplayed();
        } catch (StaleElementReferenceException e) {
            // retry the find element
            isDisplayed = driver.findElement(by).isDisplayed();
        } catch (Exception e) {
            // log("Element identified is not visible");
            Log.info("Error : Element identified is not visible: " + e);
        }
        return isDisplayed;
    }

    public static void pressEnter(AndroidDriver driver) {
        try {
            driver.pressKey(new KeyEvent(AndroidKey.ENTER));
                Thread.sleep(5000);

        } catch (Exception e) {

        }
    }

    public static String screenshot(RemoteWebDriver driver, String scenarioName, String tcStatus) throws IOException {
        Date date1 = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String folder = dateFormat.format(date1);

        File theDir = new File("./screenshots/" + folder);
        if (!theDir.exists()) {
            theDir.mkdir();
            Log.info("Info : [EW] Screenshots - Folder created");
        }
        dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String fileName = (dateFormat.format(date1) + "_" + tcStatus + "_" + scenarioName).replaceAll(" ","").replaceAll("\"","");
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String dest = "screenshots/" + folder + "/" + fileName + ".png";
        FileUtils.copyFile(srcFile, new File(dest));

        return dest;
    }

}

