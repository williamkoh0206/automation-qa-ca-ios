package CATests.POM.iOS.DATests;

import CATests.POM.iOS.AbstractPageClass;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import CATests.utils.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends AbstractPageClass{
    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
    //call the configLoader to get the values for input
    private ConfigLoader configLoader;

    public LoginPage(IOSDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.configLoader = new ConfigLoader();
    }

    //list of elements
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@label='Contact Number']/following::XCUIElementTypeTextField")
    private WebElement contactNumberInputField;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther//XCUIElementTypeSecureTextField")
    private WebElement passwordInputField;

    @iOSXCUITFindBy(accessibility = "Log in")
    private WebElement loginButton;



    public boolean enterLoginPhoneNumber(){
        try{
            System.out.println("Attempting to the login page");
            WebElement contactNumberInputVisible = waitForVisibility(contactNumberInputField);
            contactNumberInputVisible.click();
            System.out.println("Clicked on the contact number field");

            String driverPhoneNumber = configLoader.getProperty("DRIVER_PHONE_NUMBER");
            System.out.println("Entering phone number: " + driverPhoneNumber);
            //In case the contact number input field has phone number left
            contactNumberInputVisible.clear();
            contactNumberInputVisible.sendKeys(driverPhoneNumber);

            //Press the enter button
            Actions actions = new Actions(driver);
            actions.sendKeys(driverPhoneNumber, Keys.RETURN).perform();
            System.out.println("Phone number entered and enter key pressed");
            return true;
        } catch (Exception e){
            System.out.println("Error entering contact number: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean enterPassword(){
        try{
            System.out.println("Attempting to enter password");
            WebElement passwordInputVisible = waitForVisibility(passwordInputField);
            passwordInputVisible.click();
            System.out.println("Clicked on the password field");

            String driverPassword = configLoader.getProperty("DRIVER_PASSWORD");
            System.out.println("Entering password: " + driverPassword);
            passwordInputVisible.clear();
            System.out.println("clear the password field first");
            passwordInputVisible.sendKeys(driverPassword);

            // Dismiss the keyboard
            driver.hideKeyboard();
            System.out.println("Keyboard dismissed");

            //Press the enter button
//            Actions actions = new Actions(driver);
//            actions.sendKeys(driverPassword, Keys.RETURN).perform();
//            System.out.println("Password entered and enter key pressed");
            return true;
        } catch(Exception e){
            System.out.println("Error entering password: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean clickLoginBtn(){
        try{
            System.out.println("Attempting to click the login button");
            clickIfVisible(loginButton, 2);
//            WebElement loginButtonVisible = waitForVisibility(loginButton);
//            loginButtonVisible.click();
//            Thread.sleep(2000);
            System.out.println("Login button clicked");
            return true;
        }
        catch (Exception e){
            System.out.println("Error on clicking the login button");
            e.printStackTrace();
            return false;
        }
    }

}
