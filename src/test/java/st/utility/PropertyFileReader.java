package st.utility;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;

public class PropertyFileReader {

    private static Properties properties = new Properties();
    private static InputStream inputStream = null;

    public PropertyFileReader() throws Exception {
        loadProperties();

    }

    public static void loadProperties() throws Exception {
        try {
            inputStream = new FileInputStream("config.properties");
            properties.load(inputStream);
            Log.info("Info : Loading config properties");
        } catch (IOException e) {
            Log.info("Error : Loading config properties - "+e);
            throw new Exception("Error : Loading config properties - "+e);
        }

    }

    public static void setSystemProperties() throws Exception {
        Properties props = System.getProperties();
        loadProperties();
        try{
            props.setProperty("udid", properties.getProperty("udid"));
            props.setProperty("autoAcceptAlerts", properties.getProperty("autoAcceptAlerts"));
            props.setProperty("defaultImplicitTimeout", properties.getProperty("defaultImplicitTimeout"));
            props.setProperty("defaultExplicitTimeout", properties.getProperty("defaultExplicitTimeout"));
            props.setProperty("testDataFilePath", properties.getProperty("testDataFilePath"));
            props.setProperty("appActivity", properties.getProperty("appActivity"));
            props.setProperty("appAndroid", properties.getProperty("appAndroid"));
            props.setProperty("appIos", properties.getProperty("appIos"));
            props.setProperty("bundleId", properties.getProperty("bundleId"));
            props.setProperty("isNative", properties.getProperty("isNative"));
            props.setProperty("isRealDevice", properties.getProperty("isRealDevice"));
            props.setProperty("isHeadless", properties.getProperty("isHeadless"));
            props.setProperty("noResetAppData", properties.getProperty("noResetAppData"));
            props.setProperty("isWeb", properties.getProperty("isWeb"));
            props.setProperty("runPlatform", properties.getProperty("runPlatform"));
            props.setProperty("appiumManualStart", properties.getProperty("appiumManualStart"));
            props.setProperty("operatingSystem", properties.getProperty("operatingSystem"));
            props.setProperty("appiumLog", properties.getProperty("appiumLog"));
            if (System.getProperty("runPlatform").equalsIgnoreCase("Desktop")) {
                props.setProperty("platform", properties.getProperty("runPlatform"));
                props.setProperty("browser", properties.getProperty("browser"));
                props.setProperty("mainURL", properties.getProperty("mainURL"));
            }
            if (System.getProperty("runPlatform").equalsIgnoreCase("ios")) {
                props.setProperty("platform", properties.getProperty("runPlatform"));
                props.setProperty("browser", properties.getProperty("iosBrowser"));
                props.setProperty("platformName", properties.getProperty("iosPlatformName"));
                props.setProperty("platformVersion", properties.getProperty("iosPlatformVersion"));
                props.setProperty("deviceName", properties.getProperty("iosDeviceName"));
            } else if (System.getProperty("runPlatform").equalsIgnoreCase("android")) {
                props.setProperty("platform", properties.getProperty("runPlatform"));
                props.setProperty("browser", properties.getProperty("androidBrowser"));
                props.setProperty("platformName", properties.getProperty("androidPlatformName"));
                props.setProperty("platformVersion", properties.getProperty("androidPlatformVersion"));
                props.setProperty("deviceName", properties.getProperty("androidDeviceName"));
                props.setProperty("avdName", properties.getProperty("androidAvdName"));
            }

            Log.info("Info : Read properties successfully");
        } catch(Exception e){
            Log.info("Fail : Read properties - "+e);
        }
    }


    public String readProperty(String key) {
        return properties.getProperty(key);
    }

}

