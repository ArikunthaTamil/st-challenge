# st-challenge

Description: This repo contains BDD script for straitstimes Android mobile and Desktop browser(Chrome & Safari)


Framework Details:

a. Main Runner class implemented at st-challenge/src/test/java/st/runner and specify @SmokeTestMobile tag to run all mobile cases & @SmokeTestDesktop to run Desktop browser case

b. Copy straitstimes.apk(Download from https://apkpure.com/the-straits-times-for-smartphone/com.buuuk.st/download?from=details) into directory st-challenge/Apps/Android

c. Execution config details can be found at st-challenge/config.properties(Specify platform and AVD Emulator name for Android)

d. Scenarios SmokeTest.feature can be found at st-challenge/Features

e. Reports can & Screenshots be found after test run at st-challenge/reports & st-challenge/Screenshots

Note: Make sure appium servre started at port 4723 (appium -p 4732)

Appium Environment Setup:

a. Run $ npm install -g appium in terminal window

Note: Make sure you already installed Node Js https://nodejs.org/en/download/

Android Environment Setup:

a. Download and install from https://developer.android.com/studio/index.html

b. Set $ANDROID_HOME environment variable pointing to ~/Library/Android/sdk

c. Also add ~/Library/Android/sdk/platform-tool;/Library/Android/sdk/tools
directories to $PATH

d. Make sure $JAVA_HOME environment variable set and added to $PATH

e. Install SDK for API leave 25 and create new virtual device with x64/x86 type system image(use this name in config.properties file)

Note: The path needs to be updated as per current machine installation location

Tools and version details:

ANDROID STUDIO - 3.1

JAVA - 1.8.0_151

APPIUM - v1.9.0

OS Name & Version - macOS High Sierra & 10.13.6

ST APP - v6.6.8

Chrome - 70.0.3538.102

Safari - 12.0.1 (13606.2.104.1.2)
