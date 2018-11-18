package st.wrappers;

import st.glue.BaseGlue;
import st.utility.Log;

public class TestDataValue {
    public static String loginID;
    public static String loginPassword;

    public static String testcaseEnabled;

    public static void unsetTestData() {
        loginID = "";
        loginPassword = "";
        Log.info("Info : Unset test data");
    }

    public static boolean setTestData(String testcaseId) {
        boolean isTrue = false;
        try {
            unsetTestData();
            Log.info("Info : Test data for test case id - " + testcaseId);
            testcaseEnabled = BaseGlue.testDataHM.get(testcaseId + ".Enabled");
            Log.info("Info : Testcase Enabled - " + testcaseEnabled);
            if (testcaseEnabled.equalsIgnoreCase("Yes")) {
                    loginID = BaseGlue.testDataHM.get(testcaseId + ".loginID");
                    loginPassword = BaseGlue.testDataHM.get(testcaseId + ".loginPassword");
                }

            Log.info("Info : Testdata >> " + testcaseId + " >> Login user name- " + loginID);
            Log.info("Info : Testdata >> " + testcaseId + " >> Login Password - " + loginPassword);

            isTrue = true;

        } catch (Exception e) {
            Log.info("Error : Set test data - " + e);
        }
        return isTrue;
    }
}

