package st.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import st.utility.Log;
import st.wrappers.Elementwrappers;
import st.wrappers.TestDataValue;

import java.util.List;

public class LoginDesktopPage extends LoadableComponent<LoginDesktopPage> {

    @FindBy(css = ".celtra-group")
    WebElement crossBtn;

    @FindBy(css = "li.nav-login")
    WebElement btnLogin;

    @FindBy(css = "li.nav-logout")
    WebElement btnLogout;

    @FindBy(css = "li.nav-user")
    WebElement lblUserName;

    @FindBy(css = "#j_username")
    WebElement txtUserName;

    @FindBy(css = "#j_password")
    WebElement txtPassword;

    @FindBy(css = ".btn")
    WebElement btnSubmit;

    @FindBy(css = ".navbar-brand")
    WebElement mainPageTitle;

    @FindBy(css = ".pane-articles-topstory-nodequeue")
    List<WebElement> listAriticles;

    @FindBy(css = ".pane-articles-topstory-nodequeue a.block-link")
    List<WebElement> mediaLink;

    @FindBy(css = ".pane-articles-topstory-nodequeue .story-headline a")
    List<WebElement> artileTitle;

    @FindBy(css = ".node-header h1.node-title")
    WebElement lblTitleInDetailePage;

    @FindBy(css = "video[id^=video]")
    WebElement videoInDetailePage;

    @FindBy(css = "picture.img-responsive img.img-responsive")
    WebElement pictureInDetailePage;

    @FindBy(xpath ="//iframe[contains(@name,'expand_iframe')]")
    WebElement adFrame;

    @FindBy(id = "close-button")
    WebElement closeAdOverLay;

    private RemoteWebDriver driver;
    private String mainArticleHeadline;

    public LoginDesktopPage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        isLoaded();
    }
    public boolean enterLoginID() {
        boolean isTrue = false;
        try {
            isTrue = (Elementwrappers.sendKeys(driver, txtUserName, TestDataValue.loginID));
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Enter login ID");
        } catch (Exception e) {
            Log.info("Error" + "Enter login ID" + e);
        }
        return isTrue;
    }

    public boolean enterLoginPassword() {
        boolean isTrue = false;
        try {
            isTrue = (Elementwrappers.sendKeys( driver, txtPassword , TestDataValue.loginPassword));
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Enter login password");
        } catch (Exception e) {
            Log.info("Error" + "Enter login password" + e);
        }
        return isTrue;
    }

    public boolean clickSubmit() {
        boolean isTrue = false;
        try {
            isTrue = Elementwrappers.click(driver, btnSubmit);
            isTrue = isTrue && Elementwrappers.isDisplayed(driver,mainPageTitle);
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Click on submit button");
        } catch (Exception e) {
            Log.info("Error" + "Click on submit button" + e);
        }
        return isTrue;
    }

    public boolean clickLogin() {
        boolean isTrue = false;
        try {
            if(handleAdScreen() ) {
                driver.switchTo().defaultContent();
                Thread.sleep(5000);
                isTrue = Elementwrappers.click(driver, btnLogin);
                String output = isTrue ? "Pass" : "Fail";
                Log.info(output + "Click on Login button");
            }
        } catch (Exception e) {
            Log.info("Error" + "Click on Login button" + e);
        }
        return isTrue;
    }
    public boolean loginAsValidUser() throws InterruptedException {
        boolean isTrue = false;
        try {
            if(Elementwrappers.isDisplayed(driver,txtUserName)) {
                isTrue = enterLoginID();
                isTrue = isTrue && enterLoginPassword();
                String result = isTrue ? "Pass" : "Fail";
                Log.info(result + " : Login and submit button");
            }
        } catch (Exception e) {
            Log.info("Error : Login step - " + e);
        }
        return isTrue;
    }

    public boolean verifyLoggedInUser() {
        boolean isTrue = false;
        try {
                isTrue = lblUserName.getText().contains(TestDataValue.loginID);
                isTrue = isTrue && Elementwrappers.isDisplayed(driver, btnLogout);
                String output = isTrue ? "Pass" : "Fail";
                Log.info(output + "Verify logged in username");
        } catch (Exception e) {
            Log.info("Error" + "Verify logged in username" + e);
        }
        return isTrue;
    }

    public boolean verifyArticleHeadline(int index) {
        boolean isTrue = false;
        try {
                isTrue = lblTitleInDetailePage.getText().equalsIgnoreCase(mainArticleHeadline);
                String output = isTrue ? "Pass" : "Fail";
                Log.info(output + "Verify article headline");

        } catch (Exception e) {
            Log.info("Error" + "Verify article headline" + e);
        }
        return isTrue;
    }

    public boolean verifyArticleMediaPresence() {
        boolean isTrue = false;
        try {
            isTrue = Elementwrappers.isDisplayed(driver,videoInDetailePage) || Elementwrappers.isDisplayed(driver,pictureInDetailePage);
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Verify article media presence");
        } catch (Exception e) {
            Log.info("Error" + "Verify article media presence" + e);
        }
        return isTrue;
    }

    public boolean clickOnMainArticle() {
        boolean isTrue = false;
        try {
            isTrue = getMainArticleTitle(0);
            isTrue = isTrue && (Elementwrappers.click(driver, listAriticles.get(0)));
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Click on main article");
        } catch (Exception e) {
            Log.info("Error" + "Click on main article" + e);
        }
        return isTrue;
    }

    public boolean getMainArticleTitle(int index) {
        boolean isTrue = false;
        try {
            mainArticleHeadline = listAriticles.get(index).findElement(By.cssSelector("span.story-headline a")).getText();
            isTrue = !mainArticleHeadline.isEmpty();
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Get main article title");
        } catch (Exception e) {
            Log.info("Error" + "Get main article title" + e);
        }
        return isTrue;
    }

    public boolean handleAdScreen() {
        boolean isTrue = false;
        try {
            driver.switchTo().frame(adFrame);
            Thread.sleep(5000);
            isTrue = Elementwrappers.isDisplayed(driver,closeAdOverLay);
            closeAdOverLay.click();
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Click on close icon in Ad screen");
        } catch (Exception e) {
            Log.info("Error" + "Click on close icon in Ad screen" + e);
        }
        return isTrue;
    }
    public boolean isAppInHomePage() {
        boolean isTrue = false;
        try {
            isTrue = driver.getCurrentUrl().equalsIgnoreCase(System.getProperty("mainURL"));
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Verify on main page");
        } catch (Exception e) {
            Log.info("Error" + "Verify on main page" + e);
        }
        return isTrue;
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        BasePage.waitForPageLoad(driver);
    }

}
