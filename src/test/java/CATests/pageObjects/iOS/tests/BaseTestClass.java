package CATests.pageObjects.iOS.tests;

import CATests.POM.iOS.delivery.PackageInfoPage;
import CATests.POM.iOS.delivery.PickUpToPage;
import CATests.pageObjects.iOS.tests.delivery.PickUpToPageTest;
import CATests.pageObjects.iOS.tests.transport.AddressPageTest;
import CATests.pageObjects.iOS.tests.transport.TimeAndVehiclePageTest;
import CATests.pageObjects.iOS.tests.transport.OrderDetailsPageTest;
import CATests.pageObjects.iOS.tests.transport.OrderSummaryPageTest;
import CATests.pageObjects.iOS.tests.delivery.PickUpFromPageTest;
import CATests.pageObjects.iOS.tests.delivery.PickUpTimePageTest;
import CATests.pageObjects.iOS.tests.delivery.PackageInfoPageTest;
import CATests.pageObjects.iOS.tests.delivery.PlacedOrderDeliveryPageTest;
import CATests.pageObjects.iOS.tests.transport.PlacedOrderTransportPageTest;
import DATests.pageObjects.iOS.tests.DABaseTestClass;

import CATests.utils.ConfigUpdater;
import CATests.utils.ConfigLoader;
import CATests.utils.ExcelReader;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.NoSuchSessionException;
import org.testng.annotations.Test;
import CATests.utils.ExtentManager;
import CATests.utils.GlobalState;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter;
import org.testng.annotations.*;

@Listeners(ExtentITestListenerAdapter.class)
public class BaseTestClass {
    private static final Logger logger = LogManager.getLogger(BaseTestClass.class);
    static IOSDriver driver;
    private ConfigLoader configLoader;
    private static final String EXCEL_FILE_PATH = "src/main/resources/testdata.xlsx";
    private ExtentReports extent;
    private ExtentTest test;
    public static String globalOrderID;
    public static String globalPickupCode;

    @BeforeSuite
    public void setupExtent()throws IOException{
        ExtentManager.clearReportDirectory();
        extent = ExtentManager.getInstance();
    }

    @AfterSuite
    public void tearDownExtent(){
        if(extent != null){
            extent.flush(); //reports are being completed
        }
    }

    @DataProvider(name = "testData")
    public Object[][] testData(){
        int totalRows = ExcelReader.getTotalRows(EXCEL_FILE_PATH);
        Object[][] data = new Object[totalRows][];

        for (int i = 1; i <= totalRows; i++){
            data[i - 1] = new Object[]{ExcelReader.getTestData(EXCEL_FILE_PATH, i)};
        }
        return data;
    }


    @BeforeMethod
    @Parameters("rowNumber")
    public void setup(@Optional("1") int rowNumber) throws MalformedURLException{
        Map<String, String> testData = ExcelReader.getTestData(EXCEL_FILE_PATH, rowNumber);
        ConfigUpdater.updateConfig(testData);

        // Initialize the class-level configLoader
        configLoader = new ConfigLoader();
        configLoader.reload();

//        //Create a new test node in the report
//        String testName = testData.get("TEST_CASE");
//        System.out.println("Test Name: " + testName);
//        test = extent.createTest(testName);
    }

    private void updateConfig(Map<String, String> testData){
        ConfigUpdater.updateConfig(testData);
    }

