package nttdata.pagefactory;

import nttdata.drivers.CustomWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class SelectApp_AF extends PageObject {

    public SelectApp_AF(CustomWebDriver customWebDriver) throws AWTException {
        super(customWebDriver);
    }
    public void clickApp() {

        Actions action=new Actions(driver);
        WebElement Bshop=driver.findElement(By.xpath("//*[text()='Business shop']"));
        action.moveToElement(Bshop).build().perform();
        WebElement apps=driver.findElement(By.xpath("//*[text()='Apps']"));
        action.moveToElement(apps).build().perform();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement mcaffe=driver.findElement(By.xpath("(//*[text()='McAfee Multi Access'])[1]"));
        // action.moveToElement(mcaffe).build().perform();
        mcaffe.click();
    }

}
