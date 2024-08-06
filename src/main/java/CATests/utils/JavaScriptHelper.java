package CATests.utils;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.JavascriptExecutor;
import java.util.HashMap;

public class JavaScriptHelper {
    private static IOSDriver driver;

    public JavaScriptHelper(IOSDriver driver) {
        this.driver = driver;
    }

    // Method to scroll to an element with specific text using JavascriptExecutor
    public void scrollToElement(String text) {
        System.out.println("Trigger the scrollToElement function");

        try {
            // Instantiate a JavascriptExecutor to execute JavaScript code within the context of the IOSDriver
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Define a string representing the element selector for the element with the specified text
            String eleSelector = "new UiSelector().text(\"" + text + "\")";

            // Create a HashMap to store parameters for scrolling
            HashMap<String, Object> scrollObj = new HashMap<>();

            // Specify the strategy for scrolling, which is "ios uiautomation" indicating the use of iOS UI Automation
            scrollObj.put("strategy", "-ios uiautomation");

            // Specify the selector for the element to be scrolled to, using the previously defined 'eleSelector'
            scrollObj.put("selector", eleSelector);

            // Specify the direction of scrolling, in this case, "down"
            scrollObj.put("direction", "down");

            // Execute the JavaScript code to perform the scrolling action, using the 'mobile: scroll' command and passing the scroll parameters
            js.executeScript("mobile: scroll", scrollObj);

            System.out.println("Scrolled to element with text: " + text);
        } catch (Exception e) {
            System.out.println("Error during scroll: " + e.getMessage());
        }
    }
}
