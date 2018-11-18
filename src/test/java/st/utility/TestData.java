package st.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;

import st.glue.BaseGlue;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TestData {

    public static HashMap<String, String> testDataHM = new HashMap<String, String>();

    public static void getSuites() {
        Set<String> keys = testDataHM.keySet();
        // To get all the key values
        for(String key: keys){
            Log.info("Info : Read test data - "+key+" : "+testDataHM.get(key));
        }

    }

    public static void readSuite(HSSFWorkbook workbook, String sheetName) {
        try {

            HSSFSheet sheet=workbook.getSheet(sheetName);
            int totalRows = sheet.getLastRowNum();
            Log.info("Info : Sheet - "+sheetName+"; Total testcases - "+(totalRows));
            for (int i=0; i < 1; i++) {
                HSSFRow row = sheet.getRow(i);
                int totalCol=row.getLastCellNum();
                for (int j=0; j<totalCol; j++) {
                    if (row.getCell(j).getStringCellValue()!=null)
                        testDataHM.put("title_"+j, row.getCell(j).getStringCellValue());
                }
            }

            for (int i=1; i <= totalRows; i++) {
                HSSFRow row = sheet.getRow(i);

                int totalCol=row.getLastCellNum();

                String testCaseId=row.getCell(0).getStringCellValue();
                String testCase=row.getCell(1).getStringCellValue();
                String moduleName=row.getCell(2).getStringCellValue();
                String isTestCaseEnabled=row.getCell(3).getStringCellValue();

                if (isTestCaseEnabled.equals("Yes") && testCaseId.equalsIgnoreCase(BaseGlue.testcaseId)) {
                    testDataHM.put(testCaseId+".Enabled",isTestCaseEnabled);
                    testDataHM.put(testCaseId+".TestCaseId",testCaseId);
                    testDataHM.put(testCaseId+".TestCase",testCase);

                    Log.info("Info : Testcase ID Enabled - "+row.getCell(0).getStringCellValue());
                    int dataCount=0;
                    for (int j=4; j < totalCol; j++) {
                        dataCount++;
                        if (row.getCell(j).getStringCellValue()!=null)
                            testDataHM.put(testCaseId+"."+testDataHM.get("title_"+j), row.getCell(j).getStringCellValue());
                    }
                }
            }
        } catch (Exception e) {
            Log.info("Error : Read test data sheets - "+e);
        }
    }

    public static HashMap<String, String> testData() throws Exception {
        try {
            FileInputStream fis = new FileInputStream(new File(System.getProperty("testDataFilePath")));
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            HSSFSheet sheet = workbook.getSheetAt(0);

            int totalRows = sheet.getLastRowNum();
            for (int i = 1; i <= totalRows; i++) {
                HSSFRow row = sheet.getRow(i);
                String SuiteName=row.getCell(0).getStringCellValue();
                String isSuiteEnabled=row.getCell(1).getStringCellValue();
                if (SuiteName!=null && isSuiteEnabled!=null)
                    testDataHM.put(SuiteName, isSuiteEnabled);
                if (isSuiteEnabled.equals("Yes")) {
                    Log.info("Info : Sheet Enabled - "+SuiteName);
                    readSuite(workbook, SuiteName);
                }
            }
//			getSuites();
            return testDataHM;
        } catch (Exception e) {
            Log.info("Error : Test data - "+e);
            throw new Exception("Error : "+e);
        }
    }

}
