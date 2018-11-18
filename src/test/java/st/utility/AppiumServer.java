package st.utility;

import st.glue.BaseGlue;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppiumServer {

    public static boolean startServer() {
        boolean isTrue = false;
        try {
            if (BaseGlue.appiumManualStart.equalsIgnoreCase("No")) {

                // Kill any appium server running before start
                Runtime.getRuntime().exec("/usr/bin/killall -KILL node");
                Thread.sleep(2000);

                String appiumLog = System.getProperty("appiumLog").equalsIgnoreCase("yes") ? " --log Logs/Appium_Server_" + BaseGlue.timestampToAppend + ".log" : "";
                // Start appium server
                executeCommand("/usr/local/bin/appium"+appiumLog);
                Thread.sleep(10000);

                // Verify Appium Server Started...
                File file = new File("Logs/Appium_Server_" + BaseGlue.timestampToAppend + ".log");
                BufferedReader br = new BufferedReader(new FileReader(file));
                String st;
                while ((st = br.readLine()) != null) {
                    if (st.contains("Welcome to Appium v")) {
                        isTrue=true;
                        break;
                    }
                }
                String output = isTrue ? "Pass" : "Fail";
                Log.info(output+" : Appium server started...");
            } else {
                Log.info("Info : Note - Make sure Appium server started manually as configured");
                isTrue=true;
            }
        } catch (IOException e) {
            Log.info("Error IO : Please check the appium server log exists");
        } catch (Exception e) {
            Log.info("Error : Appium Start server - "+e);
        }
        return isTrue;
    }

    public static void stopServer() {
        try {
            Runtime.getRuntime().exec("/usr/bin/killall -KILL node");
            Log.info("Info : Appium server stopped...");
        } catch (Exception e) {
            Log.info("Error : Appium server stopoped - "+e);
        }
    }

    public static boolean executeCommand(String command) {
        boolean isTrue=false;
        try {
            Runtime run  = Runtime.getRuntime();
            Process proc = run.exec( command );
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));

            Log.info("Info : Command executed successfully - "+command);
        } catch (Exception e) {
            Log.info("Error : Execute command - "+e);
        }
        return isTrue;
    }
}

