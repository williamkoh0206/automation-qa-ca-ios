package CATests.pageObjects.iOS.tests.delivery;


import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import CATests.POM.iOS.delivery.PickUpTimePage;

public class PickUpTimePageTest {
    private IOSDriver driver;

    public PickUpTimePageTest(IOSDriver driver){
        this.driver = driver;
    }

    @Test
    public void testPickUpTimePage(){
        PickUpTimePage pickUpPage = new PickUpTimePage(driver);
        System.out.println("-- Pick Up Time Page --");

        //click the pick-up date button
        boolean isPickupTimeBtnSelected = pickUpPage.clickPickUpTimeOption();
        assertTrue(isPickupTimeBtnSelected, "failed to click on the date and time button");
        System.out.println("Click next button: Success");

        //click the pick-up date button
        boolean isPickupDateSelected = pickUpPage.clickPickUpDate();
        assertTrue(isPickupDateSelected, "failed to click on the pick up date");
        System.out.println("Select Date: Success");

        //click the pick-up time button
        boolean isTimeSelected = pickUpPage.clickPickUpTime();
        assertTrue(isTimeSelected, "failed to click on the pick up time");
        System.out.println("Select time: Success");

        //click the confirm button
        boolean isConfirmBtnClicked = pickUpPage.clickOkConfirmBtn();
        assertTrue(isConfirmBtnClicked, "failed to click on the confirm button");
        System.out.println("Click confirm button: Success");

        //pick the drop-off time
        boolean isDropOffTimeOptionClicked = pickUpPage.clickDropOffTimeOption();
        assertTrue(isDropOffTimeOptionClicked, "failed to click on the drop off time option");
        System.out.println("Click drop off time option: Success");

        //click next button
        boolean isNextBtnClicked = pickUpPage.clickNextBtn();
        assertTrue(isNextBtnClicked, "failed to click on the next button");
        System.out.println("Click next button: Success");
    }
}