    @Test(dataProvider = "testData")
    public void runTests(Map<String, String> testData) throws MalformedURLException{
        //update config with test data
        updateConfig(testData);

        //Create a new test node in the report
        String testName = testData.get("TEST_CASE");
        System.out.println("Test Name: " + testName);
        test = extent.createTest(testName);


        //open the client application
        openClientMobileApp();

        configLoader.reload();

        //List of testCase with testData
        String orderType = configLoader.getProperty("ORDER_TYPE");
        System.out.println("The type of order is " + orderType);

        try{
            if(orderType.equalsIgnoreCase("T")){
                //LoginPage test case
                try{
                    test.info("Test LogIn Page");
                    System.out.println("Starting testLoginPage");
                    testLoginPage(testData);
                    System.out.println("Completed testLoginPage");
                    test.pass("testedLoginPage successfully");
                } catch (Exception e){
                    test.fail("LoginPage test failed: " + e.getMessage());
                }
                //HomePage test case of transport page
                try{
                    test.info("Test Transport Home Page");
                    System.out.println("Enter the HomePage");
                    testHomePage();
                    System.out.println("Clicked on the Transport button");
                    test.pass("Entered transport page successfully");
                } catch (Exception e){
                    test.fail("HomePage test failed: " + e.getMessage());
                }
                //AddressPage test case
                try{
                    test.info("Test Address Page");
                    System.out.println("Enter the AddressPage");
                    testAddressPage(testData);
                    test.pass("click the where from input address successfully");
                    test.pass("enter the from address text option successfully");
                    test.pass("Click the where to input address successfully");
                    test.pass("enter the to address text option successfully");
                    test.pass("click the next button successfully");
                    System.out.println("Completed testAddressPage");
                } catch (Exception e){
                    test.fail("AddressPage test failed: " + e.getMessage());
                }

                //TimeAndVehiclePage test case
                try{
                    test.info("Test TimeAndVehicle Page");
                    System.out.println("Enter the TimeAndVehicle Page");
                    testTimeAndVehiclePage(testData);
                    test.pass("select the pickup date successfully");
                    test.pass("select the pickup time successfully");
                    test.pass("choose the hourly rental option successfully");
                    test.pass("choose the vehicle type successfully");
                    test.info("finish testing the TimeAndVehicle Page");
                } catch (Exception e){
                    test.fail("TimeAndVehicle Page test failed: " + e.getMessage());
                }

                //OrderDetailsPage test case
                try{
                    test.info("Test OrderDetails Page");
                    System.out.println("Enter the OrderDetails Page");
                    testOrderDetailsPage(testData);
                    test.pass("select the cargo compensation button successfully");
                    test.pass("select the passenger compensation button successfully");
                    test.pass("select the amount of passengers successfully");
                    test.pass("select the amount of cart successfully");
                    test.pass("select the cart and driver options successfully");
                    test.pass("select the cross harbour tunnel option successfully");
                    test.pass("select the move door-to-door option successfully");
                    test.pass("select the transport or dispose waste option successfully");
                    test.pass("Fill in the contact information successfully");
                } catch (Exception e){
                    test.fail("OrderDetails Page test failed: " + e.getMessage());
                }

                //test the order summary page
                try{
                    test.info("Test the order summary page");
                    System.out.println("Enter the OrderSummary Page");
                    testOderSummaryPage(testData);
                    test.pass("select the tip options successfully");
                    test.pass("select the payment options successfully");
                    test.pass("place the order successfully");
                    System.out.println("Completed place order successfully");
                    //wait for the order summary being loaded
                    Thread.sleep(3000);
                } catch(Exception e){
                    test.fail("Order Summary Page test failed: " + e.getMessage());
                }

                //Placed Order Transport page
                try{
                    test.info("Test placed order transport page");
                    System.out.println("Enter the placedOrderTransport Page");
                    testPlacedOrderTransportPage(testData);
                    String cancelOrder = configLoader.getProperty("CANCEL_FLAG");
                    if(cancelOrder.equalsIgnoreCase("true")){
                        test.pass("Cancel order pressed");
                        test.pass("Cancel order successfully");
                    }
                    System.out.println("Completed placing transport order page testing");
                } catch (Exception e){
                    test.fail("Placed Transport Order Page test failed: " + e.getMessage());
                }

                //Reload properities to ensure the latest value is read
                configLoader.reload();
                String cancelFlag = configLoader.getProperty("CANCEL_FLAG");
                System.out.println("The cancel flag is " + cancelFlag);

                //check the cancel flag before running DA tests
                if(cancelFlag.equalsIgnoreCase("false")){
                    //Run DA tests
                    System.out.println("Start to run DA");
                    DABaseTestClass daBaseTest = new DABaseTestClass(driver, extent, test);
                    daBaseTest.runTestsWithOrderID(GlobalState.globalOrderID, GlobalState.globalPickUpCode);
//                    closeDriverApp();
                }
                //Mark the test as passed
                test.pass("All Tests passed successfully!");
            }
            else if (orderType.equalsIgnoreCase("D")) {
                //LoginPage test case
                try{
                    test.info("Test LogIn Page");
                    System.out.println("Starting testLoginPage");
                    testLoginPage(testData);
                    System.out.println("Completed testLoginPage");
                    test.pass("testedLoginPage successfully");
                } catch (Exception e){
                    test.fail("LoginPage test failed: " + e.getMessage());
                }
                //HomePage test case
                try{
                    test.info("Test Delivery Homepage Page");
                    System.out.println("Enter the HomePage");
                    testHomePage();
                    System.out.println("Clicked on the Delivery button");
                    test.pass("Entered delivery page successfully");
                } catch(Exception e){
                    test.fail("HomePage test failed: " + e.getMessage());
                }
                //PickupFromPage test case
                try{
                    test.info("Test Pickup From Page");
                    System.out.println("Enter the pickup from page");
                    testPickUpFromPage(testData);
                    System.out.println("Filled in the pickup order details");
                    test.pass("Filled the pickup information successfully");
                } catch (Exception e){
                    test.fail("PickupPage test failed: " + e.getMessage());
                }
                //PickupToPage test case
                try{
                    test.info("Test Pickup To Page");
                    System.out.println("Enter the pickup to page");
                    testPickUpToPage(testData);
                    System.out.println("Filled in the pickup order details");
                    test.pass("Filled the pickup information successfully");
                } catch (Exception e){
                    test.fail("PickupPage test failed: " + e.getMessage());
                }

                //PickupTimePage test case
                try{
                    test.info("Test Pickup Time Page");
                    System.out.println("Enter the pickup time page");
                    testPickupTimePage(testData);
                    System.out.println("Filled the pickupTime details");
                    test.pass("Filled the pickup date");
                    test.pass("Filled the pickup time");
                } catch (Exception e){
                    test.fail("PickupTimePage test failed: " + e.getMessage());
                }
                //PackageInfoPage test case
                try{
                    test.info("Test Pickup Info Page");
                    System.out.println("Enter the package info page");
                    testPackageInfoPage(testData);
                    System.out.println("Select the package content type");
                    test.pass("Selected the package content type successfully");
                } catch (Exception e){
                    test.fail("PackageInfoPage test failed: " + e.getMessage());
                }
                //Order Summary page
                try{
                    test.info("Test the order summary page");
                    System.out.println("Enter the OrderSummary Page");
                    testOderSummaryPage(testData);
                    test.pass("select the tip options successfully");
                    test.pass("select the payment options successfully");
                    test.pass("place the order successfully");
                    System.out.println("Completed place order successfully");
                    //wait for the order summary being loaded
                    Thread.sleep(3000);
                } catch(Exception e){
                    test.fail("Order Summary Page test failed: " + e.getMessage());
                }
                //Placed Order Delivery page
                try{
                    test.info("Test placed order delivery page");
                    System.out.println("Enter the PlacedDelivery Page");
                    testPlacedOrderDeliveryPage(testData);
                    test.pass("get the order ID successfully");
                    test.pass("get the pickup code successfully");
                    String cancelOrder = configLoader.getProperty("CANCEL_FLAG");
                    if(cancelOrder.equalsIgnoreCase("true")){
                        test.pass("Cancel order pressed");
                        test.pass("Cancel order successfully");
                    }
                    System.out.println("Completed delivery page testing");
                } catch (Exception e){
                    test.fail("Placed Order Delivery Page test failed: " + e.getMessage());
                }

                //Reload properities to ensure the latest value is read
                configLoader.reload();
                String cancelFlag = configLoader.getProperty("CANCEL_FLAG");
                System.out.println("The cancel flag is " + cancelFlag);

                //check the cancel flag before running DA tests
                if(cancelFlag.equalsIgnoreCase("false")){
                    //Run DA tests
                    System.out.println("Start to run DA");
                    DABaseTestClass daBaseTest = new DABaseTestClass(driver, extent, test);
                    daBaseTest.runTestsWithOrderID(GlobalState.globalOrderID, GlobalState.globalPickUpCode);
//                    closeDriverApp();
                }

            }
        } catch (Exception e){
            //tests as failed
            test.fail("Test failed: " + e.getMessage());
        } finally{
            //close the app and switch back to client app
        }

    }

