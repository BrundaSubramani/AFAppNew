package nttdata.pagefactory;

import nttdata.drivers.CustomWebDriver;
import nttdata.utils.Configuration;
import nttdata.utils.Excel;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Reporter;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Component
public class LoginPage extends PageObject {

    @FindBy(id = "username")
    public WebElement inputUserName;
    @FindBy(id = "password")
    public WebElement inputPassword;
    @FindBy(id = "Login")
    public WebElement buttonSubmit;
    @FindAll({@FindBy(id = "main_center_div")})
    public List<WebElement> msgSessionAlreadyOpen;
    @FindBy(how= How.XPATH, using = "//*[@class='profileTrigger branding-user-profile bgimg slds-avatar slds-avatar_profile-image-small circular forceEntityIcon']")
    public static WebElement usericon;
    @FindBy(how= How.XPATH, using = "//*[@class='profile-link-label switch-to-aloha uiOutputURL']")
    public static  WebElement switchsales;
    @FindBy(how= How.XPATH, using = "//*[@id='phSearchInput']")
    public static  WebElement SalesRep;
    @FindBy(how= How.XPATH, using = "//*[@id='phSearchButton']")
    public static  WebElement search;
    @FindBy(how= How.XPATH, using = "(//a[text()='Sales Representative CPQ .E2E- Private User'])[1]")
    public static  WebElement Corporate_sales;
    @FindBy(how= How.XPATH, using = "//a[text()='Sales Rep PublicE2E']")
    public static  WebElement PublicE2E;
    @FindBy(how= How.XPATH, using = "(//a[text()='Sales Rep PrivateE2E'])[1]")
    public static  WebElement Corporate_sales1;
    @FindBy(how= How.XPATH, using = "//*[@id='moderatorMutton']")
    public static  WebElement dropdown;
    @FindBy(how= How.XPATH, using = "//*[@id='USER_DETAIL']")
    public static  WebElement userdetail;
    @FindBy(how= How.XPATH, using = "//*[@id='topButtonRow']/input[4]")
    public static  WebElement loginuser;
    @FindBy(how= How.XPATH, using = "//a[text()='Corporate Senior Admin Support.E2E']")
    public static  WebElement Corporate_sales_Pricing;
    @FindBy(how= How.XPATH, using = "//a[text()='Sales rep 2 CPQ']")
    public static  WebElement Corporate_sales_SIT_One;


    @Autowired
    private Configuration configuration;
    @Autowired
    Excel excel;
    @Autowired
    public LoginPage(CustomWebDriver customWebDriver) throws AWTException {
        super(customWebDriver);
    }

