package st.wrappers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import st.utility.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.List;
import java.util.NoSuchElementException;


public class Elementwrappers {

    static int maxElementWait = 20;

    public static boolean tapByElement(AppiumDriver driver, By by, int x, int y) {
        boolean isTrue = false;
        try {
            WebElement ele = driver.findElement(by);
            Point location = ele.getLocation();
            new TouchAction(driver).tap(PointOption.point(location.getX() + x, location.getY() + y));
            Log.info("Pass : [EW] Tap on element - " + ele.toString());
            isTrue = true;
        } catch (Exception e) {
            Log.info("Error : Tap on element - " + e);
        }
        return isTrue;
    }

    public static boolean tapByPosition(AppiumDriver driver, int x, int y) {
        boolean isTrue = false;
        try {
            new TouchAction(driver).tap(PointOption.point(x, y));
            Log.info("Pass : [EW] Tap by position - (" + x + "," + y + ")");
            isTrue = true;
        } catch (Exception e) {
            Log.info("Error : Tap by position - " + e);
        }
        return isTrue;
    }

    public static boolean getContext(AppiumDriver driver) {
        boolean isTrue = false;
        try {
            String context = driver.getContext();
            Log.info("Pass : [EW] Get context - " + context);
            isTrue = true;
        } catch (Exception e) {
            Log.info("Error : Get context - " + e);
        }
        return isTrue;
    }


    public static boolean isAppInstalled(AppiumDriver driver, String bundleId) {
        boolean isTrue = false;
        try {
            if (driver.isAppInstalled(bundleId)) {
                Log.info("Pass : [EW] Is app installed - " + bundleId);
                isTrue = true;
            } else {
                Log.info("Fail : [EW] Is app installed - " + bundleId);
            }
        } catch (Exception e) {
            Log.info("Error : Is app installed - " + e);
        }
        return isTrue;
    }

    public static boolean getContextHandles(AppiumDriver driver) {
        boolean isTrue = false;
        try {
            Set<String> contexts = driver.getContextHandles();
            if (contexts.size() > 0) {
                Log.info("Pass : [EW] Get context handles");
                int count = 0;
                for (String context : contexts) {
                    count++;
                    Log.info("Info : [EW] Context handle - " + count + " : " + context);
                }
                isTrue = true;
            } else {
                Log.info("Fail : [EW] No context handles");
            }
        } catch (Exception e) {
            Log.info("Error : Get context handles");
        }
        return isTrue;
    }

    public static boolean hideKeyboard(AppiumDriver driver) {
        boolean isTrue = false;
        try {
            driver.hideKeyboard();
            Log.info("Pass : [EW] Hide keyboard");
            isTrue = true;
        } catch (Exception e) {
            Log.info("Error : Hide keyboard - " + e);
        }
        return isTrue;
    }

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

    public static boolean doSwipeOnElement(AppiumDriver driver, String swipeDirection, WebElement ele) throws Exception {

        final long startTime = Log.startTime();
        boolean isTrue = false;

        try {

            JavascriptExecutor js = (JavascriptExecutor) driver;
            HashMap<String, String> swipeObject = new HashMap<String, String>();
            swipeObject.put("direction", swipeDirection);
            swipeObject.put("element", ((MobileElement)ele).getId());
            js.executeScript("mobile: swipe", swipeObject);

            Log.info("Pass : [EW] Do swipe on element towards direction \t:"+ swipeDirection);

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

    public static boolean click(RemoteWebDriver driver, By by) {
        boolean isTrue = false;
        try {
            Elementwrappers.waitForElement(driver, driver.findElement(by));
            driver.findElement(by).click();
            isTrue = true;
        } catch (StaleElementReferenceException e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(by));
            isTrue = true;
        } catch (WebDriverException e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(by));
            isTrue = true;
        } catch (Exception e) {
            Log.error("Error - Click : " + e);
        }
        return isTrue;
    }

    public static boolean reloadPage(RemoteWebDriver driver) {
        boolean isTrue = false;
        try {
            driver.get(driver.getCurrentUrl());
            isTrue = true;
        } catch (Exception e) {
            Log.info("Error : Not able to reload the page - " + e);
        }
        return isTrue;
    }

