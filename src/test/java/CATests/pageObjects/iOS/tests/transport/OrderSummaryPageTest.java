package CATests.pageObjects.iOS.tests.transport;

import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import CATests.POM.iOS.transport.OrderSummaryPage;

public class OrderSummaryPageTest {
    private IOSDriver driver;

    public OrderSummaryPageTest(IOSDriver driver){
        this.driver = driver;
    }

    @Test
    public void testAutomateTheOrderSummaryPage(){
        OrderSummaryPage OrderSummaryPage = new OrderSummaryPage(driver);
        System.out.println("-- Order Summary Page --");

        //select the tip options
        boolean isTipOptionsClicked = OrderSummaryPage.selectTipsOption();
        assertTrue(isTipOptionsClicked, "failed to click on the tip options");

        //select the coupon
        boolean isCouponClicked = OrderSummaryPage.applyCoupon();
        assertTrue(isCouponClicked, "failed to click on the coupon");

        //select the payment option
        boolean isPaymentOptionClicked = OrderSummaryPage.selectPaymentMethod();
        assertTrue(isPaymentOptionClicked, "failed to click on the payment option");

        //place the order
        boolean isOrderPlaced = OrderSummaryPage.placeOrder();
        assertTrue(isOrderPlaced, "failed to place the order");
    }
}
