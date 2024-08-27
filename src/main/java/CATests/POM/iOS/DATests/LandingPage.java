package CATests.POM.iOS.DATests;

import CATests.POM.iOS.AbstractPageClass;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.math3.analysis.function.Abs;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import CATests.utils.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

public class LandingPage extends AbstractPageClass {
    private ConfigLoader configLoader;

    public LandingPage(IOSDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.configLoader = new ConfigLoader();
    }

    //buttons
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTable//XCUIElementTypeCell[1]")
    private WebElement firstOrderOption;

    @iOSXCUITFindBy(accessibility = "Pick order")
    private WebElement pickOrderBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='ASAP']/preceding::XCUIElementTypeButton[@name='ASAP']")
    private WebElement sortingBtn;

    @iOSXCUITFindBy(accessibility = "Confirm pick order")
    private WebElement confirmPickOrderBtn;

    @iOSXCUITFindBy(accessibility = "Drop order")
    private WebElement dropOrderBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Now, you can text your rider using in-app chat for free!']")
    private WebElement confirmedPickingOrderPopup;

    @iOSXCUITFindBy(accessibility = "Begin Driving")
    private WebElement beginDriverBtn;

    @iOSXCUITFindBy(accessibility = "Arrived pick-up point")
    private WebElement arrivedPickUpPointBtn;

    @iOSXCUITFindBy(accessibility = "Arrived drop-off point")
    private WebElement arrivedDropOffPointBtn;

    @iOSXCUITFindBy(accessibility = "Complete Order")
    private WebElement completeOrderBtn;

    @iOSXCUITFindBy(accessibility = "View Order Price to complete order")
    private WebElement viewOrderPriceToCompleteOrderBtn;

    @iOSXCUITFindBy(accessibility = "Continue pick order!")
    private WebElement continuePickOrderBtn;

    public boolean clicksortingOption(){
        try{
            //Wait for 2 seconds after login to make sure the landing page is loaded
            Thread.sleep(2000);
            WebElement sortingOptionVisible = waitForVisibility(sortingBtn);
            sortingOptionVisible.click();
            //click the latest option with x-y coordinates
            int latestOptionXaxis = 360;
            int latestOptionYaxis = 654;

            Map<String, Object> args = new HashMap<>();
            args.put("x",latestOptionXaxis);
            args.put("y",latestOptionYaxis);
            driver.executeScript("mobile: tap", args);

            return true;
        } catch(Exception e){
            System.out.println("Error clicking sorting option: " + e.getMessage());
            return false;
        }
    }

    public boolean clickFirstOrder(){
        try{
            WebElement firstOrderOptionVisible = waitForVisibility(firstOrderOption);
            firstOrderOptionVisible.click();
            System.out.println("First option order being clicked, attempting to pick the order");
            boolean pickOrderBtnVisibility = clickIfVisible(pickOrderBtn,5);
            if(pickOrderBtnVisibility){
                WebElement pickOrderBtnVisible = waitForVisibility(pickOrderBtn);
                pickOrderBtnVisible.click();
                System.out.println("Picking order by click visible method");
            }
            else{
                System.out.println("Try to use coordinate alternative to click the pick order button");
                int pickOrderXaxis = 200;
                int pickOrderYaxis = 408;
                Map<String, Object> args = new HashMap<>();
                args.put("x",pickOrderXaxis);
                args.put("y",pickOrderYaxis);
                driver.executeScript("mobile: tap", args);
                System.out.println("Picking order by coordination method");
                return true;
            }
            System.out.println("Finish picking the order process");
            return true;
        } catch(Exception e){
            System.out.println("Error clicking order: " + e.getMessage());
            return false;
        }
    }

    public boolean completeOrder(){
        try{
//            clickIfVisible(confirmPickOrderBtn,5);
//            System.out.println("Picked up the order successfully");
            boolean beginDriverBtnVisibility = clickIfVisible(beginDriverBtn, 3);
            if(beginDriverBtnVisibility){
                WebElement clickConfirmedPopUpVisible = waitForVisibility(beginDriverBtn);
                clickConfirmedPopUpVisible.click();
            }
            else{
                System.out.println("Try to use coordinate alternative to click the begin driver button");
                int beginDriverXaxis = 208;
                int beginDriverYaxis = 778;
                Map<String, Object> args = new HashMap<>();
                args.put("x",beginDriverXaxis);
                args.put("y",beginDriverYaxis);
                driver.executeScript("mobile: tap", args);
                Thread.sleep(2000);
            }
            System.out.println("After clicking the popup, attempting to begin driving");
            WebElement beginDriverBtnVisible = waitForVisibility(beginDriverBtn);
            beginDriverBtnVisible.click();
            System.out.println("Attempt to arrive at pick-up point");
            WebElement arrivedPickUpPointBtnVisible = waitForVisibility(arrivedPickUpPointBtn);
            arrivedPickUpPointBtnVisible.click();
            System.out.println("Attempt to drop-off point");
            WebElement arrivedDropOffPointBtnVisible = waitForVisibility(arrivedDropOffPointBtn);
            arrivedDropOffPointBtnVisible.click();
            System.out.println("Attempt to view order price to complete order");
            WebElement viewOrderPriceToCompleteOrderBtnVisible = waitForVisibility(viewOrderPriceToCompleteOrderBtn);
            viewOrderPriceToCompleteOrderBtnVisible.click();
            System.out.println("Attempt to complete order");
            WebElement completeOrderBtnVisible = waitForVisibility(completeOrderBtn);
            completeOrderBtnVisible.click();
            System.out.println("Attempt to continue pick up order");
            WebElement continuePickOrderBtnVisible = waitForVisibility(continuePickOrderBtn);
            continuePickOrderBtnVisible.click();
            return true;
        } catch (Exception e){
            System.out.println("Error completing order: " + e.getMessage());
            return false;
        }
    }
}
