package CATests.POM.iOS.transport;

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
import java.time.DayOfWeek;

public class TimeAndVehiclePage extends AbstractPageClass{
    // call the configloader to get the values we want to input
    private ConfigLoader configLoader;

    public TimeAndVehiclePage(IOSDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.configLoader = new ConfigLoader();
    }

    //List of the elements
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther")
    private WebElement dateTimeBtn;

    //date options selector
    @iOSXCUITFindBy(xpath = "//XCUIElementTypePickerWheel[1]")
    private WebElement dateOptionSelector;

    //time options selector
    @iOSXCUITFindBy(xpath = "//XCUIElementTypePickerWheel[2]")
    private WebElement timeOptionSelector;

    //confirm date and time ok button
    @iOSXCUITFindBy(accessibility = "OK")
    private WebElement dateTimeOkBtn;

    //click the hourly rental button
    @iOSXCUITFindBy(accessibility = "Hourly rental")
    private WebElement hourlyRentalBtn;

    //click the hourly rental options
    @iOSXCUITFindBy(className = "XCUIElementTypePickerWheel")
    private WebElement hourlyRentalOptions;

    //click on van option
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[2]")
    private WebElement vanOption;

    //click on premium van option
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[3]")
    private WebElement premiumVanOption;

    //click on the 5.5 truck option
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[4]")
    private WebElement fivePointFiveTruckOption;

    //click on the 9 truck option
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[5]")
    private WebElement nineTruckOption;

    //click on the next button
    @iOSXCUITFindBy(accessibility = "Next")
    private WebElement nextButton;


    //click on the date and time button
    public boolean clickDateTimeBtn(){
        try{
            WebElement dateTimeBtnVisible = waitForVisibility(dateTimeBtn);
            dateTimeBtnVisible.click();
            return true;
        } catch (Exception e){
            System.out.println("Error clicking the date and time button: " + e.getMessage());
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

            //there are total of 120 options for the time, each 10 minutes
            for (int i = 0; i < 120; i++){
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

    //click on the hourly rental button
    public boolean clickHourlyRentalBtn(){
        try{
            WebElement hourlyRentalBtnVisible = waitForVisibility(hourlyRentalBtn);
            hourlyRentalBtnVisible.click();
            return true;
        }
        catch (Exception e){
            System.out.println("Error clicking the hourly rental button: " + e.getMessage());
            return false;
        }
    }

    //select the hourly rental time
    public boolean selectHourlyRentalTime(){
        try{
            String hourlyRentalTime = configLoader.getProperty("HOURLY_RENTAL");
            if(hourlyRentalTime.equalsIgnoreCase("No hourly rental")){
                return true;
            }

            //there are total of 18 rental hours options for the users to select
            for(int i = 0; i< 18; i++){
                WebElement hourlyRentalTimeVisible = waitForVisibility(hourlyRentalOptions);
                System.out.println("Iterate searching the " + hourlyRentalTime);
                hourlyRentalTimeVisible.sendKeys(hourlyRentalTime + " hours");
                System.out.println("click the selected hourly rental time!");
                break;
            }
            return true;
        } catch (Exception e){
            System.out.println("Error selecting the hourly rental time: " + e.getMessage());
            return false;
        }
    }

    //select the vehicle type
    public boolean selectVehicleOptions(){
        try{
            String vehicleType = configLoader.getProperty("VEHICLE_TYPE");
            if(vehicleType.equalsIgnoreCase("Van")){
                WebElement buttonVanVisible = waitForVisibility(vanOption);
                buttonVanVisible.click();
                return true;
            }
            else if (vehicleType.equalsIgnoreCase("Premium Van")){
                WebElement buttonPremiumVanVisible = waitForVisibility(premiumVanOption);
                buttonPremiumVanVisible.click();
                return true;
            }
            else if (vehicleType.equalsIgnoreCase("5.5t Truck")){
                WebElement buttonFivePointFiveTruckVisible = waitForVisibility(fivePointFiveTruckOption);
                buttonFivePointFiveTruckVisible.click();
                return true;
            }
            else if (vehicleType.equalsIgnoreCase("9t Truck")){
                WebElement buttonNineTruckVisible = waitForVisibility(nineTruckOption);
                buttonNineTruckVisible.click();
                return true;
            }
            else{
                System.out.println("Wrong vehicle type");
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //click on the next button
    public boolean clickNextBtn(){
        try{
            WebElement nextButtonVisible = waitForVisibility(nextButton);
            nextButtonVisible.click();
            return true;
        } catch (Exception e){
            System.out.println("Error clicking the next button: " + e.getMessage());
            return false;
        }
    }
}
