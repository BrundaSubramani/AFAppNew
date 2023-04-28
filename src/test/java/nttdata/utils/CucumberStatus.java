/*
 * Added by Archana for Mobile Automation Testing
 */

package nttdata.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class CucumberStatus {
    static Logger logger = LogManager.getLogger(CucumberStatus.class);
    public String scenarioName;
    public  String featureName;
    public String status;
    long executionTime;
    public long startTime;
    public long endTime;
    FileWriter writer;
    BufferedWriter bufferedWriter;
    String reportFilePath = "logs\\customCusumberReport.txt";

    public CucumberStatus() {
        try {
            createFile();
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }
    
    public CucumberStatus(String path) {
        try {
            reportFilePath = path;
            createFile();
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    public void createFile() {
        try {
            if(SystemDetails.getOsName().toLowerCase().contains("mac"))
                reportFilePath=reportFilePath.replace('\\','/');
            File f = new File(reportFilePath);
            if (f.exists()) {
                f.delete();
            }
            writer = new FileWriter(reportFilePath, true);
            bufferedWriter = new BufferedWriter(writer);
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    public void writeStatusToFile(String prefix) {
        try {
            executionTime = endTime - startTime;
            bufferedWriter.append(Utils.convertDateTime(startTime,"yyyy-MM-dd HH-mm-ss") +"\t[ " + status+" ]:: "+prefix +" ::"+scenarioName +"\t[ Duration: "+executionTime +" ] ::"+featureName);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

}
