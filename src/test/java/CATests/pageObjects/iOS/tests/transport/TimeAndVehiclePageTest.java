package CATests.pageObjects.iOS.tests.transport;

import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import CATests.POM.iOS.transport.TimeAndVehiclePage;

public class TimeAndVehiclePageTest {
    private IOSDriver driver;

    public TimeAndVehiclePageTest(IOSDriver driver){
        this.driver = driver;
    }

    //automate the TimeAndVichlePage
    @Test
    public void testAutomateTheTimeAndVehiclePage(){
        TimeAndVehiclePage timeAndVehiclePage = new TimeAndVehiclePage(driver);
        System.out.println("-- Time And Vehicle Page --");

        //click on the date and time button
        boolean isDateTimeBtnClicked = timeAndVehiclePage.clickDateTimeBtn();
        assertTrue(isDateTimeBtnClicked, "failed to click on the date and time button");

        //click the pick-up date
        boolean isPickUpDateClicked = timeAndVehiclePage.clickPickUpDate();
        assertTrue(isPickUpDateClicked, "failed to click on the pick up date");

        //click the pick-up time
        boolean isPickUpTimeClicked = timeAndVehiclePage.clickPickUpTime();
        assertTrue(isPickUpTimeClicked, "failed to click on the pick up time");

        //click the date-time ok button
        boolean isDateTimeOkBtnClicked = timeAndVehiclePage.clickOkConfirmBtn();
        assertTrue(isDateTimeOkBtnClicked, "failed to click on the ok button");

        //click the hourly rental button
        boolean isHourlyRentalBtnClicked = timeAndVehiclePage.clickHourlyRentalBtn();
        assertTrue(isHourlyRentalBtnClicked, "failed to click on the hourly rental button");

        //click the hourly rental options
        boolean isHourlyRentalOptionsClicked = timeAndVehiclePage.selectHourlyRentalTime();
        assertTrue(isHourlyRentalOptionsClicked, "failed to click on the hourly rental options");

        //click on the hourly rental confirm button
        boolean isHourlyRentalBtnConfirmClicked = timeAndVehiclePage.clickOkConfirmBtn();
        assertTrue(isHourlyRentalBtnConfirmClicked, "failed to click on the confirm button");

        //click on the van option
        boolean isVanOptionClicked = timeAndVehiclePage.selectVehicleOptions();
        assertTrue(isVanOptionClicked, "failed to click on the van option");

        //click on the next button
        boolean isNextBtnClicked = timeAndVehiclePage.clickNextBtn();
        assertTrue(isNextBtnClicked, "failed to click on the next button");
    }

}
