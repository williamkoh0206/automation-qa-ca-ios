package CATests.pageObjects.iOS.tests;

import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;
import CATests.POM.iOS.LoginPage;
import static org.testng.Assert.assertTrue;

public class LoginPageTest {
    private IOSDriver driver;
    public LoginPageTest(IOSDriver driver){
        this.driver = driver;
    }

    //automate the login process
    @Test
    public void testAutomateTheLoginPage(){
        LoginPage loginPage = new LoginPage(driver);
        System.out.println("--Login Process --");

        //click on the sidemenu settings button
        boolean isSideMenuButtonClicked = loginPage.openSettings();
        assertTrue(isSideMenuButtonClicked, "failed to click on the settings button");
        System.out.println("Open settings success");

        //click on the login button
        boolean isLoginButtonClicked = loginPage.openLogin();
        assertTrue(isLoginButtonClicked, "failed to click on the login button");
        System.out.println("Open login or Signup success");

        //enter username
        boolean isUserNameEntered = loginPage.enterLoginInfo();
        assertTrue(isUserNameEntered, "failed to enter username.");
        System.out.println("Enter username: Success");

        //enter next button after inputting username
        boolean isNextButtonClicked = loginPage.clickNextBtn();
        assertTrue(isNextButtonClicked, "failed to click on the button to sign in or sign up.");
        System.out.println("Open Login Or Sign up: Success");

        //enter password
        boolean isPasswordEntered = loginPage.enterPassword();
        assertTrue(isPasswordEntered, "failed to enter password.");
        System.out.println("Enter password: Success");

        //enter the login button after inputting the password
        boolean isLoginButtonVisible = loginPage.clickLoginBtn();
        assertTrue(isLoginButtonVisible, "failed to click on the login button.");
        System.out.println("Click login button: Success");

        //enter the exit button after login successfully
        boolean isCloseButtonVisible = loginPage.clickCloseBtn();
        assertTrue(isCloseButtonVisible, "failed to click on the exit button.");
        System.out.println("Click Exit button: Success");
    }
}
