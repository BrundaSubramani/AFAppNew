package nttdata.pagefactory;

import nttdata.drivers.CustomWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static nttdata.pagefactory.QuantityAndChoose_af.choose;

@Component
public class ProceedToOrder extends PageObject{

    @FindBy(how= How.XPATH, using = "(//*[text()='Proceed to order'])[2]")
    public static WebElement PTOClick;

    public ProceedToOrder(CustomWebDriver customWebDriver) throws AWTException {
        super(customWebDriver);
    }

    public void clickproceedToOrder(){
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("window.scrollBy(0,600)", "");
        // PTOClick.click();
        ((JavascriptExecutor)driver).executeScript("arguments[0].click()",PTOClick);
    }
}
