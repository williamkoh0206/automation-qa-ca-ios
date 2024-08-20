package CATests.POM.iOS.delivery;

import CATests.POM.iOS.AbstractPageClass;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import CATests.utils.ConfigLoader;

public class PickUpToPage extends AbstractPageClass {
    // call the configloader to get the values we want to input
    private ConfigLoader configLoader;

    public PickUpToPage(IOSDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.configLoader = new ConfigLoader();
    }

    //list of elements
    //list of the elements
    @iOSXCUITFindBy(accessibility = "Estate/street and number")
    private WebElement estateOrStreetNumber;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Estate/street and number']/preceding::XCUIElementTypeTextView")
    private WebElement estateOrStreetNumberInputField;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/following::XCUIElementTypeCell[1]")
    private WebElement firstAddressEstateOrStreetOption;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/following::XCUIElementTypeCell[1]//XCUIElementTypeStaticText[1]")
    private WebElement firstAddressEstateOrStreetTextOption;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@value='Room, floor, and block']/following::XCUIElementTypeTextField[1]")
    private WebElement roomAndFloorDetails;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@value='Recipient name']/following::XCUIElementTypeTextField[1]")
    private WebElement senderNameDetails;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@value='Phone number']/following::XCUIElementTypeTextField")
    private WebElement phoneNumberDetails;

    @iOSXCUITFindBy(accessibility = "Next")
    private WebElement clickNextBtn;

    public boolean enterEstateNumber(){
        try{
            WebElement estateOrStreetNumberVisibility = waitForVisibility(estateOrStreetNumber);
            estateOrStreetNumberVisibility.click();

            String estateStreetNumber = configLoader.getProperty("ESTATE_NUMBER_RECIPIENT");
            WebElement estateOrStreetNumberInputFieldVisibility = waitForVisibility(estateOrStreetNumberInputField);
            estateOrStreetNumberInputFieldVisibility.sendKeys(estateStreetNumber);
            WebElement firstAddressEstateOrStreetTextOptionVisibility = waitForVisibility(firstAddressEstateOrStreetOption);
            WebElement dynamicTextOptionVisible = waitForVisibility(firstAddressEstateOrStreetTextOption);
            Thread.sleep(2000);
            System.out.println("The first package To address text is: " + dynamicTextOptionVisible.getText());
            firstAddressEstateOrStreetTextOptionVisibility.click();
            return true;
        } catch (Exception e){
            System.out.println("Error clicking the estate or street number : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean enterRoomAndFloorDetails(){
        try{
            WebElement roomAndFloorDetailsVisibility = waitForVisibility(roomAndFloorDetails);
            roomAndFloorDetailsVisibility.click();
            String roomAndFloorDetails = configLoader.getProperty("ROOM_AND_FLOOR_RECIPIENT");
            roomAndFloorDetailsVisibility.sendKeys(roomAndFloorDetails);
            return true;
        } catch (Exception e){
            System.out.println("Error clicking the room and floor details : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean enterRecipientName(){
        try{
            String senderName = configLoader.getProperty("RECIPIENT_NAME");
            WebElement senderNameDetailsVisibility = waitForVisibility(senderNameDetails);
            senderNameDetailsVisibility.click();
            senderNameDetailsVisibility.clear();
            senderNameDetailsVisibility.sendKeys(senderName);
            return true;
        } catch (Exception e){
            System.out.println("Error clicking the sender name : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean enterPhoneNumber(){
        try{
            String phoneNumber = configLoader.getProperty("RECIPIENT_PHONE_NUMBER");
            WebElement phoneNumberDetailsVisibility = waitForVisibility(phoneNumberDetails);
            phoneNumberDetailsVisibility.click();
            phoneNumberDetailsVisibility.sendKeys(phoneNumber);
            return true;
        } catch (Exception e){
            System.out.println("Error clicking the phone number : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean clickNextBtn(){
        try{
            WebElement clickNextBtnVisibility = waitForVisibility(clickNextBtn);
            clickNextBtnVisibility.click();
            return true;
        } catch (Exception e){
            System.out.println("Error clicking the next button : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
