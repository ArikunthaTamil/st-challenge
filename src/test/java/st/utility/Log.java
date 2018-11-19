package st.utility;

import org.apache.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import st.glue.BaseGlue;
import st.wrappers.Elementwrappers;

public class Log {

    static Logger Log=Logger.getLogger(Log.class);
    static ExtentTest test;
    static RemoteWebDriver driver = BaseGlue.driver;

    public  Log()
    {
        Log= Logger.getLogger(Log.getClass().getName());
    }

    public static void startTestCase(String testCaseName)
    {
        Log.info("Info : -------Starting of Testcase---------");

    }

    public static void stopTestCase(String testCaseName)
    {
        Log.info("Info : -------End of Testcase---------"+testCaseName+"-------");
    }

    public static void info(String message)
    {
        Log.info(message);
        try {
            if (message.contains("Pass : "))
                test.log(LogStatus.PASS, message);
            if (message.contains("Fail : ")) {
                test.log(LogStatus.FAIL, message);
            }
            if (message.contains("Info : "))
                test.log(LogStatus.INFO, message);
            if (message.contains("Error : ")) {
                test.log(LogStatus.ERROR,  message);
                throw new Exception(message);
            }
        } catch (NullPointerException e) {

        } catch (Exception e) {
            Log.info("Error : Log Info - "+message+" Error - "+e);
        }
    }

    public static void error(String message)
    {
        Log.error(message);
    }

    public static void fatal(String message)
    {
        Log.fatal(Log);
    }
    public static void debug(String message)
    {
        Log.debug(Log);
    }

    /**
     * startTime : This method is to returns start time in long
     * @return Start time
     */
    public static long startTime() {
        long x = System.currentTimeMillis();
        return(x);
    }

    /**
     * elapsedTime : This method is to returns time difference
     * @return Time difference
     */
    public static long elapsedTime(long startTime) {

        return ((System.currentTimeMillis() - startTime)/1000);

    }
}