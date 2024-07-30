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

    //passengers counter options(after received the id from developer)


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
                }
            }
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
