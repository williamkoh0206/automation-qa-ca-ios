package CATests.POM.iOS.DATests;

import CATests.POM.iOS.AbstractPageClass;
import CATests.utils.Config;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.math3.analysis.function.Abs;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import CATests.utils.ConfigLoader;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import com.google.common.collect.ImmutableMap;

import static CATests.utils.GlobalState.globalOrderID;
import static CATests.utils.GlobalState.globalPickUpCode;

public class LandingDeliveryPage extends AbstractPageClass{
    private ConfigLoader configLoader;

    public LandingDeliveryPage(IOSDriver driver){
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.configLoader = new ConfigLoader();
    }

    //list of elements
    @iOSXCUITFindBy(accessibility = "Delivery")
    private WebElement deliveryOption;

    @iOSXCUITFindBy(accessibility = "OK")
    private WebElement verificationPopUp;

    @iOSXCUITFindBy(accessibility = "close")
    private WebElement closeOrderBtn;

    @iOSXCUITFindBy(accessibility = "Enter code to confirm pick-up")
    private WebElement confirmedPickupBtn;

    @iOSXCUITFindBy(accessibility = "Confirm delivery")
    private WebElement confirmDeliveryBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@value,'Photo')]/preceding::XCUIElementTypeStaticText[@value='Add']")
    private WebElement addPhotoBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@value,'Recipient')]/preceding::XCUIElementTypeStaticText[@value='Add']")
    private WebElement addSignatureBtn;

    @iOSXCUITFindBy(accessibility = "takePhoto")
    private WebElement takePhotoBtn;

    @iOSXCUITFindBy(accessibility = "Confirm upload")
    private WebElement confirmUploadBtn;

    @iOSXCUITFindBy(accessibility = "Complete Order")
    private WebElement completeOrderBtn;

    @iOSXCUITFindBy(accessibility = "Completed")
    private WebElement completedSortingOptionBtn;

    public boolean selectDeliveryOrder(){
        try{
            WebElement deliverOptionVisibility = waitForVisibility(deliveryOption);
            deliverOptionVisibility.click();
            return true;
        } catch (Exception e){
            System.out.println("Error clicking delivery option: " + e.getMessage());
            return false;
        }
    }

    public boolean clickDeliveryVerificationPopUp(){
        try{
            clickIfVisible(verificationPopUp,2);
            return true;
        } catch (Exception e){
            System.out.println("Error clicking the delivery popup " + e.getMessage());
            return false;
        }
    }

    public boolean pickDeliveryOrder(){
        try{
            int numOfDeliveryOrder = 1;
            while (true){
                //Construct the Xpath for delivery card element
                String deliveryCardXpath = "//XCUIElementTypeTable//XCUIElementTypeCell";
                if(numOfDeliveryOrder > 1){
                    //iterate the delivery card xpath structure e.g //XCUIElementTypeTable//XCUIElementTypeCell[1]
                    deliveryCardXpath += String.format("[%d]", numOfDeliveryOrder);
                }
                By deliveryOrderCardLocator = By.xpath(deliveryCardXpath);

                //Attempt to match the delivery order
                try{
                    WebElement deliveryOrderCardElement = driver.findElement(deliveryOrderCardLocator);
                    if(deliveryOrderCardElement != null){
                        //click the first delivery order
                        deliveryOrderCardElement.click();

                        //Construct the Xpath to match the OrderID, starting with '#' and ending with last three digits of globalOrderID
                        String lastThreeDigits = globalOrderID.substring(globalOrderID.length() - 3);
                        String orderIDPattern = String.format("#***%s", lastThreeDigits);
                        System.out.println("OrderID pattern: " + orderIDPattern);

                        //get the delivery order id xpath
                        String oderIdXpath = String.format("//XCUIElementTypeButton[contains(@name,'%s')]", orderIDPattern);
                        By orderIdLocator = By.xpath(oderIdXpath);

                        try{
                            //Locate the order ID element
                            WebElement orderElement = wait.until(ExpectedConditions.visibilityOfElementLocated(orderIdLocator));
                            if(orderElement != null){
                                //Using the clickIfVisible method to click the orderID element to check if it is visible
                                System.out.println("Order ID Xpath is: " + orderElement);
                                boolean isOrderClicked = clickIfVisible(orderElement, 2);
                                if(isOrderClicked){
                                    System.out.println("Order ID matched");
                                    return true;
                                } else{
                                    System.out.println("Order ID does not match, navigate back the landing delivery page");
                                    WebElement closeOrderBtnVisibility = waitForVisibility(closeOrderBtn);
                                    closeOrderBtnVisibility.click();
                                }
                            }
                        } catch (TimeoutException e){
                            System.out.println("Order ID not found for this order card " + numOfDeliveryOrder);
                            WebElement closeOrderBtnVisibility = waitForVisibility(closeOrderBtn);
                            closeOrderBtnVisibility.click();
                            //If the card element is not found, scroll and try again
                            System.out.println("Scrolling down to find more delivery order cards");
                            driver.executeScript("mobile: scroll", ImmutableMap.of(
                                    "direction", "down"
                            ));
                            Thread.sleep(2000);
                        }
                    }
                } catch (NoSuchElementException e){
                    //If the card element is not found, scroll and try again
//                    System.out.println("Scrolling down to find more delivery order cards");
//                    driver.executeScript("mobile: scroll", driver.findElement(deliveryOrderCardLocator), "toVisible", true);
//                    Thread.sleep(2000);
                }
                //try to check the next card
                numOfDeliveryOrder++;
            }
        } catch (Exception e){
            System.out.println("Error picking the delivery order " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean swipePickOrder(){
        try{
            //dragFromToWithVelocity action required parameters
            int fromX = 47;
            int toX = 367;
            int fromY = 774;
            int toY = 776;
            int pressDuration = 1;
            int holdDuration = 2;
            int velocity = 300; //each second move 300 pixel
            Map<String, Object> params = new HashMap<>();
            params.put("fromX",fromX);
            params.put("toX",toX);
            params.put("fromY",fromY);
            params.put("toY",toY);
            params.put("pressDuration",pressDuration);
            params.put("holdDuration",holdDuration);
            params.put("velocity",velocity);
            driver.executeScript("mobile: dragFromToWithVelocity",params);
            Thread.sleep(2000);
            return true;
        } catch (Exception e){
            System.out.println("Error to swipe for picking order: " + e.getMessage());
            return false;
        }
    }

    public boolean confirmDeliveryPopUp(){
        try{
            boolean afterSwipedNailedPopUp = clickIfVisible(confirmedPickupBtn,2);
            if(afterSwipedNailedPopUp){
                WebElement buttonCompletedOrderAfterSwipeVisible = waitForVisibility(confirmedPickupBtn);
                buttonCompletedOrderAfterSwipeVisible.click();
                System.out.println("Clicked the popup through clickIfVisible method");
            }
            else{
                System.out.println("Try to use coordinate alternative");
                int confirmPickUpBtnXais = 198;
                int confirmPickUpBtnYais = 775;
                Map<String, Object> args = new HashMap<>();
                args.put("x",confirmPickUpBtnXais);
                args.put("y",confirmPickUpBtnYais);
                driver.executeScript("mobile: tap", args);
                System.out.println("Clicked the popup through coordinate method");
            }
            return true;
        } catch (Exception e){
            System.out.println("Error complete order: " + e.getMessage());
            return false;
        }
    }

    public boolean completeOrder(){
        try{
            WebElement enterConfirmCodeBtnVisible = waitForVisibility(confirmedPickupBtn);
            enterConfirmCodeBtnVisible.click();
            System.out.println("Confirmed the order");
            String globalCode = globalPickUpCode;
            //Send the pickup code
            for(int i = 0; i< globalCode.length(); i++){
                //Construct the Xpath for each text field using index
                String pickupCodeFieldXPath = String.format("(//XCUIElementTypeTextField)[%d]", i+1);
                WebElement pickupCodeFieldLocator = driver.findElement(By.xpath(pickupCodeFieldXPath));
                // Send each character of globalCode to the text field
                char sendPickUpCodeByLetter = globalCode.charAt(i);
                System.out.println("Pick-up code by character is: " + sendPickUpCodeByLetter);
                pickupCodeFieldLocator.sendKeys(Character.toString(sendPickUpCodeByLetter));
            }
            System.out.println("Finish entering the code");

            //confirm delivery button
            WebElement confirmDeliveryBtnVisible = waitForVisibility(confirmDeliveryBtn);
            confirmDeliveryBtnVisible.click();
            System.out.println("Confirmed delivery");

            //add photo
            WebElement addPhotoForDropOffVisible = waitForVisibility(addPhotoBtn);
            addPhotoForDropOffVisible.click();
            System.out.println("Open the camera");

            //take photo with dark screen: no camera supported in simulator
            WebElement takePhotoBtnVisible = waitForVisibility(takePhotoBtn);
            takePhotoBtnVisible.click();
            System.out.println("Finish taking the photo");

            //confirm upload
            WebElement confirmUploadVisible = waitForVisibility(confirmUploadBtn);
            confirmUploadVisible.click();
            System.out.println("Finish uploading");

            //add signature
//            WebElement addSignatureVisible = waitForVisibility(addSignatureBtn);
//            addSignatureVisible.click();

            //complete order
            WebElement completeOrderBtnVisible = waitForVisibility(completeOrderBtn);
            completeOrderBtnVisible.click();
            System.out.println("Completed the order");


            return true;
        } catch (Exception e){
            System.out.println("Error on completing the order: " + e.getMessage());
            return false;
        }
    }

    public boolean reviewCompletedOrder(){
        try{
            WebElement completedSortingOptionBtnVisible = waitForVisibility(completedSortingOptionBtn);
            completedSortingOptionBtnVisible.click();
            String globalOrderId = globalOrderID;
            String completedOrderIDPattern = String.format("#%s",globalOrderId);
            System.out.println("Completed Order ID pattern: " + completedOrderIDPattern);

//            String completedOrderXpath = String.format("//XCUIElementTypeStaticText[contains(@value,'%s')]",completedOrderIDPattern);
            String completedOrderXpath = String.format("//XCUIElementTypeStaticText[contains(@value,'%s')]/preceding::XCUIElementTypeOther[1]",completedOrderIDPattern);
            By completedOrderLocator = By.xpath(completedOrderXpath);

            //Attempt to match the completed order ID
            try{
                WebElement completedOrderElement = driver.findElement(completedOrderLocator);
                if(completedOrderElement != null){
                    System.out.println("Completed Order ID Xpath is: " + completedOrderElement);
                    clickIfVisible(completedOrderElement,2);
                    System.out.println("Completed Order ID matched and being clicked");
                    Thread.sleep(2000);
                    closeOrderBtn.click();
                    return true;
                }
            } catch (Exception e){
                System.out.println("Completed Order ID cannot be found: " + e.getMessage());
            }
            return true;
        } catch (Exception e){
            System.out.println("Error on clicking the completed sorting option" + e.getMessage());
            return false;
        }
    }
}
