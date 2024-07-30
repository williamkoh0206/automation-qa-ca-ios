package CATests.pageObjects.iOS.tests.transport;

import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import CATests.POM.iOS.transport.OrderDetailsPage;

public class OrderDetailsPageTest {
    private IOSDriver driver;

    public OrderDetailsPageTest(IOSDriver driver){
        this.driver = driver;
    }

    //automate the OrderDetailsPage
    @Test
    public void testAutomateTheOrderDetailsPage(){
        OrderDetailsPage OrderDetailsPage = new OrderDetailsPage(driver);
        System.out.println("-- Order Details Page --");

        //select the cargo details button
        boolean isCargoDetailsBtnClicked = OrderDetailsPage.selectCargoCompensation();
        assertTrue(isCargoDetailsBtnClicked, "failed to click on the cargo details button");
        System.out.println("Selected the Cargo details option");

        //click the passengers counter button
        boolean isPassengersCounterBtnClicked = OrderDetailsPage.selectNumberOfPassengers();
        assertTrue(isPassengersCounterBtnClicked, "failed to click on the passengers counter button");

        //click the add cart counter button
        boolean isAddCartCounterBtnClicked = OrderDetailsPage.selectNumberOfCart();
        assertTrue(isAddCartCounterBtnClicked, "failed to click on the add cart counter button");

    }
}
