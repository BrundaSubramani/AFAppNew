package nttdata.pagefactory;

import nttdata.drivers.CustomWebDriver;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class DeliveryDetailsPage extends PageObject{

    @FindBy(how= How.XPATH, using = "//*[@id=\"j_id0:o2TemplateDSMB:j_id1248:j_id1333\"]")
    public static WebElement deliverynextbutton;

    public DeliveryDetailsPage(CustomWebDriver customWebDriver) throws AWTException {
        super(customWebDriver);
    }

    public void setDeliverynextbutton() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(deliverynextbutton));
        Thread.sleep(3000);
        deliverynextbutton.click();
        Thread.sleep(10000);
    }


}

