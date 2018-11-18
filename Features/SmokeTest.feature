Feature: ST SG Smoke Test

  @Login_OpenMainArticle
  Scenario Outline: Testcase ID "<tcId>" - ST SG - Login as valid user and open main article
    #-- Entry Points,Login, Click main article
    Given Launch app with Intro screen
    And Click on Login button
    And Enter valid login username and password
    And Click on login button
    And Click on the main article from Home Page
    Then Verify the article headline text in detailed view page

    Examples:
      | tcId              |
      | login_openArticle |

