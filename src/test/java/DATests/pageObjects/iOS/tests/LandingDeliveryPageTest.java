package DATests.pageObjects.iOS.tests;

import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import CATests.POM.iOS.DATests.LandingDeliveryPage;

public class LandingDeliveryPageTest {
    private IOSDriver driver;

    public LandingDeliveryPageTest(IOSDriver driver){
        this.driver = driver;
    }

    @Test
    public void testAutomatedTheDALandingDeliveryPage(){
        LandingDeliveryPage landingDeliveryPage = new LandingDeliveryPage(driver);
        System.out.println("-- Landing Delivery Page --");

        //click the delivery option
        boolean isDeliveryOptionClicked = landingDeliveryPage.selectDeliveryOrder();
        assertTrue(isDeliveryOptionClicked,"failed to click the delivery option");
        System.out.println("delivery option being clicked");

        //delivery order popup
        boolean isDeliveryVerificationPopUpClicked = landingDeliveryPage.clickDeliveryVerificationPopUp();
        assertTrue(isDeliveryVerificationPopUpClicked, "failed to click the delivery popup");

        //pick delivery order option
        boolean isDeliveryOrderClicked = landingDeliveryPage.pickDeliveryOrder();
        assertTrue(isDeliveryOrderClicked,"failed to pick the delivery order");
        System.out.println("delivery order being selected");

        //swipe to pick delivery order
        boolean isDeliveryOrderSwiped = landingDeliveryPage.swipePickOrder();
        assertTrue(isDeliveryOrderSwiped,"failed to swipe for confirming the order");
        System.out.println("swiped to get the order successfully");

        //confirm the delivery confirmation popup
        boolean isDeliveryPopUpClicked = landingDeliveryPage.confirmDeliveryPopUp();
        assertTrue(isDeliveryPopUpClicked,"failed to click the delivery confirmation pop up");

        //complete order
        boolean isOrderCompleted = landingDeliveryPage.completeOrder();
        assertTrue(isOrderCompleted,"failed to complete the order");
        System.out.println("Finished delivery order");

        //review the completed order
        boolean isReviewOrderClicked = landingDeliveryPage.reviewCompletedOrder();
        assertTrue(isReviewOrderClicked,"failed to review the completed order");
        System.out.println("Finish reviewing the order");
    }
}
