package st.glue;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.remote.RemoteWebDriver;
import junit.framework.Assert;
import st.pages.BasePage;
import st.pages.LoginMobilePage;
import st.utility.Log;

public class LoginGlue {

    RemoteWebDriver driver = BaseGlue.driver;

    LoginMobilePage loginPage;
    BasePage basePage;

    public LoginGlue() {
        loginPage = new LoginMobilePage(driver);
        basePage = new BasePage(driver);
    }

    @Given("^Launch app with Intro screen$")
    public void launch_app_with_Intro_screen() throws Throwable {
        Log.info("Info : ===>>> Handle intro screens");
        Assert.assertTrue("Fail to handle introduction page", basePage.initScreenHandler(driver));
    }

    @And("^Click on Login button$")
    public void click_on_Login_button() throws Throwable {
        Log.info("Info : ===>>> Click Login button from navigation pane");
        Assert.assertTrue("Fail to click on login button", loginPage.clickLogin());
    }

    @And("^Enter valid login username and password$")
    public void enter_valid_login_username_and_password() throws Throwable {
        Log.info("Info : ===>>> Enter login username and password");
        Assert.assertTrue("Fail to enter login username and password", loginPage.loginAsValidUser());
    }

    @And("^Click on login button$")
    public void click_on_login_button() throws Throwable {
        Log.info("Info : ===>>> Click on submit button in login screen");
        Assert.assertTrue("Fail to click on submit button in login screen", loginPage.clickSubmit());
    }

    @And("^Click on the main article from Home Page$")
    public void click_on_the_main_article_from_Home_Page() throws Throwable {
        Log.info("Info : ===>>> Click on first main article");
        Assert.assertTrue("Fail to click on first main article", loginPage.clickOnMainArticle());

    }

    @Then("^Verify the article headline text in detailed view page$")
    public void verify_the_article_headline_text_in_detailed_view_page() throws Throwable {
        Log.info("Info : ===>>> Verify the main article headline");
        Assert.assertTrue("Fail to the verify main article headline", loginPage.verifyArticleHeadline());

    }
}
