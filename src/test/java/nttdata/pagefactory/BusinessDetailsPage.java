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
public class BusinessDetailsPage extends PageObject{

    @FindBy(how= How.XPATH, using = "//*[@id=\"j_id0:o2TemplateDSMB:j_id31:srMoveMobile:1\"]")
    public static WebElement omobileno;

    @FindBy(how= How.XPATH, using = "//*[@id=\"j_id0:o2TemplateDSMB:j_id31:accountNameInputtxt\"]")
    public static WebElement accountname;

    @FindBy(how= How.XPATH, using = "//*[@id=\"j_id0:o2TemplateDSMB:j_id31:sortCodeTb1txt\"]")
    public static WebElement sortcode1;

    @FindBy(how= How.XPATH, using = "//*[@id=\"j_id0:o2TemplateDSMB:j_id31:sortCodeTb2txt\"]")
    public static WebElement sortcode2;

    @FindBy(how= How.XPATH, using = "//*[@id=\"j_id0:o2TemplateDSMB:j_id31:sortCodeTb3txt\"]")
    public static WebElement sortcode3;

    @FindBy(how= How.XPATH, using = "//*[@id=\"j_id0:o2TemplateDSMB:j_id31:accountNumberInputtxt\"]")
    public static WebElement accountnumber;

    @FindBy(how= How.XPATH, using = "//*[@id=\"j_id0:o2TemplateDSMB:j_id31:txtcbAgreeDirectDebit\"]")
    public static WebElement checkbox;

    @FindBy(how= How.XPATH, using = "//*[@id=\"j_id0:o2TemplateDSMB:j_id31:checkYourDetails\"]")
    public static WebElement checkyourdetails;

    @FindBy(how= How.XPATH, using = "//*[@id=\"j_id0:o2TemplateDSMB:j_id31:proceedToPersonalDetail\"]")
    public static WebElement next;

    public BusinessDetailsPage(CustomWebDriver customWebDriver) throws AWTException{
        super(customWebDriver);
    }

    public void business_details() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        omobileno.click();
        Select companytype= new Select(driver.findElement(By.id("j_id0:o2TemplateDSMB:j_id31:businessType")));
        companytype.selectByValue("Business Professional");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        accountname.sendKeys("Lisa Taylor");
        Thread.sleep(2000);
        sortcode1.sendKeys("20");
        Thread.sleep(2000);
        sortcode2.sendKeys("93");
        Thread.sleep(2000);
        sortcode3.sendKeys("15");
        Thread.sleep(5000);
        accountnumber.sendKeys("93940101");
        Thread.sleep(2000);
        checkbox.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        checkyourdetails.click();
        Thread.sleep(20000);
        next.click();
    }


}
