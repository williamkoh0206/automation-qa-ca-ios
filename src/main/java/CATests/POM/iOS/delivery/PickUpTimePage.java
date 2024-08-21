package CATests.POM.iOS.delivery;

import CATests.POM.iOS.AbstractPageClass;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import CATests.utils.ConfigLoader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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

    //date options selector
    @iOSXCUITFindBy(xpath = "//XCUIElementTypePickerWheel[1]")
    private WebElement dateOptionSelector;

    //time options selector
    @iOSXCUITFindBy(xpath = "//XCUIElementTypePickerWheel[2]")
    private WebElement timeOptionSelector;

    //confirm date and time ok button
    @iOSXCUITFindBy(accessibility = "OK")
    private WebElement dateTimeOkBtn;

    //instant delivery option
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@value='Instant delivery']/preceding::XCUIElementTypeButton")
    private WebElement instantDeliveryOption;

    //4-hour option
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@value,'hour')]/following::XCUIElementTypeButton[1]")
    private WebElement fourHourOption;

    //6pm option
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@value,'By 6 PM')]/following::XCUIElementTypeButton[1]")
    private WebElement sixPmOption;

    //6-10pm option
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@value,'6-10 PM')]/following::XCUIElementTypeButton")
    private WebElement sixToTenPmOption;

    //next button
    @iOSXCUITFindBy(accessibility = "Next")
    private WebElement nextBtn;

    //click the pick-up time button
    public boolean clickPickUpTimeOption(){
        try{
            //click the pickup time option view
            WebElement pickUpTimeViewOptionVisibility = waitForVisibility(pickUpTimeViewOption);
            pickUpTimeViewOptionVisibility.click();
            return true;
        } catch (Exception e){
            System.out.println("Error clicking the pickup time button: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    //select the pickup date
    public boolean clickPickUpDate(){
        try{
            String startPickUpDate = configLoader.getProperty("CURRENT_DATE");
            String endPickUpDate = configLoader.getProperty("ORDER_END_DATE");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
            int currentYear = LocalDate.now().getYear();
            if (startPickUpDate.equals("Today")){
                startPickUpDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM")) + " " + currentYear;
            } else if(startPickUpDate.equals("Tomorrow")){
                startPickUpDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM")) + " " + currentYear;
            } else{
                //required format will be dd MMM (e.g 01 Jan)
                startPickUpDate = startPickUpDate.replaceAll(".*?(\\d{1,2} \\w{3}).*", "$1") + " " + currentYear;
            }
            if (endPickUpDate.equals("Today")){
                endPickUpDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM")) + " " + currentYear;
            } else if(endPickUpDate.equals("Tomorrow")){
                endPickUpDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM")) + " " + currentYear;
            } else{
                //required format will be dd MMM (e.g 01 Jan)
                endPickUpDate = endPickUpDate.replaceAll(".*?(\\d{1,2} \\w{3}).*", "$1") + " " + currentYear;
            }
            LocalDate startDate = LocalDate.parse(startPickUpDate, formatter);
            LocalDate endDate = LocalDate.parse(endPickUpDate, formatter);
            String endPickUpDateOfWeek = endDate.getDayOfWeek().name().substring(0,1).toUpperCase() + endDate.getDayOfWeek().name().substring(1,3).toLowerCase();
            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

            // Print the start and end dates to the terminal
            System.out.println("Initial start pickup date: " + startPickUpDate);
            System.out.println("Initial end pickup date and week: " + endPickUpDateOfWeek + " " + endPickUpDate);
            System.out.println("Excel Start Date: " + startDate);
            System.out.println("Excel End Date: " + endDate);
            System.out.println("Excel Days Between: " + daysBetween);

            if (daysBetween == 0){ //if it is same day order, no changes
                return true;
            }

            for(int i = 0; i < daysBetween; i++){
                System.out.println("Iteration dates: " + i);
                if (i >= 0){
                    WebElement dateOptionsVisibility = waitForVisibility(dateOptionSelector);
                    String dateToSelect = startDate.plusDays(i + 1).format((DateTimeFormatter.ofPattern("dd MMM")));
                    System.out.println("Iterate the dates: " + endPickUpDateOfWeek + " " + dateToSelect);
                    if(daysBetween == 1){
                        //send Tomorrow keyword to select the date
                        dateOptionsVisibility.sendKeys("Tomorrow " + dateToSelect);
                    }else{
                        dateOptionsVisibility.sendKeys(endPickUpDateOfWeek + " " + dateToSelect);
                        System.out.println("click the selected date: " + dateToSelect);
                    }
                }
            }
            return true;
        } catch (Exception e){
            System.out.println("Error clicking the button of the selected date: " + e.getMessage());
            return false;
        }
    }

    //select the pickup time
    public boolean clickPickUpTime(){
        try{
            String selectedTime = configLoader.getProperty("TIME").replaceAll("[^a-zA-Z0-9: ]", "").trim();
            if("ASAP".equalsIgnoreCase(selectedTime)){
                return true;
            }

            //there are total of 84 options for the time, each 10 minutes
            for (int i = 0; i < 84; i++){
                WebElement timeOptionsVisibility = waitForVisibility(timeOptionSelector);
                System.out.println("Iterate searching the " + selectedTime);
                timeOptionsVisibility.sendKeys(selectedTime);
                System.out.println("click the selected time!");
                break;
            }
            return true;
        }
        catch (Exception e){
            System.out.println("Error clicking the button of the selected time: " + e.getMessage());
            return false;
        }
    }

    //click ok button to confirm the pickup date and time
    public boolean clickOkConfirmBtn(){
        try{
            WebElement okButtonVisible = waitForVisibility(dateTimeOkBtn);
            okButtonVisible.click();
            return true;
        }
        catch (Exception e){
            System.out.println("Error clicking the ok button: " + e.getMessage());
            return false;
        }
    }

    //click drop-off time option
    public boolean clickDropOffTimeOption(){
        try{
            String dropTime = configLoader.getProperty("DROP_OFF_TIME");
            if(dropTime.equalsIgnoreCase("Instant")){
                WebElement instantDropTimeVisibility = waitForVisibility(instantDeliveryOption);
                instantDropTimeVisibility.click();
                return true;
            } else if (dropTime.equalsIgnoreCase("4 hour")) {
                WebElement fourHourDropTimeVisibility = waitForVisibility(fourHourOption);
                fourHourDropTimeVisibility.click();
                return true;
            } else if(dropTime.equalsIgnoreCase("6pm")){
                WebElement sixPmDropTimeVisibility = waitForVisibility(sixPmOption);
                sixPmDropTimeVisibility.click();
                return true;
            } else if (dropTime.equalsIgnoreCase("6-10pm")) {
                WebElement sixToTenPmDropTimeVisibility = waitForVisibility(sixToTenPmOption);
                sixToTenPmDropTimeVisibility.click();
                return true;
            }
            return true;
        } catch (Exception e){
            System.out.println("Error clicking the button of the selected time: " + e.getMessage());
            return false;
        }
    }

    //click next button
    public boolean clickNextBtn(){
        try{
            WebElement nextButtonVisible = waitForVisibility(nextBtn);
            nextButtonVisible.click();
            return true;
        }catch (Exception e){
            System.out.println("Error clicking the next button: " + e.getMessage());
            return false;
        }
    }
}
