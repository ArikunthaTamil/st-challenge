package st.glue;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.remote.RemoteWebDriver;
import junit.framework.Assert;
import st.pages.BasePage;
import st.pages.LoginDesktopPage;
import st.pages.LoginMobilePage;
import st.utility.Log;

public class LoginGlue {

    RemoteWebDriver driver = BaseGlue.driver;

    LoginMobilePage loginPage;
    BasePage basePage;
    LoginDesktopPage desktopLoginPage;
    String platform = System.getProperty("runPlatform");

    public LoginGlue() {
        loginPage = new LoginMobilePage(driver);
        basePage = new BasePage(driver);
        desktopLoginPage = new LoginDesktopPage(driver);
    }

    @Given("^Launch app with Intro screen$")
    public void launch_app_with_Intro_screen() throws Throwable {
        Log.info("Info : ===>>> Handle intro screens");
        Assert.assertTrue("Fail to handle introduction page", basePage.initScreenHandler(driver));
    }

    @Given("^I'm on ST home page$")
    public void i_m_on_ST_home_page() throws Throwable {
        Log.info("Info : ===>>> Check if it is main page");
        Assert.assertTrue("Fail to verify main page", desktopLoginPage.isAppInHomePage());
    }

    @And("^Click on Login button$")
    public void click_on_Login_button() throws Throwable {
        Log.info("Info : ===>>> Click Login button from navigation pane");
        if (platform.equalsIgnoreCase("Desktop")) {
            Assert.assertTrue("Fail to click on login button", desktopLoginPage.clickLogin());
        } else{
            Assert.assertTrue("Fail to click on login button", loginPage.clickLogin());
        }
    }

    @And("^Enter valid login username and password$")
    public void enter_valid_login_username_and_password() throws Throwable {
        Log.info("Info : ===>>> Enter login username and password");
        if (platform.equalsIgnoreCase("Desktop")) {
            Assert.assertTrue("Fail to enter login username and password", desktopLoginPage.loginAsValidUser());
        }else{
            Assert.assertTrue("Fail to enter login username and password", loginPage.loginAsValidUser());
        }
    }

    @And("^Click on submit button$")
    public void click_on_submit_button() throws Throwable {
        Log.info("Info : ===>>> Click on submit button in login screen");
        if (platform.equalsIgnoreCase("Desktop")) {
            Assert.assertTrue("Fail to click on submit button in login screen", desktopLoginPage.clickSubmit());
        }else {
            Assert.assertTrue("Fail to click on submit button in login screen", loginPage.clickSubmit());
        }
    }

    @Then("^I should see logged in username$")
    public void i_should_see_logged_in_username() throws Throwable {
        Log.info("Info : ===>>> Verify the logged in username");
        if (platform.equalsIgnoreCase("Desktop")) {
            Assert.assertTrue("Fail to check logged in username", desktopLoginPage.verifyLoggedInUser());
        }else{
            Assert.assertTrue("Fail to check logged in username", loginPage.verifyLoggedInUser());
        }
    }

    @When("^Click on the main article from Home Page$")
    public void click_on_the_main_article_from_Home_Page() throws Throwable {
        Log.info("Info : ===>>> Click on first main article");
        if (platform.equalsIgnoreCase("Desktop")) {
            Assert.assertTrue("Fail to click on first main article", desktopLoginPage.clickOnMainArticle());
        }else{
            Assert.assertTrue("Fail to click on first main article", loginPage.clickOnMainArticle());
        }

    }

    @Then("^Verify the article headline text and media in detailed view page$")
    public void verify_the_article_headline_text_in_detailed_view_page() throws Throwable {
        Log.info("Info : ===>>> Verify the main article headline and media presence");
        if (platform.equalsIgnoreCase("Desktop")) {
            Assert.assertTrue("Fail to the verify main article headline and media presence", desktopLoginPage.verifyArticleHeadline(0) && desktopLoginPage.verifyArticleMediaPresence());
        } else{
            Assert.assertTrue("Fail to the verify main article headline and media presence", loginPage.verifyArticleHeadline() && loginPage.verifyArticleMediaPresence());
        }

    }

    @When("^Bookmark the main article$")
    public void bookmark_the_main_article() throws Throwable {
        Log.info("Info : ===>>> Bookmark the main article");
        Assert.assertTrue("Fail to bookmark the main article", loginPage.clickBookmarkIcon());
    }

    @Then("^Verify the bookmarked article in Bookmark section$")
    public void verify_the_bookmarked_article_in_Bookmark_section() throws Throwable {
        Log.info("Info : ===>>> Verify the bookmarked article");
        Assert.assertTrue("Fail to verify the bookmarked article", loginPage.verifyBookmarkedArticle());
    }

    @And("^Click on search field in navigation pane$")
    public void click_on_search_field_in_navigation_pane() throws Throwable {
        Log.info("Info : ===>>> Click on search field");
        Assert.assertTrue("Fail to click on search field", loginPage.clickSearchField());
    }

    @When("^Enter main article title to search and submit$")
    public void enter_main_article_title_to_search_and_submit() throws Throwable {
        Log.info("Info : ===>>> Enter article name to search");
        Assert.assertTrue("Fail to enter article name to search", loginPage.SearchValidKeywords());
    }

    @Then("^Verify the article displayed in result at first position$")
    public void verify_the_article_displayed_in_result_at_first_position() throws Throwable {
        Log.info("Info : ===>>> Verify the first article in search result");
        Assert.assertTrue("Fail to verify first article in search result", loginPage.verifyFirstArticleInResult());
    }

    @When("^Enter special characters to search and submit$")
    public void enter_special_characters_to_search_and_submit() throws Throwable {
        Log.info("Info : ===>>> key in keywords and search");
        Assert.assertTrue("Fail to key in keywords and search", loginPage.SearchInvalidKeywords());
    }

    @Then("^Verify that search result is empty$")
    public void verify_that_search_result_is_empty() throws Throwable {
        Log.info("Info : ===>>> Verify the empty search result");
        Assert.assertTrue("Fail to verify the empty search result", loginPage.verifyEmptySearchResult());
    }
    @Given("^Open more ST products screen$")
    public void open_more_ST_products_screen() throws Throwable {
        Log.info("Info : ===>>> Open more products from ST");
        Assert.assertTrue("Fail to open more products from ST", loginPage.OpenMoreSTProductsScreen());
    }

    @When("^I select the given ST product$")
    public void i_select_the_given_ST_product() throws Throwable {
        Log.info("Info : ===>>> select given product");
        Assert.assertTrue("Fail to select given product", loginPage.SelectGivenSTProduct());
    }

    @Then("^I should see the selected product main title$")
    public void i_should_see_the_selected_product_main_title() throws Throwable {
        Log.info("Info : ===>>> Verify the product main title");
        Assert.assertTrue("Fail to verify the product main title", loginPage.verifySTProductMainTitle());
    }
}
