/*
 * Added by Archana for Mobile Automation Testing
 */

package nttdata.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

@Component
public class Configuration {
    static Logger logger = LogManager.getLogger(Configuration.class);
    Properties properties;
    String automationTarget;


    @Autowired
    public Configuration(SystemDetails systemDetails) {
        read();
        //Setting the cucumber options need to work on below
        //SystemDetails.setCucumberOptions("features",getProperty("cucumber.options.features"));
        automationTarget=getProperty("automation.target");
        SystemDetails.setAutomationTarget(automationTarget);
        logger.info("PageFactory is configured for deviceType ["+automationTarget+"]");
    }

    public void read() {
        //default configuration
        read(Configuration.class.getResource("/config.properties").getFile());
    }

    public void read(String configPath) {
        properties = new Properties();
        InputStream input = null;
        try {
            logger.info("loading configuration: " + configPath);
            input = new FileInputStream(configPath);
            // load a properties file
            properties.load(input);
            //for(int i=0; i<properties.size();i++)
        } catch (Exception e) {
            logger.error("Unable to read config.properties " + e.toString());
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public Set<Object> getKeys(String keyContains) {
        Set<Object> keys = new HashSet<>();
        Iterator<Object> iterator = properties.keySet().iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            if (element.toString().contains(keyContains))
                keys.add(element);
        }
        return keys;
    }

    public void setProperty(String key, String value) {
        try {
            properties.setProperty(key, value);
            FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties", true);
            properties.store(fos, null);
            fos.close();
        } catch (Exception e) {
            logger.debug("cannot update the property file" + " - " + e.toString());
        }
    }


}
