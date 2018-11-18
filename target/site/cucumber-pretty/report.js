$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("SmokeTest.feature");
formatter.feature({
  "line": 1,
  "name": "ST SG Smoke Test",
  "description": "",
  "id": "st-sg-smoke-test",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 4,
  "name": "Testcase ID \"\u003ctcId\u003e\" - ST SG - Login as valid user and open main article",
  "description": "",
  "id": "st-sg-smoke-test;testcase-id-\"\u003ctcid\u003e\"---st-sg---login-as-valid-user-and-open-main-article",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 3,
      "name": "@Login_OpenMainArticle"
    }
  ]
});
formatter.step({
  "comments": [
    {
      "line": 5,
      "value": "#-- Entry Points,Login, Click main article"
    }
  ],
  "line": 6,
  "name": "Launch app with Intro screen",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "Click on Login button",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "Enter valid login username and password",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "Click on login button",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "Click on the main article from Home Page",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "Verify the article headline text in detailed view page",
  "keyword": "Then "
});
formatter.examples({
  "line": 13,
  "name": "",
  "description": "",
  "id": "st-sg-smoke-test;testcase-id-\"\u003ctcid\u003e\"---st-sg---login-as-valid-user-and-open-main-article;",
  "rows": [
    {
      "cells": [
        "tcId"
      ],
      "line": 14,
      "id": "st-sg-smoke-test;testcase-id-\"\u003ctcid\u003e\"---st-sg---login-as-valid-user-and-open-main-article;;1"
    },
    {
      "cells": [
        "login_openArticle"
      ],
      "line": 15,
      "id": "st-sg-smoke-test;testcase-id-\"\u003ctcid\u003e\"---st-sg---login-as-valid-user-and-open-main-article;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 26101561267,
  "status": "passed"
});
formatter.scenario({
  "line": 15,
  "name": "Testcase ID \"login_openArticle\" - ST SG - Login as valid user and open main article",
  "description": "",
  "id": "st-sg-smoke-test;testcase-id-\"\u003ctcid\u003e\"---st-sg---login-as-valid-user-and-open-main-article;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 3,
      "name": "@Login_OpenMainArticle"
    }
  ]
});
formatter.step({
  "comments": [
    {
      "line": 5,
      "value": "#-- Entry Points,Login, Click main article"
    }
  ],
  "line": 6,
  "name": "Launch app with Intro screen",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "Click on Login button",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "Enter valid login username and password",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "Click on login button",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "Click on the main article from Home Page",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "Verify the article headline text in detailed view page",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginGlue.launch_app_with_Intro_screen()"
});
formatter.result({
  "duration": 4305631022,
  "status": "passed"
});
formatter.match({
  "location": "LoginGlue.click_on_Login_button()"
});
formatter.result({
  "duration": 8828703679,
  "status": "passed"
});
formatter.match({
  "location": "LoginGlue.enter_valid_login_username_and_password()"
});
formatter.result({
  "duration": 13061569629,
  "status": "passed"
});
formatter.match({
  "location": "LoginGlue.click_on_login_button()"
});
formatter.result({
  "duration": 31376373039,
  "status": "passed"
});
formatter.match({
  "location": "LoginGlue.click_on_the_main_article_from_Home_Page()"
});
formatter.result({
  "duration": 48306535837,
  "status": "passed"
});
formatter.match({
  "location": "LoginGlue.verify_the_article_headline_text_in_detailed_view_page()"
});
formatter.result({
  "duration": 84934865264,
  "status": "passed"
});
formatter.after({
  "duration": 1075523134,
  "status": "passed"
});
});