    public static boolean clickWithJs(RemoteWebDriver driver, WebElement element) {
        return clickWithJs(driver, element, true);
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

    public static boolean doubleClick(RemoteWebDriver driver, WebElement element) {
        boolean isTrue=false;
        String logMsg = " : [EW] Element clicked by Action - "+element.toString();
        try {
            Elementwrappers.waitForElement(driver, element);
            Actions ac = new Actions(driver);
            ac.moveToElement(element).doubleClick().build().perform();
            Log.info("Pass"+logMsg);
            isTrue = true;
        } catch (WebDriverException e1) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            Log.info("Info : [EW] Try click with JS");
        }catch (Exception e) {
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

    public static boolean submit(RemoteWebDriver driver, WebElement element) {
        boolean isTrue = false;
        try {
            Elementwrappers.waitForElement(driver, element);
            (new WebDriverWait(driver, maxElementWait)).until(ExpectedConditions.elementToBeClickable(element));
            element.sendKeys(Keys.ENTER);
            Log.info("Pass : [EW] Element submitted - "+element.toString());
            isTrue = true;
        } catch (NoSuchElementException e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            Log.info("Pass : [EW] Element clicked by js (Stale) - "+element.toString());
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

    public static boolean sendKeysWithoutClear(RemoteWebDriver driver, WebElement ele, String inputData) {
        boolean isTrue = false;
        try {
            (new WebDriverWait(driver, maxElementWait)).until(ExpectedConditions.elementToBeClickable(ele));
            if (waitForElement(driver, ele)) {
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


    public static void clear(WebElement ele) {
        ele.clear();
    }

    public static boolean isDisplayed(WebElement ele) {
        return ele.isDisplayed();
    }

    public static boolean isEnabled(RemoteWebDriver driver, WebElement ele) {
        Elementwrappers.waitForElement(driver, ele);
        boolean isEnabled = false;
        try {
            Thread.sleep(1000);
            isEnabled = ele.isEnabled();
        } catch (StaleElementReferenceException e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            isEnabled = (boolean) executor.executeScript("return arguments[0].disabled;", ele);
        } catch (Exception e) {
            Log.info("Info : [EW] isEnabled : " + isEnabled + "   error : " + e.getMessage());
        }

        return isEnabled;
    }

    public static boolean isEnabled(RemoteWebDriver driver, By by) {
        boolean isEnabled = false;
        try {
            (new WebDriverWait(driver, maxElementWait)).until(ExpectedConditions.elementToBeClickable(by));
            isEnabled = driver.findElement(by).isEnabled();
        } catch (StaleElementReferenceException e) {
            // retry the find element
            isEnabled = driver.findElement(by).isEnabled();
        } catch (Exception e) {
            // log("Element identified is not enabled");
            Log.info("Error : Element identified is not enabled: " + e);
        }
        return isEnabled;
    }

    public static String getText(RemoteWebDriver driver, WebElement element) throws Exception {
        try {
            Elementwrappers.waitForElement(driver, element);
            return element.getText();
        } catch (StaleElementReferenceException e) {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            String Text = (String) jse.executeScript("return arguments[0].value;", element);
            return Text;
        } catch (Exception e) {
            Log.info("Error : Unable to get text - " + e);
            throw new Exception("Not able to get the text");
        }
    }

    public static String getTextWithJs(RemoteWebDriver driver, WebElement element) throws Exception {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            String Text = (String) jse.executeScript("return arguments[0].value;", element);
            return Text;
        } catch (Exception e) {
            Log.info("Error : Unable to get text - " + e);
            throw new Exception("Not able to get the text");
        }
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

    /**
     * To select/check the give element
     *
     * @param driver
     * @param element
     * @return
     */
    public static boolean selectOption(RemoteWebDriver driver, WebElement element) {
        boolean isSelected = false;
        try {
            (new WebDriverWait(driver, maxElementWait)).until(ExpectedConditions.elementToBeClickable(element));
            if (!element.isSelected()) {
                if (Elementwrappers.clickWithJs(driver, element)) {
                    isSelected = true;
                    Log.info("Info : [EW] Checkbox/radio button selected");
                } else {
                    Log.info("Fail : [EW] Fail to select checkbox/radio button");
                }
            } else {
                Elementwrappers.clickWithJs(driver, element);
                Log.info("Info : [EW] Checkbox/radio button already selected");
                isSelected = true;
            }
        } catch (Exception e) {
            Log.info("Error : Not able to select Checkbox/radio button - "+e);
        }
        return isSelected;
    }

    /**
     * To select/check the give locator
     *
     * @param driver
     * @param byElement
     * @return
     */
    public static boolean selectOption(RemoteWebDriver driver, By byElement) {
        boolean isSelected = false;
        try {
            WebElement element = driver.findElement(byElement);
            if (!element.isSelected()) {
                if (Elementwrappers.click(driver, element)) {
                    isSelected = true;
                    Log.info("Info : [EW] Checkbox/radio button selected");
                } else {
                    Log.info("Fail : [EW] Fail to select checkbox/radio button");
                }
            } else {
                Log.info("Info : [EW] Checkbox/radio button already selected");
                isSelected = true;
            }
        } catch (Exception e) {
            Log.info("Error : Not able to select Checkbox/radio button - " + e);
        }
        return isSelected;
    }

    /**
     * To de- select if the element is already selected/checked
     *
     * @param driver
     * @param element
     * @return
     */
    public static boolean deselectOption(RemoteWebDriver driver, WebElement element) {
        boolean deSelected = false;
        try {
            (new WebDriverWait(driver, maxElementWait)).until(ExpectedConditions.elementToBeClickable(element));
            if (element.isSelected()) {
                if (Elementwrappers.click(driver, element)) {
                    deSelected = true;
                    Log.info("Info : [EW] Checkbox/radio button de-selected");
                } else {
                    Log.info("Fail : [EW] Fail to de-select checkbox/radio button");
                }
            } else {
                Log.info("Info : [EW] Checkbox/radio button already de-selected");
                deSelected = true;
            }
        } catch (Exception e) {
            Log.info("Error : Not able to de-select Checkbox/radio button");
        }
        return deSelected;
    }

    /**
     * To check if the element is selected
     *
     * @param driver
     * @param element
     * @return
     */
    public static boolean isSelected(RemoteWebDriver driver, WebElement element) {
        boolean isSelected = false;
        try {
            (new WebDriverWait(driver, maxElementWait)).until(ExpectedConditions.elementToBeClickable(element));
            String elementStr = element.toString().replaceAll("Located by By.cssSelector: ", "");
            WebElement rdoElement = driver.findElement(By.cssSelector(elementStr+":checked"));
            isSelected = rdoElement.isSelected();
            Log.info("Info : [EW] Is Selected - "+isSelected);
        } catch (StaleElementReferenceException e) {
            Log.info("Error : Is element selected - " + e);
        } catch (Exception e) {
            Log.info("Error : Is element selected - " + e);
        }
        return isSelected;
    }

    /**
     * To check if the element is checked
     *
     * @param driver
     * @param element
     * @return
     */
    public static boolean isChecked(RemoteWebDriver driver, WebElement element) {
        boolean isSelected = false;
        try {
            (new WebDriverWait(driver, maxElementWait)).until(ExpectedConditions.elementToBeClickable(element));
            String elementStr = element.toString().replaceAll("Located by By.cssSelector: ", "");
            elementStr = elementStr.replaceAll(" .checbox_wrapper", "");
            WebElement rdoElement = driver.findElement(By.cssSelector(elementStr));
            Log.info("Info : Is Checked; Element - "+elementStr + "; Class attribute content - "+rdoElement.getAttribute("class"));
            isSelected = rdoElement.getAttribute("class").contains("checked");
            Log.info("Info : [EW] Is Checked - "+isSelected);
        } catch (StaleElementReferenceException e) {
            Log.info("Error : Is element checked - " + e);
        } catch (Exception e) {
            Log.info("Error : Is element checked - " + e);
        }
        return isSelected;
    }

    /**
     * Getting the attribute by passing the By
     *
     * @param driver
     * @param by
     * @param eleProp
     * @return
     */

    public static String getAttribute(RemoteWebDriver driver, By by, String eleProp) {
        String eleAttribute = null;
        try {
            (new WebDriverWait(driver, maxElementWait)).until(ExpectedConditions.elementToBeClickable(by));
            eleAttribute = driver.findElement(by).getAttribute(eleProp);
        } catch (StaleElementReferenceException e) {
            // retry the find element
            eleAttribute = driver.findElement(by).getAttribute(eleProp);
        } catch (Exception e) {
            Log.info("Error : Unable to get attribute: " + e);
        }
        return eleAttribute;
    }

    /**
     * Getting the attribute by passing the By
     *
     * @param driver
     * @param element
     * @param eleProp
     * @return
     */

    public static String getAttribute(RemoteWebDriver driver, WebElement element, String eleProp) {
        String eleValue = null;
        try {
            Elementwrappers.waitForElement(driver, element);
            eleValue = element.getAttribute(eleProp);
            Log.info("Info : [EW] Get value - " + eleValue);
        } catch (StaleElementReferenceException e) {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            String Text = (String) jse.executeScript("return arguments[0].value;", element);
            Log.info("Info : [EW] Get value - " + Text);
            return Text;
        } catch (Exception e) {
            Log.info("Error : Get value - " + e);
        }
        return eleValue;
    }

    /**
     * Getting the child count
     *
     * @param driver
     * @param by
     * @return
     */

    public static int getChildCount(RemoteWebDriver driver, By by) {
        int count = 0;
        try {
            Elementwrappers.waitForElement(driver, driver.findElement(by));
            (new WebDriverWait(driver, maxElementWait)).until(ExpectedConditions.elementToBeClickable(by));
            count = driver.findElements(by).size();
        } catch (StaleElementReferenceException e) {
            // retry the find element
            count = driver.findElements(by).size();
        } catch (Exception e) {
            Log.info("Error : Unable to get child element count: " + e);
        }
        return count;
    }

    /**
     * Getting the WebElements as list at run time
     *
     * @param driver
     * @param by
     * @return
     */
    public static List<WebElement> getElements(RemoteWebDriver driver, By by) {
        List<WebElement> ele = new ArrayList<>();
        ele = driver.findElements(by);
        return ele;
    }

    /**
     * Getting the WebElement at run time
     *
     * @param driver
     * @param by
     * @return
     */
    public static WebElement getElement(RemoteWebDriver driver, By by) {
        WebElement ele;
        ele = driver.findElement(by);
        return ele;
    }

    /**
     * Getting the css attribute of the element by passing the By
     *
     * @param driver
     * @param by
     * @return
     */

    public String getCSSValue(RemoteWebDriver driver, By by, String eleProp) {
        String eleAttribute = null;
        try {
            (new WebDriverWait(driver, maxElementWait)).until(ExpectedConditions.elementToBeClickable(by));
            eleAttribute = driver.findElement(by).getCssValue(eleProp);
        } catch (StaleElementReferenceException e) {
            // retry the find element
            eleAttribute = driver.findElement(by).getCssValue(eleProp);
        } catch (Exception e) {
            Log.info("Error : Unable to get element css value: " + e);
        }
        return eleAttribute;
    }

    /**
     * Getting the Tag attribute by passing the By
     *
     * @param driver
     * @param by
     * @return
     */

    public String getTagName(RemoteWebDriver driver, By by) {
        String eleAttribute = null;
        try {
            (new WebDriverWait(driver, maxElementWait)).until(ExpectedConditions.elementToBeClickable(by));
            eleAttribute = driver.findElement(by).getTagName();
        } catch (StaleElementReferenceException e) {
            // retry the find element
            eleAttribute = driver.findElement(by).getTagName();
        } catch (Exception e) {
            Log.info("Error : Unable to get element tag name: " + e);
        }
        return eleAttribute;
    }

    /**
     * To scroll to element
     *
     * @param driver
     * @param ele
     * @return
     */

    public static void scrollToElement(RemoteWebDriver driver, WebElement ele) {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeAsyncScript("argument[0].scrollintoView(true)", ele);

    }

    public static void scrollDown(RemoteWebDriver driver) {
        try {
            JavascriptExecutor je = (JavascriptExecutor) driver;
            je.executeAsyncScript("window.scrollBy(0, 100)");
            Log.info("Info : [EW] Scrolled down");
        } catch (Exception e) {

        }
    }

    public static boolean scrollDown(RemoteWebDriver driver, int goDownBy) {
        boolean isTrue=false;
        try {
            JavascriptExecutor je = (JavascriptExecutor) driver;
            je.executeAsyncScript("window.scrollBy(0, " + goDownBy + ")");
            Log.info("Info : [EW] Scrolled down");
            isTrue=true;
        } catch (Exception e) {
            Log.info("Info : [EW] Scrolled down [E]");
        }
        return isTrue;
    }

    public static void scrollDown(RemoteWebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
        Log.info("Info : Moved to element by action - "+element.toString());
    }

    public static void scrollUpJs(RemoteWebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap scrollObject = new HashMap(); scrollObject.put("direction", "up");
        js.executeScript("mobile: scroll", scrollObject);
        Log.info("Info : Scroll Up JS");
    }

    public static void scrollDownJs(RemoteWebDriver driver) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap scrollObject = new HashMap(); scrollObject.put("direction", "down");
        js.executeScript("mobile: scroll", scrollObject);
        Thread.sleep(1000);
        Log.info("Info : Scroll Down JS");
    }

    public static void scrollDown(RemoteWebDriver driver, By byelement) {
        Actions actions = new Actions(driver);
        WebElement element = Elementwrappers.getElement(driver, byelement);
        actions.moveToElement(element);
        actions.perform();
    }

    public static void pressEnter(RemoteWebDriver driver, WebElement element) {
        try {
            Robot robot = new Robot();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {

            }
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {

        }
    }

    public static void scrollUp(RemoteWebDriver driver) {
        try {
            JavascriptExecutor je = (JavascriptExecutor) driver;
            je.executeAsyncScript("window.scrollBy(0, 0)");
            Log.info("Info : [EW] Scrolled up");
        } catch (Exception e) {

        }
    }

    /**
     * Performing mouse over on the web element
     *
     * @param driver
     * @param by
     * @param ele
     * @return
     */
    public void mousemove(RemoteWebDriver driver, By by, WebElement ele) {
        try {
            (new WebDriverWait(driver, maxElementWait)).until(ExpectedConditions.elementToBeClickable(by));
            Locatable hoverItem = (Locatable) driver.findElement(by);
            Mouse mouse = ((HasInputDevices) driver).getMouse();
            Coordinates c = hoverItem.getCoordinates();
            mouse.mouseMove(c);
        } catch (StaleElementReferenceException e) {

        } catch (Exception e) {
            Log.info("Error : Unable to perform mouse hover on given element: " + e);
        }
    }


    public static String getCurrentWindowId(RemoteWebDriver driver) {
        try {
            return driver.getWindowHandle();
        } catch (Exception e) {
            return "Error";
        }
    }

    public static boolean checkWebElementsAvailable(RemoteWebDriver driver, List<WebElement> elements) {
        try {
            int count = 0;
            for (WebElement element : elements) {
                if (Elementwrappers.isDisplayed(driver, element)) {
                    Log.info("Element " + element + " is available");
                    count++;
                } else {
                    Log.info("Element " + element + " is not available");
                }
            }
            if (count == elements.size()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean checkWebElementsWithTextAvailable(RemoteWebDriver driver, List<WebElement> elements,
                                                            String specificString) {
        try {
            int count = 0;
            for (WebElement element : elements) {
                if (Elementwrappers.isDisplayed(driver, element)) {
                    if (element.getText().contains(specificString)) {
                        Log.info("Element " + element + " is available");
                        count++;
                    } else {
                        Log.info("Element " + element + " with " + specificString + " is not available");
                    }
                } else {
                    Log.info("Element " + element + " is not available");
                }
            }
            if (count == elements.size()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }


    public static boolean openUrl(RemoteWebDriver driver, String url) {
        boolean isTrue = false;
        try {
            driver.get(url);
            Log.info("Info : [EW] Open URL - " + url);
            isTrue = true;
        } catch (Exception e) {
            Log.info("Error : Open URL - " + e);
        }
        return isTrue;
    }

    public boolean isSwitchSelected(RemoteWebDriver driver, WebElement element) {
        boolean isTrue=false;
        try {
            isTrue = (element.getAttribute("class").contains("checked"));
            String output = isTrue?"Pass":"Fail";
            Log.info(output+" : Switch selected - "+isTrue);
        } catch (Exception e) {
            Log.info("Error : Is Switch selected - "+e);
        }
        return isTrue;
    }

    public static ArrayList<String> getDropdownValues(RemoteWebDriver driver, By byElement) throws Exception {
        ArrayList<String> valueList;
        try {
            List<WebElement> ddList = driver.findElements(byElement);
            valueList = new ArrayList<String>();

            for (WebElement ddValue : ddList) {
                if (ddValue.getText() != null && !ddValue.getText().equals(" ") && !ddValue.getText().equals("")) {
                    valueList.add(ddValue.getText());
                    Log.info("Info : [EW] DD Element - " + ddValue.getText());
                }
            }
        } catch (Exception e) {
            throw new Exception("Unable to get the dropdown vlaues : " + e);
        }
        return valueList;
    }

    public static void responseHeader(RemoteWebDriver driver) {
        try {
            Object response = ((JavascriptExecutor) driver)
                    .executeAsyncScript("var callback = arguments[arguments.length - 1];"
                            + "var xhr = new XMLHttpRequest();" + "xhr.open('GET', '/resource/data.json', true);"
                            + "xhr.onreadystatechange = function() {" + "  if (xhr.readyState == 4) {"
                            + "    callback(xhr.getResponseHeader());" + "  }" + "};" + "xhr.send();");
            JsonObject json = (JsonObject) new JsonParser().parse((String) response);
            Log.info("Info : [EW] JSON Output - " + json.getAsString());
        } catch (JsonSyntaxException e) {

        } catch (Exception e) {

        }
    }

    public static String getScreenshot(RemoteWebDriver driver) throws IOException {
        Date date1 = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String fileName = dateFormat.format(date1);
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String dest = "screenshots/" + fileName + ".png";
        FileUtils.copyFile(srcFile, new File(dest));

        return dest;
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
        String fileName = (dateFormat.format(date1) + "_" + tcStatus + "_" + scenarioName).replaceAll(" ","");
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String dest = System.getProperty("user.dir") + File.separator + "screenshots/" + folder + "/" + fileName + ".png";
        FileUtils.copyFile(srcFile, new File(dest));

        return dest;
    }

    /**
     * Returns the current title of Page
     */
    public static String getTitle(RemoteWebDriver driver) {
        return driver.getTitle();
    }

    public static String getPageContent(RemoteWebDriver driver) {
        String output = "";
        try {
            output = driver.getPageSource();
            Log.info("Info : [EW] Get Page Context - " + output);
        } catch (Exception e) {
            Log.info("Error : Get Page Context - " + e);
        }
        return output;
    }

    public static boolean verifyPageHasThisContent(RemoteWebDriver driver, String data) {
        boolean isTrue = false;
        try {
            String output = driver.getPageSource();
            String log = output.contains(data) ? "Pass" : "Fail";
            Log.info(log + " : Verify Page Context - " + output + "; Expected data - " + data);
        } catch (Exception e) {
            Log.info("Error : Verify Page Context - " + e);
        }
        return isTrue;
    }

    public static boolean verifyText(RemoteWebDriver driver, WebElement element, String expectedText) throws Exception {
        boolean isTrue=false;
        String actualText ="";
        try {
            Elementwrappers.waitForElement(driver, element);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            actualText = (String) jse.executeScript("return arguments[0].value;", element);

            if (actualText.equalsIgnoreCase(expectedText)) {
                Log.info("Pass : [EW] Verify text; Expected - "+expectedText+"; Actual - " +actualText);
                isTrue=true;
            } else {
                Log.info("Fail : [EW] Verify text; Expected - "+expectedText+"; Actual - " +actualText);
            }
        } catch (StaleElementReferenceException e) {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            actualText = (String) jse.executeScript("return arguments[0].value;", element);
            if (actualText.equals(expectedText)) {
                Log.info("Pass : [EW] Verify text; Expected - "+expectedText+"; Actual - " +actualText);
                isTrue=true;
            } else {
                Log.info("Fail : [EW] Actual text at Verify text - "+actualText);
            }
        } catch (Exception e) {
            Log.info("Error : Unable to get text - " + e);
            throw new Exception("Not able to get the text");
        }
        return isTrue;
    }

    public static boolean verifyTextContains(RemoteWebDriver driver, WebElement element, String expectedText) throws Exception {
        boolean isTrue=false;
        try {
            Elementwrappers.waitForElement(driver, element);
            String actualText = element.getText();
            if (actualText.contains(expectedText)) {
                Log.info("Pass : [EW] Actual text at Verify text contains - "+actualText);
                isTrue=true;
            } else {
                Log.info("Fail : [EW] Actual text at Verify text contains - "+actualText);
            }
        } catch (StaleElementReferenceException e) {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            String actualText = (String) jse.executeScript("return arguments[0].value;", element);
            if (actualText.contains(expectedText)) {
                Log.info("Pass : [EW] Actual text at Verify text contains - "+actualText);
                isTrue=true;
            } else {
                Log.info("Fail : [EW] Actual text at Verify text contains - "+actualText);
            }
        } catch (Exception e) {
            Log.info("Error : Unable to get text contains - " + e);
            throw new Exception("Not able to get the text contains");
        }
        return isTrue;
    }

    public static boolean verifyTheFieldLabelContains(RemoteWebDriver driver, WebElement element, String value) throws Exception {
        boolean isTrue=false;
        try {
            isTrue = (isDisplayed(driver, element)) &&  (element.getText().contains(value));
            String output = isTrue ? "Pass" : "False";
            Log.info(output+" : Verify the field with label contains - "+value);
        } catch (Exception e) {
            Log.info("Error : Verify the field with label contains - "+e);
            throw new Exception("Error : Verify the field with label contains - "+e);
        }
        return isTrue;
    }

    public static boolean verifyTheFieldLabelShowsError(RemoteWebDriver driver, WebElement element, String value) throws Exception {
        boolean isTrue=false;
        try {
            isTrue = (isDisplayed(driver, element)) &&  (element.getText().equalsIgnoreCase(value) && element.getCssValue("color").equalsIgnoreCase("#d0021b"));
            String output = isTrue ? "Pass" : "False";
            Log.info(output+" : Verify the field with label; Expected - "+value+"; Actual - "+element.getText());
        } catch (Exception e) {
            Log.info("Error : Verify the field with label - "+e);
            throw new Exception("Error : Verify the field with label - "+e);
        }
        return isTrue;
    }

    public static boolean verifyTheFieldLabel(RemoteWebDriver driver, WebElement element, String value) throws Exception {
        boolean isTrue=false;
        try {
            isTrue = (isDisplayed(driver, element)) &&  (element.getText().equalsIgnoreCase(value));
            String output = isTrue ? "Pass" : "False";
            Log.info(output+" : Verify the field with label; Expected - "+value+"; Actual - "+element.getText());
        } catch (Exception e) {
            Log.info("Error : Verify the field with label - "+e);
            throw new Exception("Error : Verify the field with label - "+e);
        }
        return isTrue;
    }

    public static boolean verifyAllTheFieldsAreAvailable(RemoteWebDriver driver, HashMap<WebElement, String> elements, String pageName) throws Exception {
        boolean isTrue=false;
        try {
            int count=0;
            Iterator iter = elements.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<WebElement, String> mEntry = (Map.Entry) iter.next();
                if (isDisplayed((AppiumDriver) driver, mEntry.getKey())) {
                    Log.info("Pass : [EW] Element available - "+mEntry.getValue());
                    String lblText = mEntry.getValue().replaceAll("Label - ", "");
                    if (mEntry.getValue().contains("Label")) {
                        if (mEntry.getKey().getText().contains(lblText)) {
                            Log.info("Pass : [EW] Label matched - "+lblText+ "; Actual - "+mEntry.getKey().getText());
                            count++;
                        } else {
                            Log.info("Fail : [EW] Label matched - "+lblText+ "; Actual - "+mEntry.getKey().getText());
                        }
                    } else {
                        count++;
                    }
                } else {
                    Log.info("Fail : [EW] Element available - "+mEntry.getValue());
                }
            }
            String result = (elements.size()==count)?"Pass":"Fail";
            isTrue = elements.size()==count?true:false;
            Log.info(result + " : All elements available in page - "+pageName);
        } catch (Exception e) {
            Log.info("Error : Verify all fields availalbe - "+e);
            throw new Exception("Error : Verify all fields available - "+e);
        }
        return isTrue;
    }

}

