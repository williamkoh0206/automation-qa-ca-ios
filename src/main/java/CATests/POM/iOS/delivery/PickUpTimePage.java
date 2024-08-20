package CATests.POM.iOS.delivery;

import CATests.POM.iOS.AbstractPageClass;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import CATests.utils.ConfigLoader;

public class PickUpTimePage extends AbstractPageClass {
    // call the configloader to get the values we want to input
    private ConfigLoader configLoader;

    public PickUpTimePage(IOSDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.configLoader = new ConfigLoader();
    }

    //list of elements
    @iOSXCUITFindBy(accessibility = "TRANSPORT_ORDER_PICK_UP_TIME_VIEW")
    private WebElement pickUpTimeViewOption;
    

}
