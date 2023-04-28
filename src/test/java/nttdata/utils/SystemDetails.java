/*
 * Added by Archana for Mobile Automation Testing
 */

package nttdata.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Properties;


@Component
public class SystemDetails {
    static Logger logger = LogManager.getLogger(SystemDetails.class);
    Properties properties = new Properties();
    public static String osName, osVersion,automationTarget,lastLoggedUser="";



    boolean fingerPrint = false;
    boolean phoneBook = false;
    private static boolean msisdnReadRequired=true;
    private static  int loginCount=1; //assuming the device already logged in.
    private static  int retryCount=0;

    public static int getLoginCount() {
        return loginCount;
    }
    public static void resetLoginCount() {
        SystemDetails.loginCount = 0; msisdnReadRequired=true;
    }
    public static void pinLoginSuccess() {
        SystemDetails.loginCount++; msisdnReadRequired=false;
    }
    public static void incrementRetry() {
        SystemDetails.retryCount++;
    }
    public static void resetRetryCount() {
        SystemDetails.retryCount = 0;
    }
    public static int getRetryCount() {
        return retryCount;
    }

    public SystemDetails() {
        osName = System.getProperty("os.name");
        osVersion = System.getProperty("os.version");
        logger.info("detected OS: ["+osName+" , "+osVersion+"]");
    }

    public static String getOsName() {
        return osName;
    }

    public static String getAutomationTarget() {
        return automationTarget;
    }

    public static void setAutomationTarget(String automationTarget) {
        SystemDetails.automationTarget = automationTarget;
    }

    //Setting up the cucumber options
    public static void setCucumberOptions(String option, String value) {
        if (osName.toLowerCase().contains("mac"))
            value = value.replace('\\', '/');
        System.setProperty("cucumber.options","--"+option+" "+value);
        logger.debug("Setting cucumber.option."+option+" ["+value+"]");
    }

    public static boolean isMsisdnReadRequired() {
        return msisdnReadRequired;
    }

    public static void setMsisdnReadRequired(boolean msisdnReadRequired) {
        SystemDetails.msisdnReadRequired = msisdnReadRequired;
    }
}
