package DATests.pageObjects.iOS.tests;

import CATests.POM.iOS.DATests.LandingPage;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import CATests.POM.iOS.DATests.LoginPage;

public class LandingPageTest {
    private IOSDriver driver;

    public LandingPageTest(IOSDriver driver){
        this.driver = driver;
    }

    @Test
    public void testAutomatedTheDALandingPage(){
        LandingPage landingPage = new LandingPage(driver);
        System.out.println("-- Landing DA Page --");

        //click the sorting option
        boolean isSortingOptionClicked = landingPage.clicksortingOption();
        assertTrue(isSortingOptionClicked, "failed to click on the sorting option");
        System.out.println("Sorting option clicked");

        //click on the first order
        boolean isFirstOrderClicked = landingPage.clickFirstOrder();
        assertTrue(isFirstOrderClicked, "failed to click on the first order");
        System.out.println("First order clicked and picked successfully");

        //complete the order
        boolean isOrderCompleted = landingPage.completeOrder();
        assertTrue(isOrderCompleted, "failed to complete the order");
        System.out.println("Order completed successfully");
    }
}
