package CATests.POM.iOS.transport;

import CATests.POM.iOS.AbstractPageClass;
import io.appium.java_client.ios.IOSDriver;
import CATests.utils.GlobalState;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import CATests.utils.ConfigLoader;
import CATests.utils.JavaScriptHelper;

public class PlacedOrderTransportPage extends AbstractPageClass {
    // call the configloader to get the values we want to input
    private ConfigLoader configLoader;

    // call the javascript helper to scroll
    protected JavaScriptHelper jsHelper;

    public PlacedOrderTransportPage(IOSDriver driver){
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.configLoader = new ConfigLoader();
    }

    @iOSXCUITFindBy(accessibility = "Cancel order")
    private WebElement cancelOrderBtn;

    @iOSXCUITFindBy(accessibility = "CANCEL_ORDER_BUTTON")
    private WebElement confirmCancelOrderBtn;

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
