package CATests.POM.iOS.transport;

import CATests.POM.iOS.AbstractPageClass;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import CATests.utils.ConfigLoader;
import java.util.HashMap;
import java.util.Map;


public class OrderDetailsPage extends AbstractPageClass{
    // call the configloader to get the values we want to input
    private ConfigLoader configLoader;

    public OrderDetailsPage(IOSDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.configLoader = new ConfigLoader();
    }

    //list of element locators

    //cargo included compensation button
    @iOSXCUITFindBy(accessibility = "Up to $2,000")
    private WebElement cargoIncludedCompensationBtn;

    //cargo basic compensation button
    @iOSXCUITFindBy(accessibility = "Up to $5,000")
    private WebElement cargoBasicCompensationBtn;

    //cargo standard compensation button
    @iOSXCUITFindBy(accessibility = "Up to $10,000")
    private WebElement cargoStandardCompensationBtn;

    //cargo premium compensation button
    @iOSXCUITFindBy(accessibility = "Up to $20,000")
    private WebElement cargoPremiumCompensationBtn;

    //passengers counter options(after received the id from developer)

    //goods longer than 6 feet option
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='ORDER_DETAIL_VC_GOODS_LONGER_THAN_6FT_CELL']//XCUIElementTypeImage[@name='checkbox_unselected']")
    private WebElement goodsLongerThanSixFtOption;


    @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='ORDER_DETAIL_VC_GOODS_TALLER_THAN_2FT_CELL']//XCUIElementTypeImage[@name='checkbox_unselected']")
    private WebElement goodsTallerThanTwoFtOption;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[.//XCUIElementTypeStaticText[@label='Pet-Friendly Driver']]//XCUIElementTypeImage[@name='checkbox_unselected']")
    private WebElement petFriendlyDriverOption;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[.//XCUIElementTypeStaticText[@label='English-speaking driver']]//XCUIElementTypeImage[@name='checkbox_unselected']")
    private WebElement englishSepakingDriverOption;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[.//XCUIElementTypeStaticText[@label='Tunnel preference']]//XCUIElementTypeImage[@name='checkbox_unselected']")
    private WebElement tunnelPreferenceOption;

    @iOSXCUITFindBy(accessibility = "No preference")
    private WebElement noPreferenceOption;

    @iOSXCUITFindBy(accessibility = "Cross-Harbour Tunnel")
    private WebElement crossHarbourTunnelOption;

    @iOSXCUITFindBy(accessibility = "Eastern Harbour Crossing")
    private WebElement easternHarbourCrossingOption;

    @iOSXCUITFindBy(accessibility = "Western Harbour Crossing")
    private WebElement westernHarbourCrossingOption;

    @iOSXCUITFindBy(accessibility = "Lion Rock Tunnel")
    private WebElement lionRockTunnelOption;

    @iOSXCUITFindBy(accessibility = "Tate's Cairn Tunnel")
    private WebElement tatesCairnTunnelOption;

    @iOSXCUITFindBy(accessibility = "Eagle's Nest Tunnel")
    private WebElement eaglesNestTunnelOption;

    @iOSXCUITFindBy(accessibility = "Shing Mun Tunnel")
    private WebElement shingMunTunnelOption;

    @iOSXCUITFindBy(accessibility = "Tai Lam Tunnel")
    private WebElement taiLamTunnelOption;

    @iOSXCUITFindBy(accessibility = "Aberdeen Tunnel")
    private WebElement aberdeenTunnelOption;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[.//XCUIElementTypeStaticText[@label='Move door-to-door']]//XCUIElementTypeImage[@name='checkbox_unselected']")
    private WebElement moveDoorToDoorOption;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[.//XCUIElementTypeStaticText[@label='Transport or dispose waste']]//XCUIElementTypeImage[@name='checkbox_unselected']")
    private WebElement transportOrDisposeWasteOption;

    @iOSXCUITFindBy(accessibility = "Order contact info")
    private WebElement orderContactInfoBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView//XCUIElementTypeStaticText[@name='Your name']/following::XCUIElementTypeOther[2]//XCUIElementTypeTextField")
    private WebElement contactInfoNameField;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView//XCUIElementTypeStaticText[@name='Your phone number']/following::XCUIElementTypeOther[2]//XCUIElementTypeTextField")
    private WebElement contactPhoneNumberField;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView//XCUIElementTypeOther[3]//XCUIElementTypeTextField")
    private WebElement contactExtensionField;

    @iOSXCUITFindBy(accessibility = "Save")
    private WebElement saveButton;

    @iOSXCUITFindBy(accessibility = "ggv__order_detail__footer__button__review_order")
    private WebElement reviewOrderBtn;

    //click cargo compensation button
    public boolean selectCargoCompensation(){
        try{
            String cargoOption = configLoader.getProperty("CARGO_COMPENSATION");
            if(cargoOption.equalsIgnoreCase("Included")){
                return true;
            }
            else if(cargoOption.equalsIgnoreCase("Basic")){
                WebElement basicCargoCompensationBtn = waitForVisibility(cargoBasicCompensationBtn);
                basicCargoCompensationBtn.click();
                return true;
            }
            else if(cargoOption.equalsIgnoreCase("Standard")){
                WebElement standardCargoCompensationBtn = waitForVisibility(cargoStandardCompensationBtn);
                standardCargoCompensationBtn.click();
            }
            else if(cargoOption.equalsIgnoreCase("Premium")){
                WebElement premiumCargoCompensationBtn = waitForVisibility(cargoPremiumCompensationBtn);
                premiumCargoCompensationBtn.click();
            }
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //select number of passengers
    public boolean selectNumberOfPassengers(){
        try{
            String numberOfPassengers = configLoader.getProperty("NUMBER_OF_PASSENGERS");
            int passengerCount = Integer.parseInt(numberOfPassengers);
            //use x-y coordinates to select the number of passengers
            int addPassengersCounterXaxis = 362;
            int addPassengersCounterYaxis = 316;
            int minusPassengersCounterXaxis = 315;
            int minusPassengersCounterYaxis = 313;

            if (passengerCount == 2){
                return true;
            }
            else if(passengerCount < 2){
                Map<String, Object> args = new HashMap<>();
                args.put("x",minusPassengersCounterXaxis);
                args.put("y",minusPassengersCounterYaxis);

                // Calculate the number of times to execute the script
                int timesToExecuteMinus = 2 - passengerCount;
                for (int i = 0; i < timesToExecuteMinus; i++) {
                    driver.executeScript("mobile: tap",args);
                }
                return true;
            }
            else if(passengerCount > 2){
                int timesToExecutePlus = passengerCount - 2;
                for (int i = 0; i < timesToExecutePlus; i++) {
                    Map<String, Object> args = new HashMap<>();
                    args.put("x",addPassengersCounterXaxis);
                    args.put("y",addPassengersCounterYaxis);
                    driver.executeScript("mobile: tap",args);
                }
            }
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //select number of cart
    public boolean selectNumberOfCart(){
        try{
            String excelNumOfCart = configLoader.getProperty("NUMBER_OF_CART");
            int numberOfCart = Integer.parseInt(excelNumOfCart);

            //use x-y coordinates to select the number of carts
            int addCartCounterXaxis = 363;
            int addCartCounterYaxis = 370;

            if(numberOfCart == 0){
                return true;
            }
            else if(numberOfCart > 0){
                for (int i =0; i < numberOfCart; i++){
                    Map<String, Object> args = new HashMap<>();
                    args.put("x",addCartCounterXaxis);
                    args.put("y",addCartCounterYaxis);
                    driver.executeScript("mobile: tap",args);
                    System.out.println("Clicked the cart for the cart for " + i + " times ");
                }
            }
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /* Other options
    1. goods longer than 6ft
    1.1 goods taller than 2ft
    2. pet friendly driver
    3. english-speaking drive
    */
    //select other options
    public boolean selectAdditionalOptions(){
        try{
            String longerThanSixFt = configLoader.getProperty("GOODS_LONGER_THAN_6_FT");
            String tallerThanTwoFt = configLoader.getProperty("GOODS_TALLER_THAN_2_FT");
            String petFriendlyDriver = configLoader.getProperty("PET_FRIENDLY");
            String englishSpeakingDriver = configLoader.getProperty("ENGLISH_SPEAKING");
                if (longerThanSixFt.equalsIgnoreCase("true")){
                    WebElement goodsLongerThanSixFtVisibility = waitForVisibility(goodsLongerThanSixFtOption);
                    goodsLongerThanSixFtVisibility.click();
                    System.out.println("Clicked on the longer than 6ft option");

                    if(tallerThanTwoFt.equalsIgnoreCase("true")){
                        WebElement goodsTallerThanTwoFtVisibility = waitForVisibility(goodsTallerThanTwoFtOption);
                        goodsTallerThanTwoFtVisibility.click();
                        System.out.println("Clicked on the taller than 2ft option");
                    }
                }
                if(petFriendlyDriver.equalsIgnoreCase("true")){
                    WebElement petFriendlyDriverVisibility = waitForVisibility(petFriendlyDriverOption);
                    petFriendlyDriverVisibility.click();
                    System.out.println("Clicked on the pet friendly driver option");
                }
                if(englishSpeakingDriver.equalsIgnoreCase("true")){
                    WebElement englishSpeakingDriverVisibility = waitForVisibility(englishSepakingDriverOption);
                    englishSpeakingDriverVisibility.click();
                    System.out.println("Clicked on the english speaking driver option");
                }
                return true;
            } catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }

    //Select tunnel preference options
    public boolean selectTunnelPreferenceOption() {
        try {
            System.out.println("Entered the tunnel preference option");
            String tunnelFlag = configLoader.getProperty("TUNNEL_PREFERENCE");
            if (tunnelFlag.equalsIgnoreCase("false")) {
                return true;
            }
            System.out.println("Trying to select the tunnel options");

            //Click the tunnel option
            WebElement tunnelOptionBtnVisible = waitForVisibility(tunnelPreferenceOption);
            tunnelOptionBtnVisible.click();
            System.out.println("Clicked tunnel preference");

            //Select the tunnel options
            String selectedTunnel = configLoader.getProperty("SELECTED_TUNNEL");
            switch (selectedTunnel){
                case "No preference":
                    WebElement noPreferenceBtnVisible = waitForVisibility(noPreferenceOption);
                    noPreferenceBtnVisible.click();
                    break;

                case "Cross-Harbour Tunnel":
                    WebElement crossHarbourTunnelBtnVisible = waitForVisibility(crossHarbourTunnelOption);
                    crossHarbourTunnelBtnVisible.click();
                    break;

                case "Eastern Harbour Crossing":
                    WebElement easternHarbourCrossingBtnVisible = waitForVisibility(easternHarbourCrossingOption);
                    easternHarbourCrossingBtnVisible.click();
                    break;

                case "Western Harbour Crossing":
                    WebElement westernHarbourCrossingBtnVisible = waitForVisibility(westernHarbourCrossingOption);
                    westernHarbourCrossingBtnVisible.click();
                    break;

                case "Lion Rock Tunnel":
                    WebElement lionRockTunnelBtnVisible = waitForVisibility(lionRockTunnelOption);
                    lionRockTunnelBtnVisible.click();
                    break;

                case "Tate's Cairn Tunnel":
                    WebElement tatesCairnTunnelBtnVisible = waitForVisibility(tatesCairnTunnelOption);
                    tatesCairnTunnelBtnVisible.click();
                    break;

                case "Eagle's Nest Tunnel":
                    WebElement enaglesNestTunnelBtnVisible = waitForVisibility(eaglesNestTunnelOption);
                    enaglesNestTunnelBtnVisible.click();
                    break;

                case "Shing Mun Tunnel":
                    WebElement shingMunTunnelBtnVisible = waitForVisibility(shingMunTunnelOption);
                    shingMunTunnelBtnVisible.click();
                    break;

                case "Tai Lam Tunnel":
                    WebElement taiLamTunnelBtnVisible = waitForVisibility(taiLamTunnelOption);
                    taiLamTunnelBtnVisible.click();
                    break;

                case "Aberdeen Tunnel":
                    WebElement abardeenTunnelBtnVisible = waitForVisibility(aberdeenTunnelOption);
                    abardeenTunnelBtnVisible.click();
                    break;

                default:
                    return true;
            }
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /*select other quote options
    1. Move door-to-door
    2. Transport or dispose waste
    */

    public boolean selectOtherQuoteOptions(){
        try{
            String moveDoorToDoor = configLoader.getProperty("MOVE_DOOR_TO_DOOR");
            String transportOrDisposeWaste = configLoader.getProperty("TRANSPORT_OR_DISPOSE_WASTE");
            if (moveDoorToDoor.equalsIgnoreCase("true")){
                WebElement moveDoorToDoorVisible = waitForVisibilityWithScroll(moveDoorToDoorOption);
                moveDoorToDoorVisible.click();
                System.out.println("Clicked on the move door to door option");
            }
            if (transportOrDisposeWaste.equalsIgnoreCase("true")){
                WebElement transportOrDisposeWasteVisible = waitForVisibilityWithScroll(transportOrDisposeWasteOption);
                transportOrDisposeWasteVisible.click();
                System.out.println("Clicked on the transport or dispose waste option");
            }
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //add contact info
    public boolean addContactInfo(){
        try{
            WebElement contactInfoBtnVisible = waitForVisibilityWithScroll(orderContactInfoBtn);
            contactInfoBtnVisible.click();
            System.out.println("Clicked contact info button");

            WebElement contactInfoNameFieldVisible = waitForVisibility(contactInfoNameField);
            String userName = configLoader.getProperty("USER_NAME");
            //remove the default tester username
            contactInfoNameFieldVisible.clear();
            contactInfoBtnVisible.sendKeys(userName);
            System.out.println("Entered the username successfully");

            WebElement contactPhoneNumberFieldVisible = waitForVisibility(contactPhoneNumberField);
            String phoneNumber = configLoader.getProperty("PHONE_NUMBER");
            //remove the default tester phone number
            contactPhoneNumberFieldVisible.clear();
            contactPhoneNumberFieldVisible.sendKeys(phoneNumber);
            System.out.println("Entered the phone number successfully");

            String extensionFlag = configLoader.getProperty("EXTENSION_FLAG");
            if (extensionFlag.equalsIgnoreCase("true")){
                String userExtension = configLoader.getProperty("EXTENSION");
                WebElement contactExtensionFieldVisible = waitForVisibility(contactExtensionField);
                contactExtensionFieldVisible.clear();
                contactExtensionFieldVisible.sendKeys(userExtension);
            }
            WebElement saveBtnVisible = waitForVisibility(saveButton);
            saveBtnVisible.click();
            return true;
        } catch (Exception e){
            System.out.println("Error adding the contact info: " + e.getMessage());
            return false;
        }
    }

    //click the review button
    public boolean clickReviewBtn(){
        try{
            WebElement reviewBtnVisible = waitForVisibility(reviewOrderBtn);
            reviewBtnVisible.click();
            return true;
        } catch (Exception e){
            System.out.println("Error clicking the review order button: " + e.getMessage());
            return false;
        }
    }
}
