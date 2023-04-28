package nttdata.pagefactory;

import nttdata.drivers.CustomWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class MonthlyPaymentDetails extends PageObject{

    @FindBy(how = How.XPATH, using = "//*[@id=\"j_id0:o2TemplateDSMB:j_id1337:j_id1446:j_id1447:gdprCheckbox9\"]")
    public static WebElement checkbox;

    @FindBy(how = How.XPATH, using = "//*[@id=\"j_id0:o2TemplateDSMB:j_id1337:hf2\"]")
    public static WebElement next;

    public MonthlyPaymentDetails(CustomWebDriver customWebDriver) throws AWTException{
        super(customWebDriver);
    }

    public void monthly_details() throws InterruptedException {
        checkbox.click();
        Thread.sleep(30000);
        next.click();
    }

}
