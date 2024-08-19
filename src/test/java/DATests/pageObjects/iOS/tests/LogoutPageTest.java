package DATests.pageObjects.iOS.tests;

import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import CATests.POM.iOS.DATests.LogoutPage;

public class LogoutPageTest {
    private IOSDriver driver;

    public LogoutPageTest(IOSDriver driver){
        this.driver = driver;
    }

    @Test
    public void testAutomateTheDALogoutPage(){
        LogoutPage logoutPage = new LogoutPage(driver);
        System.out.println("-- Logout DA Page --");

        //click on the side menu
        boolean isSideMenuClicked = logoutPage.openSideMenu();
        assertTrue(isSideMenuClicked, "failed to click on the logout button");
        System.out.println("Logout: Success");

        //go to settings page
        boolean isSettingsClicked = logoutPage.goToSettings();
        assertTrue(isSettingsClicked, "failed to click on the settings button");
        System.out.println("Settings: Success");

        //logout
        boolean isLogoutClicked = logoutPage.logout();
        assertTrue(isLogoutClicked, "failed to click on the logout button");
        System.out.println("Logout: Success");
    }
}
