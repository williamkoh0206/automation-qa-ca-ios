package CATests.pageObjects.iOS.tests.transport;

import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import CATests.POM.iOS.transport.PlacedOrderTransportPage;

public class PlacedOrderTransportPageTest {
    private IOSDriver driver;

    public PlacedOrderTransportPageTest(IOSDriver driver){
        this.driver = driver;
    }

    @Test
    public void testPlacedOrderTransportPage(){
        PlacedOrderTransportPage placedOrderTransportPage = new PlacedOrderTransportPage(driver);
        System.out.println("-- PlacedOrder Transport Page --");

        //cancel order
        boolean isOrderCancelled = placedOrderTransportPage.cancelOrder();
        assertTrue(isOrderCancelled, "Failed to cancel order");
        System.out.println("cancel order: Success");
    }
}
