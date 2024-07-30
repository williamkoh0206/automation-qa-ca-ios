package CATests.POM.iOS;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.JavascriptExecutor;
import io.appium.java_client.ios.IOSTouchAction;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;


public class AbstractPageClass {
    protected IOSDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor jsHelper;
    protected IOSTouchAction touch;

    //setup the driver for all the page classes
    public AbstractPageClass(IOSDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.jsHelper = (JavascriptExecutor) driver;
    }
    // wait until a web element is visible
    public WebElement waitForVisibility(WebElement element) {
        return wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    //method to check if an element is visible and click it
    public boolean clickIfVisible(WebElement element, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
            if (visibleElement != null) {
                visibleElement.click();
                System.out.println("Clicked on the element " + element.toString());
                return true;
            }
        } catch (NoSuchElementException e) {
            //if no element is found
            System.err.println("Element not found: " + e.getMessage());
        } catch (Exception e) {
            //handle any other exceptions
            System.err.println("Error while trying to click the element:" + e.getMessage());
        }
        return false;
    }
}
