package CATests.pageObjects.iOS.tests.transport;

import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import CATests.POM.iOS.transport.AddressPage;

import java.net.MalformedURLException;
import java.net.URL;

public class AddressPageTest {
    private IOSDriver driver;

    public AddressPageTest(IOSDriver driver){
        this.driver = driver;
    }

    //automate the address page
    @Test
    public void testAutomateTheAddressPage(){
        AddressPage addressPage = new AddressPage(driver);
        System.out.println("-- Address Page --");

        //click on the "where from" button
        boolean isWhereFromClicked = addressPage.clickWhereFromBtn();
        assertTrue(isWhereFromClicked, "failed to click on the where from button");

        //Enter address into the "where from" input field
        boolean isFromAddressEntered = addressPage.enterAddressFromInput();
        assertTrue(isFromAddressEntered, "failed to enter address into the where from input field");

        // Get and print the first from address text option
        String firstFromAddressText = addressPage.getFirstAddressTextOption();
        System.out.println("The first FROM address text is: " + firstFromAddressText);

        //click on the first address text option
        boolean isFirstFromAddressTextOptionClicked = addressPage.clickFirstAddressTextOption();
        assertTrue(isFirstFromAddressTextOptionClicked, "failed to click on the first from address text option");

        // click on the "where to" button
        boolean isWhereToClicked = addressPage.clickWhereToBtn();
        assertTrue(isWhereToClicked, "failed to click on the where to button");

        // enter address into the "where to" input field
        boolean isInputFieldBoxClicked = addressPage.enterAddressToInput();
        assertTrue(isInputFieldBoxClicked, "failed to click on the input field box");

        // Get and print the first to address text option
        String firstToAddressText = addressPage.getFirstAddressTextOption();
        System.out.println("The first TO address text is: " + firstToAddressText);

        //click on the first address text option
        boolean isFirstToOptionTextClicked = addressPage.clickFirstAddressTextOption();
        assertTrue(isFirstToOptionTextClicked, "failed to click on the first to address text option");

        //click on the swap address button if necessary
        boolean isSwapAddressBtnClicked = addressPage.clickSwipeBtn();
        assertTrue(isSwapAddressBtnClicked, "failed to click on the swap address button");

        //click on the stop stations if necessary
        boolean isStopStationsBtnClicked = addressPage.addStopBtn();
        assertTrue(isStopStationsBtnClicked, "failed to click on the stop stations button");
        System.out.println("Finish adding the stop stations");

        //click on the next button
        boolean isNextBtnClicked = addressPage.clickNextBtn();
        assertTrue(isNextBtnClicked, "failed to click on the next button");
    }
}
