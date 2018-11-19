Feature: ST SG Smoke Test

  @SmokeTestMobile
  @SmokeTest
  @Login_OpenMainArticle
  Scenario Outline: Testcase ID "<tcId>" - ST SG - Login as valid user and open main article
    #-- Entry Points,Login, Click main article
    Given Launch app with Intro screen
    And Click on Login button
    And Enter valid login username and password
    When Click on submit button
    Then I should see logged in username
    When Click on the main article from Home Page
    Then Verify the article headline text and media in detailed view page

    Examples:
      | tcId              |
      | login_openArticle |

  @SmokeTestMobile
  @SmokeTest
  @Login_BookmarkMainArticle
  Scenario Outline: Testcase ID "<tcId>" - ST SG - Bookmark main article
    #-- Entry Points,Login, Bookmark main article
    Given Launch app with Intro screen
    And Click on Login button
    And Enter valid login username and password
    And Click on submit button
    And Click on the main article from Home Page
    When Bookmark the main article
    Then Verify the bookmarked article in Bookmark section

    Examples:
      | tcId                  |
      | login_bookmarkArticle |

  @SmokeTestMobile
  @SmokeTest
  @Login_SearchMainArticle
  Scenario Outline: Testcase ID "<tcId>" - ST SG - Search with keywords
    #-- Entry Points,Login, Search article
    Given Launch app with Intro screen
    And Click on Login button
    And Enter valid login username and password
    And Click on submit button
    And Click on search field in navigation pane
    When Enter main article title to search and submit
    Then Verify the article displayed in result at first position
    When Enter special characters to search and submit
    Then Verify that search result is empty

    Examples:
      | tcId                  |
      | login_searchArticle   |

  @SmokeTestMobile
  @SmokeTest
  @Login_OpenOtherProduct
  Scenario Outline: Testcase ID "<tcId>" - ST SG - Open other product
    #-- Entry Points,Login, open other product
    Given Launch app with Intro screen
    And Click on Login button
    And Enter valid login username and password
    And Click on submit button
    And Open more ST products screen
    When I select the given ST product
    Then I should see the selected product main title

    Examples:
      | tcId                  |
      | login_openMoreProduct  |

  @SmokeTestDesktop
  @SmokeTest
  @Login_OpenMainArticleDesktop
  Scenario Outline: Testcase ID "<tcId>" - ST SG - Open main article Desktop
    #-- Entry Points,Login, Click main article
    Given I'm on ST home page
    And Click on Login button
    And Enter valid login username and password
    When Click on submit button
    Then I should see logged in username
    When Click on the main article from Home Page
    Then Verify the article headline text and media in detailed view page

    Examples:
      | tcId               |
      | login_openArticle  |

