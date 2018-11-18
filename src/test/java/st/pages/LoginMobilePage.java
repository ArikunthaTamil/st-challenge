package st.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import st.utility.Log;
import st.wrappers.DriverFactory;
import st.wrappers.Elementwrappers;
import st.wrappers.TestDataValue;

import java.time.Duration;
import java.util.List;

public class LoginMobilePage extends LoadableComponent<LoginMobilePage> {

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
    WebElement btnNavigationMenu;

    @AndroidFindBy(xpath = "//android.widget.TextView[@id='tv_tab_title']")
    List<WebElement> tabTitles;

    @AndroidFindBy(id = "root_view")
    List<WebElement> listAriticles;

    @AndroidFindBy(id = "tv_title")
    WebElement lblTopStories;

    @AndroidFindBy(id = "article_headline")
    WebElement lblHeadline;

    @AndroidFindBy(id = "tv_login")
    WebElement btnLogin;

    @AndroidFindBy(id = "tv_login_name")
    WebElement lblLoggedInUser;

    @AndroidFindBy(id = "tv_logout")
    WebElement btnLogout;

    @AndroidFindBy(id = "rl_search_bar")
    WebElement searchField;

    @AndroidFindBy(id = "search_src_text")
    WebElement txtMainSearchField;

    @AndroidFindBy(id = "iv_home")
    WebElement btnHome;

    @AndroidFindBy(id = "btn_boomark")
    WebElement btnArticleBookmark;

    @AndroidFindBy(id = "btn_back")
    WebElement btnBack;

    @AndroidFindBy(id = "iv_saved")
    WebElement btnHomeBookmark;

    @AndroidFindBy(id = "iv_more_from_st")
    WebElement btnMoreProducts;

    @AndroidFindBy(id = "tv_login_label")
    WebElement lblLogin;

    @AndroidFindBy(id = "et_ldap_login_username")
    WebElement txtLoginID;

    @AndroidFindBy(id = "et_ldap_login_password")
    WebElement txtLoginPassword;

    @AndroidFindBy(id = "btn_ldap_login_continue")
    WebElement btnSubmit;

    @AndroidFindBy(id = "tvMessageView")
    WebElement lblErrorMsg;

    private RemoteWebDriver driver;
    private String mainArticleHeadline;

    public LoginMobilePage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(DriverFactory.maxElementWait)), this);
        isLoaded();
    }

    public boolean clickNavigationMenu() {
        boolean isTrue = false;
        try {
            isTrue = (Elementwrappers.click(driver, btnNavigationMenu));
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Click on Navigation menu button");
        } catch (Exception e) {
            Log.info("Error" + "Click on Navigation menu button" + e);
        }
        return isTrue;
    }

    public boolean clickLogin() {
        boolean isTrue = false;
        try {
            new BasePage(driver).handleAdScreen();
            if(clickNavigationMenu() ) {
                isTrue = (Elementwrappers.click(driver, btnLogin));
                String output = isTrue ? "Pass" : "Fail";
                Log.info(output + "Click on Login button");
            }
        } catch (Exception e) {
            Log.info("Error" + "Click on Login button" + e);
        }
        return isTrue;
    }

    public boolean enterLoginID() {
        boolean isTrue = false;
        try {
            isTrue = (Elementwrappers.enterText((AppiumDriver) driver, txtLoginID, TestDataValue.loginID));
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
            isTrue = (Elementwrappers.enterText((AppiumDriver) driver, txtLoginPassword , TestDataValue.loginPassword));
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
            isTrue = (Elementwrappers.click(driver, btnSubmit));
            isTrue = isTrue && !Elementwrappers.isDisplayed(driver,lblErrorMsg);
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Click on submit button");
        } catch (Exception e) {
            Log.info("Error" + "Click on submit button" + e);
        }
        return isTrue;
    }

    public boolean clickOnMainArticle() {
        boolean isTrue = false;
        try {
            mainArticleHeadline = listAriticles.get(1).findElement(By.id("article_title")).getText();
            isTrue = (Elementwrappers.click(driver, listAriticles.get(1)));
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Click on main article");
        } catch (Exception e) {
            Log.info("Error" + "Click on main article" + e);
        }
        return isTrue;
    }

    public boolean verifyArticleHeadline() {
        boolean isTrue = false;
        try {
            if(Elementwrappers.click(driver,lblTopStories)) {
                    isTrue = lblHeadline.getText().equalsIgnoreCase(mainArticleHeadline);
                    String output = isTrue ? "Pass" : "Fail";
                    Log.info(output + "Verify article headline");
            }
        } catch (Exception e) {
            Log.info("Error" + "Verify article headline" + e);
        }
        return isTrue;
    }

    public boolean verifyLoggedInUser() {
        boolean isTrue = false;
        try {
            if(clickNavigationMenu() ) {
                if (Elementwrappers.isDisplayed(driver, btnLogout)) {
                    isTrue = lblLoggedInUser.getText().equalsIgnoreCase(TestDataValue.loginID);
                    isTrue = isTrue && Elementwrappers.isDisplayed(driver, btnLogout);
                    String output = isTrue ? "Pass" : "Fail";
                    Elementwrappers.click(driver, btnHome);
                    Log.info(output + "Verify logged in username");
                }
            }
        } catch (Exception e) {
            Log.info("Error" + "Verify logged in username" + e);
        }
        return isTrue;
    }

    public boolean loginAsValidUser() throws InterruptedException {
        boolean isTrue = false;
        try {
            if(Elementwrappers.isDisplayed(driver,lblLogin)) {
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

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        BasePage.waitForPageLoad(driver);
    }
}
