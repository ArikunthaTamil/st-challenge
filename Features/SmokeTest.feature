Feature: ST SG Smoke Test

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

  @SmokeTest
  @Login_SearchMainArticle
  Scenario Outline: Testcase ID "<tcId>" - ST SG - Bookmark main article
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

