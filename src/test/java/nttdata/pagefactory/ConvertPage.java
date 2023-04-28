package nttdata.pagefactory;

import nttdata.drivers.CustomWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class ConvertPage extends PageObject{

    @FindBy(how= How.XPATH, using = "//*[@id=\"tsk5_fu\"]")
    public static WebElement subject;
    @FindBy(how= How.XPATH, using = "//*[@id=\"bottomButtonRow\"]/input[1]")
    public static WebElement convert;

    public ConvertPage(CustomWebDriver customWebDriver) throws AWTException {
        super(customWebDriver);
    }
    public void convert_page()
    {
        Select accountname= new Select(driver.findElement(By.id("accid")));
        accountname.selectByValue("000000000000000");
        subject.sendKeys("AutoSpringBAss");
        convert.click();
    }

}
