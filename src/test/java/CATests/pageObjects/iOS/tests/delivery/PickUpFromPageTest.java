package CATests.pageObjects.iOS.tests.delivery;

import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import CATests.POM.iOS.delivery.PickUpFromPage;

public class PickUpFromPageTest {
    private IOSDriver driver;

    public PickUpFromPageTest(IOSDriver driver){
        this.driver = driver;
    }

    @Test
    public void testPickUpFromPage(){
        PickUpFromPage pickUpPage = new PickUpFromPage(driver);
        System.out.println("-- Pick Up From Page --");

        //enter the estate number
        boolean isEstateNumberEntered = pickUpPage.enterEstateNumber();
        assertTrue(isEstateNumberEntered, "failed to enter the estate number");
        System.out.println("Enter estate number: Success");

        //enter the room details
        boolean isRoomDetailsEntered = pickUpPage.enterRoomAndFloorDetails();
        assertTrue(isRoomDetailsEntered, "failed to enter the room details");
        System.out.println("Enter room details: Success");

        //enter sender's name
        boolean isSenderNameEntered = pickUpPage.enterSenderName();
        assertTrue(isSenderNameEntered, "failed to enter the sender name");
        System.out.println("Enter sender name: Success");

        //enter sender's phone number details
        boolean isSenderPhoneNumberEntered = pickUpPage.enterPhoneNumber();
        assertTrue(isSenderPhoneNumberEntered, "failed to enter the sender phone number");
        System.out.println("Enter sender phone number: Success");

        //click next button
        boolean isNextBtnClicked = pickUpPage.clickNextBtn();
        assertTrue(isNextBtnClicked, "failed to click on the next button");
        System.out.println("Click next button: Success");
    }
}
