package CATests.POM.iOS.transport;
import CATests.POM.iOS.AbstractPageClass;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import CATests.utils.ConfigLoader;

public class AddressPage extends AbstractPageClass {
    // call the configloader to get the values we want to input
    private ConfigLoader configLoader;

    public AddressPage(IOSDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.configLoader = new ConfigLoader();
    }

    //List of elements locator

    //"where from" button
    @iOSXCUITFindBy(accessibility = "Where from?")
    private WebElement whereFromButton;

    //enter address for where form input field
    @iOSXCUITFindBy(accessibility = "Where from?")
    private WebElement whereFromInputField;

    //select the first address text option
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeTable)[2]//XCUIElementTypeCell[1]")
    private WebElement firstAddressTextOption;

    //show the first address static text
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeTable)[2]//XCUIElementTypeCell[1]//XCUIElementTypeStaticText[1]")
    private WebElement firstAddressStaticText;

    //"where to" button
    @iOSXCUITFindBy(accessibility = "Where to?")
    private WebElement whereToButton;

    //input the address field box
    @iOSXCUITFindBy(className = "XCUIElementTypeTextView")
    private WebElement InputFieldBox;

    //next button
    @iOSXCUITFindBy(accessibility = "Next")
    private WebElement nextButton;

    //swipe address button
    @iOSXCUITFindBy(accessibility = "reverse")
    private WebElement swapAddressButton;

    //add stop routes
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Add stop\"]")
    private WebElement addStopOptionsBtn;

    public boolean clickWhereFromBtn(){
        try{
            String accountType = configLoader.getProperty("ACCOUNT_TYPE");
            if(accountType.equalsIgnoreCase("Personal")){
                WebElement whereFromButtonVisible = waitForVisibility(whereFromButton);
                whereFromButtonVisible.click();
            } else if (accountType.equalsIgnoreCase("Business")){
                //Wait 2 seconds for the element to be visible
                Thread.sleep(2000);
                WebElement whereFromButtonVisible = waitForVisibility(whereFromButton);
                whereFromButtonVisible.click();
            }
            return true;
        }catch (Exception e){
            System.out.println("Error clicking the where from button: " + e.getMessage());
            return false;
        }
    }

    public boolean enterAddressFromInput(){
        try{
            WebElement enterAddressInputVisible = waitForVisibility(whereFromInputField);
            Thread.sleep(3000);
            enterAddressInputVisible.click();
            String fromAddress = configLoader.getProperty("FROM_ADDRESS");
            whereFromInputField.sendKeys(fromAddress);
            //press the enter button
            Actions actions = new Actions(driver);
            actions.sendKeys(whereFromInputField, Keys.RETURN).perform();
            return true;
        } catch (Exception e){
            System.out.println("Error entering address into the where from input field: " + e.getMessage());
            return false;
        }
    }

    public String getFirstAddressTextOption() {
        try {
            // Wait for the element to be visible
            WebElement dynamicTextOptionVisible = waitForVisibility(firstAddressStaticText);
            return dynamicTextOptionVisible.getText();
        } catch (Exception e) {
            System.out.println("Error retrieving the text from the first address option: " + e.getMessage());
            return null;
        }
    }

    public boolean clickFirstAddressTextOption(){
        try{
            Thread.sleep(2000);
            boolean clickFirstOption = clickIfVisible(firstAddressTextOption, 3);
            if (clickFirstOption){
                return true;
            }
            Thread.sleep(2000);
            firstAddressTextOption.click();
            return true;
        } catch (Exception e){
            System.out.println("Error clicking the first address option: " + e.getMessage());
            return false;
        }
    }

    public boolean clickWhereToBtn(){
        try{
            whereToButton.click();
            return true;
        }catch (Exception e){
            System.out.println("Error clicking the where to button: " + e.getMessage());
            return false;
        }
    }

    public boolean enterAddressToInput(){
        try{
            WebElement enterAddressInputVisible = waitForVisibility(InputFieldBox);
            Thread.sleep(3000);
            enterAddressInputVisible.click();
            String toAddress = configLoader.getProperty("TO_ADDRESS");
            InputFieldBox.sendKeys(toAddress);
            //press the enter button
            Actions actions = new Actions(driver);
            actions.sendKeys(InputFieldBox, Keys.RETURN).perform();
            return true;
        } catch (Exception e){
            System.out.println("Error entering address into the where from input field: " + e.getMessage());
            return false;
        }
    }

    public boolean clickNextBtn(){
        try{
            WebElement nextButtonVisible = waitForVisibility(nextButton);
            nextButtonVisible.click();
            return true;
        }catch (Exception e){
            System.out.println("Error clicking the next button: " + e.getMessage());
            return false;
        }
    }

    public boolean clickSwipeBtn(){
        try{
            String swapFlag = configLoader.getProperty("SWAP");
            //By default, the swipe button is not clicked:false
            if (swapFlag.equalsIgnoreCase("false")){
                return true;
            }
            WebElement swapAddressButtonVisible = waitForVisibility(swapAddressButton);
            swapAddressButtonVisible.click();
            return true;
        }catch (Exception e){
            System.out.println("Error clicking the swipe button: " + e.getMessage());
            return false;
        }
    }

    public boolean addStopBtn(){
        try{
            String stopFlag = configLoader.getProperty("ADD_STOP");
            //by default, the add stop columns button is not clicked: false
            if (stopFlag.equalsIgnoreCase("false")){
                return true;
            }
            WebElement addStopOptionsBtnVisible = waitForVisibility(addStopOptionsBtn);
            addStopOptionsBtnVisible.click();
            WebElement buttonAddStopWhereToVisible = waitForVisibility(whereToButton);
            buttonAddStopWhereToVisible.click();
            WebElement inputAddStopVisible = waitForVisibility(InputFieldBox);
            String stopAddress = configLoader.getProperty("STOP_ADDRESS");
            inputAddStopVisible.sendKeys(stopAddress);
            WebElement buttonFirstOptionAddStopVisible = waitForVisibility(firstAddressTextOption);
            buttonFirstOptionAddStopVisible.click();
            return true;
        }catch (Exception e){
            System.out.println("Error clicking the add stop button: " + e.getMessage());
            return false;
        }
    }

}
