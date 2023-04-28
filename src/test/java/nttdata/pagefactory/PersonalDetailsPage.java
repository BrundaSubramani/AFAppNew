package nttdata.pagefactory;

import nttdata.drivers.CustomWebDriver;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
public class PersonalDetailsPage extends PageObject {

    @FindBy(how= How.XPATH, using = "//*[@id=\"step2PersonalDetails\"]/div[2]/div[1]/div[1]/div[4]/input")
    public static WebElement mpn;

    @FindBy(how= How.XPATH, using = "//*[@id=\"j_id0:o2TemplateDSMB:j_id659:disableEmailtxt\"]")
    public static WebElement email;

    @FindBy(how= How.XPATH, using = "//*[@id=\"step2PersonalDetails\"]/div[2]/div[1]/div[1]/div[6]/input")
    public static WebElement confirmemail;

    @FindBy(how= How.XPATH, using = "//*[@id=\"j_id0:o2TemplateDSMB:j_id659:addrPostalCodeTemp\"]")
    public static WebElement postcode;

    @FindBy(how= How.XPATH, using = "//*[@id=\"j_id0:o2TemplateDSMB:j_id659:j_id778\"]")
    public static WebElement findaddress;

    @FindBy(how= How.XPATH, using = "//*[@id=\"step2hardwareLoanDetails\"]/input[1]")
    public static WebElement checkbox1;

    @FindBy(how= How.XPATH, using = "//*[@id=\"step2hardwareLoanDetails\"]/input[2]")
    public static WebElement checkbox2;

    @FindBy(how= How.XPATH, using = "//*[@id=\"j_id0:o2TemplateDSMB:j_id659:CCA-HLA:j_id915\"]/input")
    public static WebElement checkbox3;

    @FindBy(how= How.XPATH, using = "//*[@id=\"j_id0:o2TemplateDSMB:j_id659:CCA-HLA:makePaymentBtn\"]")
    public static WebElement doverificationcheck;

    @FindBy(how= How.XPATH, using = "//input[@id= 'CardHolderName']")
    public static WebElement nameoncard;

    @FindBy(how= How.XPATH, using = "//*[@id=\"CardNumber\"]")
    public static WebElement cardnumber;

    @FindBy(how= How.XPATH, using = "//*[@id=\"Month\"]")
    public static WebElement expirymonth;

    @FindBy(how= How.XPATH, using = "//*[@id=\"Year\"]")
    public static WebElement expiryyear;

    @FindBy(how= How.XPATH, using = "//*[@id=\"CardCvv\"]")
    public static WebElement cvv;

    @FindBy(how= How.XPATH, using = "//*[@id=\"btnPayNow\"]")
    public static WebElement conttonextstep;

    @FindBy(how= How.XPATH, using = "(//a[text()=\"Next\"])[3]")
    public static WebElement personaldetailsnext;

    public PersonalDetailsPage(CustomWebDriver customWebDriver) throws AWTException{
        super(customWebDriver);
    }

    public void personal_details() throws InterruptedException {
        Select title= new Select(driver.findElement(By.name("j_id0:o2TemplateDSMB:j_id659:title")));
        title.selectByValue("Mr");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Select day= new Select(driver.findElement(By.name("j_id0:o2TemplateDSMB:j_id659:birth_day")));
        day.selectByValue("24");
        Thread.sleep(2000);
        Select month= new Select(driver.findElement(By.name("j_id0:o2TemplateDSMB:j_id659:birth_month")));
        month.selectByValue("5");
        Thread.sleep(2000);
        Select year= new Select(driver.findElement(By.name("j_id0:o2TemplateDSMB:j_id659:birth_year")));
        year.selectByValue("1997");
        Thread.sleep(2000);
        mpn.sendKeys("07805080421");
        Thread.sleep(2000);
        email.sendKeys("fraudtest54321@yopmail.com");
        Thread.sleep(2000);
        confirmemail.sendKeys("fraudtest54321@yopmail.com");
        Thread.sleep(2000);
        postcode.sendKeys("SL12AA");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        findaddress.click();
        Thread.sleep(20000);
        Select address= new Select(driver.findElement(By.id("j_id0:o2TemplateDSMB:j_id659:addressListTemp")));
        address.selectByValue("8");
        Thread.sleep(20000);
        Select years= new Select(driver.findElement(By.id("j_id0:o2TemplateDSMB:j_id659:previousAddrYear")));
        years.selectByValue("7");
        Thread.sleep(4000);
        Select months= new Select(driver.findElement(By.id("j_id0:o2TemplateDSMB:j_id659:previousAddrMonth")));
        months.selectByValue("8");
        Thread.sleep(20000);
        checkbox1.click();
        Thread.sleep(40000);
        checkbox2.click();
        Thread.sleep(10000);
        checkbox3.click();
        Thread.sleep(10000);
        doverificationcheck.click();
        Thread.sleep(30000);
        WebElement ifram1=driver.findElement(By.xpath("//*[@id=\"theIframe\"]"));
        driver.switchTo().frame(ifram1);
        nameoncard.sendKeys("DEREK ACCEPT");
        Thread.sleep(1000);
        cardnumber.sendKeys("4000000000001091");
        Thread.sleep(1000);
        expirymonth.sendKeys("01");
        Thread.sleep(1000);
        expiryyear.sendKeys("23");
        Thread.sleep(1000);
        cvv.sendKeys("123");
        Thread.sleep(1000);
        conttonextstep.click();
        Thread.sleep(2000);
        //driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        //personaldetailsnext.click();
        driver.switchTo().defaultContent();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(personaldetailsnext));
        Thread.sleep(3000);
        personaldetailsnext.click();
        Thread.sleep(10000);

    }

}
