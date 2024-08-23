package CATests.pageObjects.iOS.tests.delivery;

import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import CATests.POM.iOS.delivery.PlacedOrderDeliveryPage;

public class PlacedOrderDeliveryPageTest {
    private IOSDriver driver;

    public PlacedOrderDeliveryPageTest(IOSDriver driver){
        this.driver = driver;
    }

    @Test
    public void testPlacedOrderDeliveryPage(){
        PlacedOrderDeliveryPage placedOrderDeliveryPage = new PlacedOrderDeliveryPage(driver);
        System.out.println("-- PlacedOrderDelivery Page --");

        //get orderID
        boolean isOrderIDSelected = placedOrderDeliveryPage.getOrderID();
        assertTrue(isOrderIDSelected,"failed to get the order ID");
        System.out.println("Copy the order ID: Success");

        //cancel order
        boolean isOrderCancelled = placedOrderDeliveryPage.cancelOrder();
        assertTrue(isOrderCancelled, "Failed to cancel order");
        System.out.println("cancel order: Success");
    }
}
