package CATests.POM.iOS;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;
import CATests.utils.JavaScriptHelper;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import java.time.Duration;


public class AbstractPageClass {
    protected IOSDriver driver;
    protected WebDriverWait wait;
    protected JavaScriptHelper jsHelper;
    protected IOSTouchAction touch;

    //setup the driver for all the page classes
    public AbstractPageClass(IOSDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.jsHelper = new JavaScriptHelper(driver);
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

    public WebElement waitForVisibilityWithScroll(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        boolean canScrollMore = true;

        while(canScrollMore){
            try {
                //wait for the element to be visible
                System.out.println("Waiting for an element to be visible");
                WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));

                //Get the current screen dimensions
                int windowHeight = driver.manage().window().getSize().getHeight();

                //get the y-coordinate of the bottom of the element
                int elementBottom = visibleElement.getLocation().getY() + visibleElement.getSize().getHeight();
                System.out.println("The current screen height is : " + windowHeight);
                System.out.println("The bottom of the element height is : " + elementBottom);

                //Check if the element is visible or not
                if (elementBottom < windowHeight) {
                    return visibleElement; //element is fully visible in the screen
                } else {
                    System.out.println("Element is partically visible or covered, need to scroll further");
                    jsHelper.scrollToElement("Review order");
                    System.out.println("returned from scrolldown");
                    WebElement visibleElement2 = wait.until(ExpectedConditions.visibilityOf(element));
                    return visibleElement2;
                }
            } catch(org.openqa.selenium.TimeoutException e){
                // If the element is not visible within the timeout, scroll down using TouchAction
                System.out.println("Element not visible, scrolling down");
                jsHelper.scrollToElement("Review order");

                // Small delay after each scroll to allow UI to update
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Thread was interrupted", ie);
                }
            } catch (Exception e) {
                // Handle other exceptions and break the loop if scrolling is no longer possible
                throw new RuntimeException("An error occurred while waiting for visibility or scrolling: " + e.getMessage());
            }
        }
        throw new RuntimeException("Element not found after scrolling");
    }
}
