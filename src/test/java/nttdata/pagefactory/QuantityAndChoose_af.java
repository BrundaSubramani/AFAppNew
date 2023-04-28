package nttdata.pagefactory;

import nttdata.drivers.CustomWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
public class QuantityAndChoose_af extends PageObject{

    @FindBy(how= How.XPATH, using = "//*[text()='Choose']")
    public static WebElement choose;
    public QuantityAndChoose_af(CustomWebDriver customWebDriver) throws AWTException {
        super(customWebDriver);
    }
    public void clickOnChoose() throws InterruptedException {
        Thread.sleep(5000);
        Select quantity= new Select(driver.findElement(By.xpath("//label[.=\"Number of licenses\"]/../..//select")));
        quantity.selectByValue("2");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        choose.click();
    }

}
