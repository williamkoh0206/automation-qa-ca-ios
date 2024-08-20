package CATests.pageObjects.iOS.tests.delivery;

import CATests.POM.iOS.delivery.PickUpFromPage;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import CATests.POM.iOS.delivery.PickUpToPage;

public class PickUpToPageTest {
    private IOSDriver driver;

    public PickUpToPageTest(IOSDriver driver){
        this.driver = driver;
    }

    @Test
    public void testPickUpToPage(){
        PickUpToPage pickUpPage = new PickUpToPage(driver);
        System.out.println("-- Pick Up To Page --");

        //enter the estate number
        boolean isEstateNumberEntered = pickUpPage.enterEstateNumber();
        assertTrue(isEstateNumberEntered, "failed to enter the estate number");
        System.out.println("Enter recipient estate number: Success");

        //enter the room details
        boolean isRoomDetailsEntered = pickUpPage.enterRoomAndFloorDetails();
        assertTrue(isRoomDetailsEntered, "failed to enter the room details");
        System.out.println("Enter recipient room details: Success");

        //enter sender's name
        boolean isSenderNameEntered = pickUpPage.enterRecipientName();
        assertTrue(isSenderNameEntered, "failed to enter the recipient name");
        System.out.println("Enter recipient name: Success");

        //enter sender's phone number details
        boolean isSenderPhoneNumberEntered = pickUpPage.enterPhoneNumber();
        assertTrue(isSenderPhoneNumberEntered, "failed to enter the recipient phone number");
        System.out.println("Enter recipient phone number: Success");

        //click next button
        boolean isNextBtnClicked = pickUpPage.clickNextBtn();
        assertTrue(isNextBtnClicked, "failed to click on the next button");
        System.out.println("Click next button: Success");
    }
}
