package CATests.POM.iOS;

import CATests.POM.iOS.AbstractPageClass;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import CATests.utils.ConfigLoader;

public class HomePage extends AbstractPageClass {
    // call the configloader to get the values we want to input
    private ConfigLoader configLoader;

    public HomePage(IOSDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.configLoader = new ConfigLoader();
    }

    //buttons
    //transport page button
    @iOSXCUITFindBy(accessibility="transport")
    private WebElement buttonToTransportPage;

    @iOSXCUITFindBy(accessibility = "delivery")
    private WebElement buttonToDeliveryPage;

    public boolean selectOrderTypeButton(){
        try{
            String orderType = configLoader.getProperty("ORDER_TYPE");
            if(orderType.equalsIgnoreCase("T")){
                WebElement buttonToTransportPageVisibility = buttonToTransportPage;
                buttonToTransportPageVisibility.click();
            } else if (orderType.equalsIgnoreCase("D")) {
                WebElement buttonToDeliveryPageVisibility = buttonToDeliveryPage;
                buttonToDeliveryPageVisibility.click();
            }
            return true;
        }catch (Exception e){
            System.out.println("Error clicking the transport button: " + e.getMessage());
            return false;
        }
    }
}
