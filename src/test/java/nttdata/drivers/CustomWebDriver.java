package nttdata.drivers;

import nttdata.utils.Configuration;
import nttdata.utils.SystemDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

@Component
public class CustomWebDriver {
    static {
        System.setProperty("java.awt.headless", "false");
        System.out.println(java.awt.GraphicsEnvironment.isHeadless());
    }
    private WebDriver driver;
    static Logger logger = LogManager.getLogger(CustomWebDriver.class);

    @Autowired
    public CustomWebDriver(Configuration configuration) {
        try {
            //Properties config;
            String driverType;
            String driverPath;
            //configuration = Utils.readConfigProperties(CustomWebDriver.class.getResource("/config.properties").getFile());
            driverType = configuration.getProperty("driver");
            if (driverType.equalsIgnoreCase("firefox")) {
                driverPath = configuration.getProperty(driverType + ".path");
                System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
                driver = new FirefoxDriver();
            }
            if (driverType.equalsIgnoreCase("chrome")) {
                driverPath = configuration.getProperty(driverType + ".path");
                if(SystemDetails.getOsName().toLowerCase().contains("mac")) //on MAC
                    System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver");
                else //otherwise....windows
                    System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
               // options.addArguments("--proxy-server=http://localhost:3128");

                driver = new ChromeDriver();
                //driver.manage().window().maximize();
            }
            if (driverType.equalsIgnoreCase("internetExplorer")) {
                driverPath = configuration.getProperty(driverType + ".path");
                System.setProperty("webdriver.ie.driver", driverPath + "IEDriverServer.exe");
                driver = new InternetExplorerDriver();
            }
            driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    public WebDriver getWebDriver() {
        return this.driver;
    }

    @PreDestroy
    public void destory() {

        //driver.quit();
    }

}