    public void openClientMobileApp() throws MalformedURLException{
        // Set desired capabilities for IOS driver
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 14 Pro")
                .setPlatformVersion("16.4")
                .setAutomationName("XCUITest")
                .setBundleId("hk.gogovan.GoGoVanClient.staging");

        URL url = new URL("http://127.0.0.1:4723/");
        driver = new IOSDriver(url,options);
        System.out.println("iOS client application started!");

        try {
            // Wait for 2 seconds before starting the test
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void closeDriverApp(){
        if(driver != null){
            try{
                // Send the driver app to the background
                driver.runAppInBackground(Duration.ofSeconds(-1));

                //Wait for a short period to ensure the app is in the background
                Thread.sleep(2000);

                //Switch the customer app to the foreground
                driver.activateApp("hk.gogovan.GoGoVanClient.staging");

                //wait for the order rating page
                Thread.sleep(8000);
                System.out.println("Switched back to the customer app");
            } catch (NoSuchSessionException e) {
                System.err.println("Session is terminated or not started: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error while switching apps: " + e.getMessage());
            } finally {
                try {
                    // Quit the driver to end the session
                    driver.quit();
                    System.out.println("Driver session ended.");
                } catch (Exception e) {
                    System.err.println("Error while quitting the driver: " + e.getMessage());
                }
            }
        }
    }

    private void testLoginPage(Map<String, String> testData){
        LoginPageTest loginPageTest = new LoginPageTest(driver);
        loginPageTest.testAutomateTheLoginPage();
    }

    private void testHomePage(){
        HomePageTest homePageTest = new HomePageTest(driver);
        homePageTest.testAutomateTheHomePage();
    }

    //delivery order pages
    private void testPickUpFromPage(Map<String, String> testData){
        PickUpFromPageTest pickUpFromPageTest = new PickUpFromPageTest(driver);
        pickUpFromPageTest.testPickUpFromPage();
    }

    private void testPickUpToPage(Map<String, String> testDataa){
        PickUpToPageTest pickUpToPageTest = new PickUpToPageTest(driver);
        pickUpToPageTest.testPickUpToPage();
    }

    private void testPickupTimePage(Map<String, String> testData){
        PickUpTimePageTest pickUpTimePageTest = new PickUpTimePageTest(driver);
        pickUpTimePageTest.testPickUpTimePage();
    }

    private void testPackageInfoPage(Map<String,String> testData){
        PackageInfoPageTest packageInfoPageTest = new PackageInfoPageTest(driver);
        packageInfoPageTest.testPackageInfoPage();
    }

    private void testPlacedOrderDeliveryPage(Map<String, String> testData){
        PlacedOrderDeliveryPageTest placedOrderDeliveryPageTest = new PlacedOrderDeliveryPageTest(driver);
        placedOrderDeliveryPageTest.testPlacedOrderDeliveryPage();
    }

    //transport order page
    private void testAddressPage(Map<String, String> testData){
        AddressPageTest addressPageTest = new AddressPageTest(driver);
        addressPageTest.testAutomateTheAddressPage();
    }

    private void testTimeAndVehiclePage(Map<String, String> testData){
        TimeAndVehiclePageTest timeAndVehiclePageTest = new TimeAndVehiclePageTest(driver);
        timeAndVehiclePageTest.testAutomateTheTimeAndVehiclePage();
    }

    private void testOrderDetailsPage(Map<String, String> testData){
        OrderDetailsPageTest orderDetailsPageTest = new OrderDetailsPageTest(driver);
        orderDetailsPageTest.testAutomateTheOrderDetailsPage();
    }

    private void testOderSummaryPage(Map<String, String> testData){
        OrderSummaryPageTest orderSummaryPageTest = new OrderSummaryPageTest(driver);
        orderSummaryPageTest.testAutomateTheOrderSummaryPage();
    }

    private void testPlacedOrderTransportPage(Map<String, String> testData){
        PlacedOrderTransportPageTest placedOrderTransportPageTest = new PlacedOrderTransportPageTest(driver);
        placedOrderTransportPageTest.testPlacedOrderTransportPage();
    }
}