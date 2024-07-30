package CATests.pageObjects.iOS.tests;

import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import CATests.POM.iOS.HomePage;

public class HomePageTest {
    private IOSDriver driver;
    public HomePageTest(IOSDriver driver){
        this.driver = driver;
    }

    @Test
    public void testAutomateTheHomePage(){
        HomePage homePage = new HomePage(driver);
        System.out.println("-- Home Page --");

        //click transport button
        boolean isClicked = homePage.clickTransportButton();
        assertTrue(isClicked, "failed to click on the transport button");
        System.out.println("Click transport button: Success");
    }
}
