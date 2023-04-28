package nttdata.pagefactory;

import nttdata.drivers.CustomWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class ConfirmOrder extends PageObject{

    @FindBy(how= How.XPATH, using = "//*[@id=\"j_id0:o2TemplateDSMB:j_id1538:saveCardDetailCreditCheck1\"]")
    public static WebElement confirmorder;

    @FindBy(how= How.XPATH, using = "//*[@id=\"j_id0:o2TemplateDSMB:j_id1538:gdprCheckbox3\"]")
    public static WebElement pleasecomfirm;

    @FindBy(how= How.XPATH, using = "//*[@id=\"j_id0:o2TemplateDSMB:j_id1538:completeOrderBtnSimOnly\"]")
    public static WebElement finalcomfirm;

    public ConfirmOrder(CustomWebDriver customWebDriver) throws AWTException {
        super(customWebDriver);
    }

    public void setConfirmorder() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(confirmorder));
        Thread.sleep(3000);
        confirmorder.click();
        Thread.sleep(10000);
//        WebDriverWait wait1 = new WebDriverWait(driver, 10);
        //wait1.until(ExpectedConditions.visibilityOf(pleasecomfirm));
        Thread.sleep(3000);
        pleasecomfirm.click();
        Thread.sleep(10000);
//        WebDriverWait wait2 = new WebDriverWait(driver, 10);
//        wait2.until(ExpectedConditions.visibilityOf(finalcomfirm));
        Thread.sleep(10000);
        finalcomfirm.click();
        Thread.sleep(10000);
    }

}
