package st.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
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

    @AndroidFindBy(id = "row_container")
    List<WebElement> listArticleResult;

    @AndroidFindBy(xpath = "//android.widget.RelativeLayout/android.widget.TextView")
    List<WebElement> moreProducts;

    @AndroidFindBy(id = "article_title")
    WebElement txtArticleTitle;

    @AndroidFindBy(id = "tv_title")
    WebElement lblTopStories;

    @AndroidFindBy(id = "article_headline")
    WebElement lblHeadline;

    @AndroidFindBy(id = "viewPager")
    WebElement detailedViewPager;

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

    public boolean clickBackBtn() {
        boolean isTrue = false;
        try {
            isTrue = (Elementwrappers.click(driver, btnBack));
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Click on back button");
        } catch (Exception e) {
            Log.info("Error" + "Click on back button" + e);
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
            isTrue = isTrue && Elementwrappers.isDisplayed(driver,lblTopStories);
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Click on submit button");
        } catch (Exception e) {
            Log.info("Error" + "Click on submit button" + e);
        }
        return isTrue;
    }

    public boolean clickSearchField() {
        boolean isTrue = false;
        try {
            isTrue = getMainArticleTitle(1);
            isTrue = isTrue && clickNavigationMenu();
            isTrue = isTrue && Elementwrappers.click(driver,searchField);
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Click on search field");
        } catch (Exception e) {
            Log.info("Error" + "Click on search field" + e);
        }
        return isTrue;
    }

    public boolean SearchValidKeywords() {
        boolean isTrue = false;
        try {
            isTrue = Elementwrappers.enterText((AppiumDriver) driver,txtMainSearchField,TestDataValue.ValidKeywords);
            Elementwrappers.pressEnter((AndroidDriver) driver);
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Enter keyword and search");
        } catch (Exception e) {
            Log.info("Error" + "Enter keyword and search" + e);
        }
        return isTrue;
    }

    public boolean SearchInvalidKeywords() {
        boolean isTrue = false;
        try {
            isTrue = Elementwrappers.enterText((AppiumDriver) driver,txtMainSearchField,TestDataValue.InvalidKeywords);
            Elementwrappers.pressEnter((AndroidDriver) driver);
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Enter invalid keyword and search");
        } catch (Exception e) {
            Log.info("Error" + "Enter invalid keyword and search" + e);
        }
        return isTrue;
    }

    public boolean OpenMoreSTProductsScreen() {
        boolean isTrue = false;
        try {
            isTrue = clickNavigationMenu();
            isTrue = isTrue && Elementwrappers.click(driver,btnMoreProducts);
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Click on more products");
        } catch (Exception e) {
            Log.info("Error" + "Click on more products" + e);
        }
        return isTrue;
    }

    public boolean SelectGivenSTProduct() {
        boolean isTrue = false;
        int productSize = 0;
        try {
            while(productSize < moreProducts.size()) {
                if(moreProducts.get(productSize).getText().equalsIgnoreCase(TestDataValue.OtherProduct)){
                    Log.info("Matching product found with given name "+TestDataValue.OtherProduct);
                    isTrue = Elementwrappers.click(driver,moreProducts.get(productSize));
                    break;
                }
                productSize++;
            }
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Select the given product");
        } catch (Exception e) {
            Log.info("Error" + "Select the given product" + e);
        }
        return isTrue;
    }

    public boolean verifyFirstArticleInResult() {
        boolean isTrue = false;
        try {
            isTrue = listArticleResult.get(0).findElement(By.id("article_title")).getText().contains(TestDataValue.ValidKeywords);
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Verify first article title in search result");
        } catch (Exception e) {
            Log.info("Error" + "Verify first article title in search result" + e);
        }
        return isTrue;
    }

    public boolean verifyEmptySearchResult() {
        boolean isTrue = false;
        try {
            isTrue = (listArticleResult.size() < 1)?true:false;
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Verify empty search result");
        } catch (Exception e) {
            Log.info("Error" + "Verify empty search result" + e);
        }
        return isTrue;
    }

    public boolean verifySTProductMainTitle() {
        boolean isTrue = false;
        try {
            isTrue = lblTopStories.getText().contains(TestDataValue.OtherProduct);
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Verify product main title");
        } catch (Exception e) {
            Log.info("Error" + "Verify product main title" + e);
        }
        return isTrue;
    }


    public boolean getMainArticleTitle(int index) {
        boolean isTrue = false;
        try {
            mainArticleHeadline = listAriticles.get(index).findElement(By.id("article_title")).getText();
            isTrue = !mainArticleHeadline.isEmpty();
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Get main article title");
        } catch (Exception e) {
            Log.info("Error" + "Get main article title" + e);
        }
        return isTrue;
    }

    public boolean clickOnMainArticle() {
        boolean isTrue = false;
        try {
            isTrue = getMainArticleTitle(1);
            isTrue = isTrue && (Elementwrappers.click(driver, listAriticles.get(1)));
            String output = isTrue ? "Pass" : "Fail";
            Log.info(output + "Click on main article");
        } catch (Exception e) {
            Log.info("Error" + "Click on main article" + e);
        }
        return isTrue;
    }

    public boolean clickBookmarkIcon() {
        boolean isTrue = false;
        try {
            if(Elementwrappers.click(driver,lblTopStories)) {
                isTrue = !btnArticleBookmark.isSelected();
                isTrue = isTrue && Elementwrappers.click(driver, btnArticleBookmark);
                String output = isTrue ? "Pass" : "Fail";
                Log.info(output + "Click on bookmark icon");
            }
        } catch (Exception e) {
            Log.info("Error" + "Click on bookmark icon" + e);
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

    public boolean verifyArticleMediaPresence() {
        boolean isTrue = false;
        try {
                isTrue = Elementwrappers.isDisplayed(driver,detailedViewPager.findElement(By.id("article_image")));
                String output = isTrue ? "Pass" : "Fail";
                Log.info(output + "Verify article media presence");
        } catch (Exception e) {
            Log.info("Error" + "Verify article media presence" + e);
        }
        return isTrue;
    }

    public boolean verifyLoggedInUser() {
        boolean isTrue = false;
        try {
            if(clickNavigationMenu() ) {
                    isTrue = lblLoggedInUser.getText().contains(TestDataValue.loginID);
                    isTrue = isTrue && Elementwrappers.isDisplayed(driver, btnLogout);
                    String output = isTrue ? "Pass" : "Fail";
                    Elementwrappers.click(driver, btnHome);
                    Log.info(output + "Verify logged in username");
            }
        } catch (Exception e) {
            Log.info("Error" + "Verify logged in username" + e);
        }
        return isTrue;
    }

    public boolean verifyBookmarkedArticle() {
        boolean isTrue = false;
        try {
            if(clickBackBtn() ) {
                isTrue = clickNavigationMenu();
                isTrue = isTrue && Elementwrappers.click(driver, btnHomeBookmark);;
                isTrue = isTrue && listAriticles.get(0).findElement(By.id("article_title")).getText().contains(mainArticleHeadline);
                String output = isTrue ? "Pass" : "Fail";
                Log.info(output + "Verify bookmarked article name");
            }
        } catch (Exception e) {
            Log.info("Error" + "Verify bookmarked article name" + e);
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
