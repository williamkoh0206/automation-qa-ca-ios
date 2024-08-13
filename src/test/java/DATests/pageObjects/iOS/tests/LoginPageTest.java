package DATests.pageObjects.iOS.tests;

import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import CATests.POM.iOS.DATests.LoginPage;

public class LoginPageTest {
    private IOSDriver driver;

    public LoginPageTest(IOSDriver driver){
        this.driver = driver;
    }

    @Test
    public void testAutomateTheDALoginPage(){
        LoginPage loginPage = new LoginPage(driver);
        System.out.println("-- Login DA Page  --");

        //enter the contactPhoneNumber
        boolean isUserNameEntered = loginPage.enterLoginPhoneNumber();
        assertTrue(isUserNameEntered, "failed to enter the username");
        System.out.println("Enter contact number: Success");

        //enter the password
        boolean isPasswordEntered = loginPage.enterPassword();
        assertTrue(isPasswordEntered, "failed to enter the password");
        System.out.println("Enter password: Success");

        //click on the login button
        boolean isLoginButtonClicked = loginPage.clickLoginBtn();
        assertTrue(isLoginButtonClicked, "failed to click on the login button");
        System.out.println("Log In: Success");
    }
}
