/*
 * Added by Archana for Mobile Automation Testing
 */

package nttdata.pagefactory;

import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import nttdata.drivers.CustomWebDriver;
import nttdata.utils.Configuration;
import nttdata.utils.SystemDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.plexus.util.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Boolean.TRUE;
import static java.lang.Thread.sleep;

public abstract class PageObject {
    static Logger logger = LogManager.getLogger(PageObject.class);
    public static WebDriver driver;
    protected static WebDriverWait webDriverWait;
    public String automationTarget;
    private static String currentPin;
    private static String alternatePin;
    private static boolean pinInitRequired=TRUE;
    Robot robot = new Robot();


    @Autowired
    private Configuration configuration;
    @Autowired
    private CustomWebDriver customWebDriver;

    @Autowired
    public PageObject(CustomWebDriver customWebDriver) throws AWTException {
        driver = customWebDriver.getWebDriver();
        webDriverWait = new WebDriverWait(driver, 30);
        PageFactory.initElements(new AppiumFieldDecorator(driver, 20, TimeUnit.SECONDS), this);
        automationTarget = SystemDetails.automationTarget;
    }

    public static void clickElement(WebElement webElement) {
        try {
            logger.debug("clicking on " + webElement.getText());
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            Thread.sleep(1000);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
            Thread.sleep(1000);
            webElement.click();

        } catch (Exception e) {
            logger.error(e.toString());
            Assert.fail("Element " + webElement.getText() + " is not present");
        }
    }

    public static void sendKeys(WebElement webElement, String keys) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            //Thread.sleep(1000);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
            //Thread.sleep(1000);
            webElement.click();
           // Thread.sleep(4000);
            //webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            //Thread.sleep(2000);
           // webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.sendKeys(keys);
                    }
        catch (Exception e) {
            logger.error(e.toString());
            Assert.fail("unable to enter text " + keys);
        }
    }

    public static void sendEnter(WebElement webElement, String keys) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            webElement.click();
            webElement.sendKeys(keys);
            webElement.sendKeys(Keys.ENTER);
            Thread.sleep(4000);
        } catch (Exception e) {
            logger.error(e.toString());
            Assert.fail("unable to enter text " + keys);
        }
    }

    public void clickOn(WebElement element) {
        try {
            logger.debug("Clicking on " + element.getText());
            waitFor(element);
            element.click();
        } catch (Exception e) {
            Assert.fail("Element is not present");
        }
    }

    public void waitFor(WebElement element) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForMobileElement(MobileElement element) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }


    public void scroll(WebElement element) throws InterruptedException {
        waitFor(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(1000);
    }

    //refined by Pankaj
    public void scrollTo(String parentResourceId, String elementWithClass, String elementWithText){
        logger.debug("scrolling to " + elementWithText);
        String scrollableList="uk.co.o2.android.myo2.enterprise:id/fragment_container_main_activity";
        String className="android.view.View";
        MobileElement element = driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().resourceId(\""+parentResourceId+"\")).getChildByText("
                        + "new UiSelector().className(\""+elementWithClass+"\"), \""+elementWithText+"\")"));

        //Perform the action on the element
        //element.click();
    }

    public void scrollToElement(String visibleText) {
        logger.debug("scrolling to " + visibleText);
        try {
            if (SystemDetails.getAutomationTarget().equalsIgnoreCase("ANDROID")) {
                ((AndroidDriver) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + visibleText + "\").instance(0))");
                //((AndroidDriver) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().textContains("+visibleText+")).scrollIntoView(" + visibleText +")");
            } else {
                MobileElement searchElement = driver.findElement(By.name(visibleText));
                logger.debug("element with text ["+visibleText+"] visible? " + searchElement.isDisplayed());
                int i =0; //temporary loop exit
                while(!searchElement.isDisplayed() && i < 6) {
                    scrollBySwipe_BottomUp();
                    logger.debug("element with text ["+visibleText+"] visible?" + searchElement.isDisplayed());
                    i++;
                    Thread.sleep(1000);
                }
                /*
                RemoteWebElement parent = (RemoteWebElement) driver.findElement(By.name(visibleText));
                //RemoteWebElement parent = (RemoteWebElement)driver.findElement(By.className("XCUIElementTypeTable"));
                String parentID = parent.getId();
                HashMap<String, String> scrollObject = new HashMap<String, String>();
                scrollObject.put("element", parentID);
                //scrollObject.put("toVisible", visibleText);
                scrollObject.put("direction", "down");
                scrollObject.put("name", visibleText);
                JavascriptExecutor js = ((JavascriptExecutor) driver);
                js.executeScript("mobile: scroll", scrollObject);
                Thread.sleep(2000);
                */
            }
        }catch(Exception e){
            logger.error("unable to scroll..."+e.toString());
        }

    }
