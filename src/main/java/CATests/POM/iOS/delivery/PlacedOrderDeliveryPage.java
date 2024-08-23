package CATests.POM.iOS.delivery;

import CATests.POM.iOS.AbstractPageClass;
import io.appium.java_client.ios.IOSDriver;
import CATests.utils.GlobalState;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import CATests.utils.ConfigLoader;
import CATests.utils.JavaScriptHelper;

public class PlacedOrderDeliveryPage extends AbstractPageClass {
    // call the configloader to get the values we want to input
    private ConfigLoader configLoader;

    // call the javascript helper to scroll
    protected JavaScriptHelper jsHelper;

    public PlacedOrderDeliveryPage(IOSDriver driver){
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.configLoader = new ConfigLoader();
    }

    //list of elements

    //orderID text
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@value='Order ID ']/preceding::XCUIElementTypeStaticText[1]")
    private WebElement orderId;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@value='Pick-up code']/following::XCUIElementTypeStaticText[1]")
    private WebElement pickUpCode;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Cancel order']")
    private WebElement cancelOrderBtn;

    @iOSXCUITFindBy(accessibility = "DELIVERY_CANCEL_ORDER_BUTTON")
    private WebElement confirmCancelOrderBtn;

    public boolean getOrderID(){
        try{
            String orderType = configLoader.getProperty("ORDER_TYPE");
            if(orderType.equalsIgnoreCase("D")){
                WebElement orderIdVisibility = waitForVisibility(orderId);
                System.out.println("The order ID is: " + orderIdVisibility.getText());
                //Extract oder ID
                GlobalState.globalOrderID = orderIdVisibility.getText();

                //Extract pick-up code
                WebElement pickUpCodeVisibility = waitForVisibility(pickUpCode);
                GlobalState.globalPickUpCode = pickUpCodeVisibility.getText();
                System.out.println("The pick-up code is: " + pickUpCodeVisibility.getText());
                return true;
            }
            return true;
        } catch (Exception e){
            System.out.println("Error capturing the orderID " + e.getMessage());
            return false;
        }
    }

    public boolean cancelOrder(){
        try{
            String cancelFlag = configLoader.getProperty("CANCEL_FLAG");
            if(cancelFlag.equalsIgnoreCase("false")){
                System.out.println("Skip the cancel order flow");
                return true;
            }
            else{
                System.out.println("Attempt to cancel order");
                WebElement cancelOrderBtnVisibility = waitForVisibilityWithScroll(cancelOrderBtn,"Cancel order");
                System.out.println("Scrolling down until reach the text");
                cancelOrderBtnVisibility.click();
                WebElement confirmCancelOrderBtnVisibility = waitForVisibility(confirmCancelOrderBtn);
                confirmCancelOrderBtnVisibility.click();
            }
            return true;
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error on cancelling the order: " + e.getMessage());
            return false;
        }
    }

}
