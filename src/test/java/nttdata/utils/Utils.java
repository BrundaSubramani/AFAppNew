/*
 * Added by Archana for Mobile Automation Testing
 */

package nttdata.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class Utils {
    static Logger logger = LogManager.getLogger(Utils.class);
    final static long longNowDateTime=getLongDateTime();
    static String dateFormatString="yyyy-MM-dd";
    public Utils(){
    }
    //get current DateTime as long
    public static long getLongDateTime(){
        Date referenceDateTime = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(referenceDateTime);
        long longReferenceDateTime = referenceDateTime.getTime();
        return longReferenceDateTime;
    }
    //convert the long date time to specified dataTime format
    public static String convertDateTime(long longDateTime, String strDateTimeFormat){
        DateFormat dateFormat = new SimpleDateFormat(strDateTimeFormat);
        return dateFormat.format(convertDateTime(longDateTime));
    }
    //Convert longDate to Date
    public static Date convertDateTime(long longDateTime){
        try {
            return new Date(longDateTime);
        }catch(Exception e){
            return new Date();
        }
    }

    public static Properties readConfigProperties(String configPath){
        Properties properitis = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(configPath);
            // load a properties file
            properitis.load(input);
            return properitis;
        }catch (Exception e){
            logger.error("Unable to read config.properties "+ e.toString());
            return null;
        }

    }
    public static String getTimeWrtNow(String timeWrtNow){
        long longOffset = 0;
        long longDateTime = 0;
        String[] splitStringWords = timeWrtNow.split("[\\s\\W]+");
        String[] splitStringOprator = timeWrtNow.split("\\w+");
        if (splitStringWords[1].equalsIgnoreCase("TODAY")) {
            longDateTime = longNowDateTime;
            if(splitStringWords.length>=4){
                if (splitStringWords[3].equalsIgnoreCase("MINUTES"))
                    longOffset = Long.parseLong(splitStringWords[2]) * 60000;
                if (splitStringWords[3].equalsIgnoreCase("HOURS"))
                    longOffset = Long.parseLong(splitStringWords[2]) * 3600000;
                if (splitStringWords[3].equalsIgnoreCase("DAYS"))
                    longOffset = Long.parseLong(splitStringWords[2]) * 86400000;
            }
        }
        if(splitStringOprator.length>=2){
            //Add the Offset
            if(splitStringOprator[1].equalsIgnoreCase("+")){
                longDateTime= longNowDateTime+longOffset;
            }
            //Subtract Offset from the time
            if(splitStringOprator[1].equalsIgnoreCase("-")){
                longDateTime= longNowDateTime-longOffset;
            }
        }
        return convertDateTime(longDateTime,dateFormatString); //"yyyy-MM-dd"
    }

}
