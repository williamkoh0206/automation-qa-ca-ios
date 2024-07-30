package CATests.POM.iOS;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;
import CATests.utils.ConfigLoader;

public class LoginPage extends AbstractPageClass {

    //call the configLoader to get the values for input
    private ConfigLoader configLoader;

    public LoginPage(IOSDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.configLoader = new ConfigLoader(); //initialize the configLoader
    }

    //Element locator variables
    @iOSXCUITFindBy(accessibility = "Log in or sign up")
    private WebElement loginSignupButton;

    @iOSXCUITFindBy(accessibility = "login_email_number__input__email_number")
    private WebElement emailInputField;


    @iOSXCUITFindBy(accessibility = "login_email_number__button__next")
    private WebElement nextButton;

    @iOSXCUITFindBy(accessibility = "login_password__input__password")
    private WebElement passwordInputField;

    @iOSXCUITFindBy(accessibility = "login_password__button__login")
    private WebElement loginButton;

    @iOSXCUITFindBy(accessibility = "profile__header__navigation_button__close")
    private WebElement closeButton;

    //functions:
    // open settings
    public boolean openSettings(){
        try{
            //sideMenuIcon coordinates
            int sideMenuIconXaxis = 27;
            int sideMenuIconYaxis = 88;
            //create a map to hold the sideMenuIcon coordinates
            Map<String, Object> args = new HashMap<>();
            args.put("x",sideMenuIconXaxis);
            args.put("y",sideMenuIconYaxis);
            driver.executeScript("mobile: tap", args);
            return true;
        }catch (Exception e){
            System.out.println("Error clicking the settings button: " + e.getMessage());
            return false;
        }
    }

    public boolean openLogin(){
        try{
            WebElement buttonLoginOrSignupVisible = waitForVisibility(loginSignupButton);
            buttonLoginOrSignupVisible.click();
            return true;
        }catch (Exception e){
            System.out.println("Error clicking the login button: " + e.getMessage());
            return false;
        }
    }

    //enter username or email
   public boolean enterLoginInfo(){
        try{
            WebElement emailInputFieldVisible = waitForVisibility(emailInputField);
            emailInputFieldVisible.click();
            String userName = configLoader.getProperty("EMAIL_OR_PHONENUMBER");
            emailInputField.sendKeys(userName);
            //press the enter button
            Actions actions = new Actions(driver);
            actions.sendKeys(emailInputField, Keys.RETURN).perform();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
   }
    //click next button
   public boolean clickNextBtn(){
        try{
            WebElement nextButtonVisible = waitForVisibility(nextButton);
            nextButtonVisible.click();
            return true;
        }catch (Exception e){
            System.out.println("Error clicking the next button: " + e.getMessage());
            return false;
        }
   }

    //enter password
   public boolean enterPassword(){
        try{
            WebElement passwordInputFieldVisible = waitForVisibility(passwordInputField);
            passwordInputFieldVisible.click();
            String password = configLoader.getProperty("PASSWORD");
            passwordInputField.sendKeys(password);
            //press the enter button
            Actions actions = new Actions(driver);
            actions.sendKeys(passwordInputField, Keys.RETURN).perform();
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
   }

   //click login button
    public boolean clickLoginBtn(){
        try {
            WebElement loginButtonVisible = waitForVisibility(loginButton);
            loginButtonVisible.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the login button: " + e.getMessage());
            return false;
        }
    }

    //click close button
    public boolean clickCloseBtn(){
        try{
            WebElement closeButtonVisible = waitForVisibility(closeButton);
            closeButtonVisible.click();
            return true;
        }catch (Exception e){
            System.out.println("Error clicking the close button: " + e.getMessage());
            return false;
        }
    }
}