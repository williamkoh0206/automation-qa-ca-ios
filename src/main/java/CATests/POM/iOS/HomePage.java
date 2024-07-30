package CATests.POM.iOS;

import CATests.POM.iOS.AbstractPageClass;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPageClass {
    public HomePage(IOSDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //buttons
    //transport page button
    @iOSXCUITFindBy(accessibility="transport")
    private WebElement buttonToTransportPage;

    @iOSXCUITFindBy(accessibility = "delivery")
    private WebElement buttonToDeliveryPage;

    public boolean clickTransportButton(){
        try{
            WebElement buttonToTransportPageVisibility = buttonToTransportPage;
            buttonToTransportPageVisibility.click();
            return true;
        }catch (Exception e){
            System.out.println("Error clicking the transport button: " + e.getMessage());
            return false;
        }
    }
}
