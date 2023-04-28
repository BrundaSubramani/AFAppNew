package nttdata.code.stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import nttdata.drivers.CustomWebDriver;
import nttdata.pagefactory.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class AfAppSteps {

    static Logger logger = LogManager.getLogger(AfAppSteps.class);

    public WebDriver driver;

    @Autowired
    LoginPage Loginpage;
    private WebElement element;

    public AfAppSteps(CustomWebDriver customWebDriver){this.driver = customWebDriver.getWebDriver();}


    @Given("Sales representative lands on salesforce page")
    public void salesRepresentativeLandsOnSalesforcePage() {
        try {
            Loginpage.login();
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    /*@When("Sales representative filled up UserName {string} and password {string} to login")
    public void salesRepresentativeFilledUpUserNameAndPasswordToLogin(String arg0, String arg1) {
    }*/

    /*@And("Sales representative searches respective DMU profile in Global search")
    public void salesRepresentativeSearchesRespectiveDMUProfileInGlobalSearch() {
    }*/

    @Autowired
    loginProfile Loginprofile;

    @And("Sales representative logs in to the DMU profile")
    public void salesRepresentativeLogsInToTheDMUProfile() {
        try {
            Loginprofile.login_profile();
        } catch (Exception e) {
            logger.error(e.toString());
        }

    }

    @Autowired
    LeadPage leadPage;

    @And("Sales representative creates a new lead in object lead")
    public void salesRepresentativeCreatesANewLeadInObjectLead() {
        leadPage.lead_page();
    }

    @And("Sales representative converts the lead into account by clicking on convert")
    public void salesRepresentativeConvertsTheLeadIntoAccountByClickingOnConvert() {
        driver.findElement(By.xpath("//*[@id=\"topButtonRow\"]/input[4]")).click();
    }

    @Autowired
    ConvertPage convertPage;

    @And("Sales representative enter the account name subject and clicks on convert")
    public void salesRepresentativeEnterTheAccountNameSubjectAndClicksOnConvert() {
        convertPage.convert_page();

    }

    @And("Sales representative proceeds to place and order by clicking order button")
    public void salesRepresentativeProceedsToPlaceAndOrderByClickingOrderButton() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"topButtonRow\"]/input[11]")).click();
    }

    @Autowired
    SelectApp_AF selectApp_af;

    @And("Sales representative lands on business shop to select app purchases")
    public void salesRepresentativeLandsOnBusinessShopToSelectAppPurchases() throws InterruptedException {
        Thread.sleep(40000);
        String parent=driver.getWindowHandle();
        Set<String> s=driver.getWindowHandles();
        Iterator<String> I1= s.iterator();
        while(I1.hasNext())
        {
            String child_window=I1.next();
            if(!parent.equals(child_window))
                driver.switchTo().window(child_window);}
        driver.manage().window().maximize();
        selectApp_af.clickApp();
    }

    @Autowired
    QuantityAndChoose_af quantityAndChoose_af;

    @And("Sales representative lands application page and chooses apps available")
    public void salesRepresentativeLandsApplicationPageAndChoosesAppsAvailable() {
        try {
            quantityAndChoose_af.clickOnChoose();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Autowired
    ProceedToOrder proceedToOrder;

    @And("Sales representative performs purchased app configurations and proceeds to place an order")
    public void salesRepresentativePerformsPurchasedAppConfigurationsAndProceedsToPlaceAnOrder() {
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        proceedToOrder.clickproceedToOrder();
    }

    @Autowired
    BusinessDetailsPage businessDetailsPage;

    @And("Sales representative enters the business details on transactional checkout page")
    public void salesRepresentativeEntersTheBusinessDetailsOnTransactionalCheckoutPage() throws InterruptedException {
        businessDetailsPage.business_details();
    }

    @Autowired
    PersonalDetailsPage personalDetailsPage;

    @And("Sales representative enters the personal details on transactional checkout page")
    public void salesRepresentativeEntersThePersonalDetailsOnTransactionalCheckoutPage() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        personalDetailsPage.personal_details();
    }

    @Autowired
    DeliveryDetailsPage deliveryDetailsPage;

    @And("Sales representative enters the delivery details on transactional checkout page")
    public void salesRepresentativeEntersTheDeliveryDetailsOnTransactionalCheckoutPage() throws InterruptedException {
        /*Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        /*WebElement next= driver.findElement(By.xpath("//*[@id=\"j_id0:o2TemplateDSMB:j_id1248:j_id1333\"]"));
        next.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("//*[@id=\"j_id0:o2TemplateDSMB:j_id1248:j_id1333\"]")));
        */
        deliveryDetailsPage.setDeliverynextbutton();

    }

    @Autowired
    MonthlyPaymentDetails monthlyPaymentDetails;

    @And("Sales representative enters the monthly payment details on transactional checkout page")
    public void salesRepresentativeEntersTheMonthlyPaymentDetailsOnTransactionalCheckoutPage() throws InterruptedException {
        monthlyPaymentDetails.monthly_details();
    }

    @Autowired
    ConfirmOrder confirmOrder;

    @And("Sales representative enters the upfront payment details on transactional checkout page")
    public void salesRepresentativeEntersTheUpfrontPaymentDetailsOnTransactionalCheckoutPage() throws InterruptedException {

        confirmOrder.setConfirmorder();
        //driver.findElement((By.xpath("//*[@id=\"j_id0:o2TemplateDSMB:j_id1538:saveCardDetailCreditCheck1\"]"))).click();
    }

//    @Then("Sales representative confirms the purchase order is generated")
//    public void salesRepresentativeConfirmsThePurchaseOrderIsGenerated() {
//        driver.findElement((By.xpath("//*[@id=\"j_id0:o2TemplateDSMB:j_id1538:saveCardDetailCreditCheck12\"]"))).click();
//    }
}