// Scroll Page
    public void scrollBySwipe_BottomUp() throws InterruptedException {
        if (SystemDetails.getAutomationTarget().equalsIgnoreCase("ANDROID")) {
            //Android logic here
        } else {
            logger.debug("scrolling by screen swipe vertical bottom up .");
            Dimension dim = driver.manage().window().getSize();
            int width = dim.getWidth() / 2;
            Double ScreenHeightStart = dim.getHeight() * 0.80;
            int scrollS = ScreenHeightStart.intValue();
            Double ScreenHeightEnd = dim.getHeight() * 0.20;
            int scrollE = ScreenHeightEnd.intValue();
            new TouchAction((PerformsTouchActions) driver)
                    .press(PointOption.point(width, scrollS))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(width, scrollE))
                    .release().perform();
            sleep(1000);
        }
    }


    public String getTextOfWebElement(WebElement element) {
        waitFor(element);
        String text = element.getText();
        logger.info("Element text [" + text+"]");
        return text;
    }

    public String getTextOfMobileElement(MobileElement element) {
        String text;
        waitFor(element);
        text = element.getText();
        logger.info("element text is: " + text);
        //element.clear();
        return text;

    }

    public void printList(List<WebElement> list) {
        logger.info("size of the list is " + list.size());
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                logger.info(list.get(i).getText());
            }
        } else {
            logger.info("elements are not present");
        }
    }

    public void printListMobile(List<MobileElement> list) {
        logger.info("size of the list is " + list.size());
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                logger.info(list.get(i).getText());
            }
        } else {
            logger.info("elements are not present");
        }
    }

    public void navigateBack() {
        try {
            driver.navigate().back();
            logger.info("back button clicked...");
            pause(5000);
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    public void navigateUp() {
        logger.debug("navigating back");
        List<MobileElement> list = driver.findElements(By.xpath("//*[@content-desc='Navigate up']"));
        if (list.size() > 0) {
            list.get(0).click();
            logger.debug("back button clicked...");
        } else {
            logger.debug("unable to navigate back");
        }
        pause(5000);
    }

    public void isElementDisplayed(WebElement element) {
        waitFor(element);
        if (element.isDisplayed()) {
            logger.info(element.getText() + " is displayed ");
        }
        else {
            Assert.fail("element is not displayed");
        }
    }

    public void isMobileElementDisplayed(MobileElement element) {
        waitFor(element);
        if (element.isDisplayed()) {
            logger.info(element.getText() + " is displayed ");
        } else {
            Assert.fail("element is not displayed");
        }
    }

    public void enterText(WebElement element, String value) {
        waitFor(element);
        element.sendKeys(value);
    }

    public String getTitleOfScreen() {
        String title = driver.getTitle();
        logger.info("title of the screen is " + title);
        return title;
    }

    public void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    public void setValue(MobileElement element, String value) {
        element.setValue(value);
    }
//Get all available contexts
    public void getAllContexts() {
        Set<String> contextNames = ((AppiumDriver) driver).getContextHandles();
        logger.debug("Available contexts >> "+ contextNames);
    }

    public String getPresentContext() {
        Set<String> contextNames = ((AppiumDriver) driver).getContextHandles();
        return getLastElement(contextNames).toString();
    }

    public Object getLastElement(final Collection c) {
        final Iterator itr = c.iterator();
        Object lastElement = itr.next();
        while (itr.hasNext()) {
            lastElement = itr.next();
        }
        return lastElement;
    }


    public static Integer[] getDigits(int num) {
        List<Integer> digits = new ArrayList<Integer>();
        collectDigits(num, digits);
        return digits.toArray(new Integer[]{});
    }
    private static void collectDigits(int num, List<Integer> digits) {
        if (num / 10 > 0) {
            collectDigits(num / 10, digits);
        }
        digits.add(num % 10);
    }
    public String getPageTitle(){
        logger.debug("pageTitle");
        return "testpage";
    }

    public static void dropDown(WebElement webElement, String value)
    {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            Thread.sleep(2000);
            Select drp = new Select(webElement);
            if(true)
                drp.selectByVisibleText(value);
            else
                drp.selectByValue(value);
        }
        catch (Exception e) {
            logger.error(e.toString());
            Assert.fail("Element " + webElement.getText() + " is not present");
        }

    }

    public static void dropDownIndex(WebElement webElement, int index)
    {
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
        Select drp = new Select (webElement);
        drp.selectByIndex(index);
    }

        public static void takeScreenShot(String screenShotName) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        Date date = new Date();
        String date1= dateFormat.format(date);
        DateFormat dateFormat1 = new SimpleDateFormat("ddMMyyyy HHmmss");
        Date date2 = new Date();
        String dateTime= dateFormat1.format(date2);
        TakesScreenshot scrShot =((TakesScreenshot) driver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File("target/testResult/screenshots/"+date1+"/"+screenShotName+dateTime+".JPEG");
        FileUtils.copyFile(SrcFile, DestFile);
    }

    public static void clearElement(WebElement webElement) {
        try {
            logger.debug("clearing on " + webElement.getText());
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.clear();
        } catch (Exception e) {
            logger.error(e.toString());
            Assert.fail("Element " + webElement.getText() + " is not present");
        }
    }
    public void TabKey() throws InterruptedException, AWTException {
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            Thread.sleep(1000);
    }

    public void EnterKey() throws InterruptedException, AWTException {
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }


    public void CtrlplusV() throws InterruptedException, AWTException {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

}