     public void login() throws InterruptedException {
        try {
            String appUrl = configuration.getProperty("salesforceBaseUrl");
            driver.get(appUrl);
            takeScreenShot("loginPage");
            driver.manage().window().maximize();
          //  excel.writeExcel(0,0,"dealType");

            webDriverWait.until(ExpectedConditions.elementToBeClickable(inputUserName));
        } catch (UnhandledAlertException | IOException e) {
            logger.debug("error navigating...." + e.toString());

        }
        logger.debug("using login details form configuration file...");

        String userName = configuration.getProperty("User_Name");
        String password = configuration.getProperty("Pass_code");
        //Base64.Decoder decoder = Base64.getDecoder();
        //password= new String(decoder.decode(password));
        logger.debug(password);
        logger.debug("login to saleforce ....");
        sendKeys(inputUserName, userName);
        sendKeys(inputPassword, password);
        clickElement(buttonSubmit);
        Thread.sleep(5000);
    }

//    public void profileLogin(String tos) throws InterruptedException
//    {
//        try
//        {
//            webDriverWait.until(ExpectedConditions.visibilityOf(SalesRep));
//            Thread.sleep(5000);
//            webDriverWait.until(ExpectedConditions.elementToBeClickable(SalesRep));
//            Thread.sleep(2000);
//            clickElement(SalesRep);
//        }
//        catch (Exception e)
//        {
//            webDriverWait.until(ExpectedConditions.elementToBeClickable(usericon));
//            clickElement(usericon);
//            webDriverWait.until(ExpectedConditions.elementToBeClickable(switchsales));
//            clickElement(switchsales);
//            webDriverWait.until(ExpectedConditions.visibilityOf(SalesRep));
//        }
//        if("Pricing Desk".equals(tos))
//        {
//            sendKeys(SalesRep,"Corporate Senior Admin Support.E2E");
//            clickElement(search);
//            webDriverWait.until(ExpectedConditions.elementToBeClickable(Corporate_sales_Pricing));
//            clickElement(Corporate_sales_Pricing);
//        }
//        else
//        {
//            sendKeys(SalesRep,"Sales Representative CPQ .E2E- Private User");
//            clickElement(search);
//            webDriverWait.until(ExpectedConditions.elementToBeClickable(Corporate_sales));
//            clickElement(Corporate_sales);
//        }
//        webDriverWait.until(ExpectedConditions.visibilityOf(dropdown));
//        Thread.sleep(2000);
//        clickElement(dropdown);
//        clickElement(userdetail);
//        webDriverWait.until(ExpectedConditions.elementToBeClickable(loginuser));
//        clickElement(loginuser);
//        logger.debug("Logged in as Sales Rep");
//
//    }
//
//    public void login(String userName, String password) throws Throwable {
//        logger.debug("login to saleforce ....");
//        sendKeys(inputUserName, userName);
//        sendKeys(inputPassword, password);
//        clickElement(buttonSubmit);
//        Thread.sleep(5000);
//    }
//    public void profileLoginSIT(String tos) throws InterruptedException
//    {
//        try
//        {
//
//            clickElement(SalesRep);
//        }
//        catch (Exception e)
//        {
//            webDriverWait.until(ExpectedConditions.elementToBeClickable(usericon));
//            clickElement(usericon);
//            webDriverWait.until(ExpectedConditions.elementToBeClickable(switchsales));
//            clickElement(switchsales);
//            webDriverWait.until(ExpectedConditions.visibilityOf(SalesRep));
//        }
//            sendKeys(SalesRep,"Sales rep 2 CPQ");
//            clickElement(search);
//            webDriverWait.until(ExpectedConditions.elementToBeClickable(Corporate_sales_SIT_One));
//            clickElement(Corporate_sales_SIT_One);
//
//
//        webDriverWait.until(ExpectedConditions.visibilityOf(dropdown));
//        Thread.sleep(2000);
//        clickElement(dropdown);
//        clickElement(userdetail);
//        webDriverWait.until(ExpectedConditions.elementToBeClickable(loginuser));
//        clickElement(loginuser);
//        logger.debug("Logged in as Sales Rep");
//
//    }
//    public void profileLoginGPT(String tos) throws InterruptedException
//    {
//        try
//        {
//
//            clickElement(SalesRep);
//        }
//        catch (Exception e)
//        {
//            webDriverWait.until(ExpectedConditions.elementToBeClickable(usericon));
//            clickElement(usericon);
//            webDriverWait.until(ExpectedConditions.elementToBeClickable(switchsales));
//            clickElement(switchsales);
//            webDriverWait.until(ExpectedConditions.visibilityOf(SalesRep));
//        }
//
//            sendKeys(SalesRep,"Sales Rep PrivateE2E");
//            clickElement(search);
//            webDriverWait.until(ExpectedConditions.elementToBeClickable(Corporate_sales1));
//            clickElement(Corporate_sales1);
//
//        webDriverWait.until(ExpectedConditions.visibilityOf(dropdown));
//        Thread.sleep(2000);
//        clickElement(dropdown);
//        clickElement(userdetail);
//        webDriverWait.until(ExpectedConditions.elementToBeClickable(loginuser));
//        clickElement(loginuser);
//        logger.debug("Logged in as Sales Rep");
//
//    }
//

//    public void profileLogin(String tos, String acc) throws InterruptedException
//    {
//        try
//        {
//            Reporter.log("tos=          "+tos,true);
//            Reporter.log("Account=          "+acc,true);
//            //clickElement(SalesRep);
//            webDriverWait.until(ExpectedConditions.visibilityOf(SalesRep));
//            Thread.sleep(2000);
//            webDriverWait.until(ExpectedConditions.elementToBeClickable(SalesRep));
//            Thread.sleep(2000);
//            clickElement(SalesRep);
//        }
//        catch (Exception e)
//        {
//            webDriverWait.until(ExpectedConditions.elementToBeClickable(usericon));
//            clickElement(usericon);
//            webDriverWait.until(ExpectedConditions.elementToBeClickable(switchsales));
//            clickElement(switchsales);
//            webDriverWait.until(ExpectedConditions.visibilityOf(SalesRep));
//        }
//        if("Pricing Desk".equals(tos))
//        {
//            sendKeys(SalesRep,"Corporate Senior Admin Support.E2E");
//            clickElement(search);
//            webDriverWait.until(ExpectedConditions.elementToBeClickable(Corporate_sales_Pricing));
//            clickElement(Corporate_sales_Pricing);
//        }
//        else if("Test_Public".equals(acc))
//        {
//            sendKeys(SalesRep,"Sales Rep PublicE2E");
//            clickElement(search);
//            webDriverWait.until(ExpectedConditions.elementToBeClickable(PublicE2E));
//            clickElement(PublicE2E);
//        }
//        else if("1000+ Sites".equals(acc))
//        {
//            sendKeys(SalesRep,"Sales Rep PublicE2E");
//            clickElement(search);
//            webDriverWait.until(ExpectedConditions.elementToBeClickable(PublicE2E));
//            clickElement(PublicE2E);
//        }
//        else
//        {
//            sendKeys(SalesRep,"Sales Representative CPQ .E2E- Private User");
//            clickElement(search);
//            webDriverWait.until(ExpectedConditions.elementToBeClickable(Corporate_sales));
//            clickElement(Corporate_sales);
//        }
//        webDriverWait.until(ExpectedConditions.visibilityOf(dropdown));
//        Thread.sleep(2000);
//        clickElement(dropdown);
//        clickElement(userdetail);
//        webDriverWait.until(ExpectedConditions.elementToBeClickable(loginuser));
//        clickElement(loginuser);
//        logger.debug("Logged in as Sales Rep");
//
//    }

}
