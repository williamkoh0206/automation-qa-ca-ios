package CATests.pageObjects.iOS.tests;

import CATests.pageObjects.iOS.tests.transport.AddressPageTest;
import CATests.pageObjects.iOS.tests.transport.TimeAndVehiclePageTest;
import CATests.pageObjects.iOS.tests.transport.OrderDetailsPageTest;
import CATests.utils.ConfigUpdater;
import CATests.utils.ConfigLoader;
import CATests.utils.ExcelReader;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.testng.annotations.Test;
import CATests.utils.ExtentManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
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

        //Create a new test node in the report
        String testName = testData.get("TEST_CASE");
        test = extent.createTest(testName);
    }

    private void updateConfig(Map<String, String> testData){
        ConfigUpdater.updateConfig(testData);
    }

    @Test(dataProvider = "testData")
    public void runTests(Map<String, String> testData) throws MalformedURLException{
        //update config with test data
        updateConfig(testData);

        //open the client application
        openClientMobileApp();

        try{
            configLoader.reload();

            //List of testCase with testData

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
                test.info("Test Home Page");
                System.out.println("Enter the HomePage");
                testHomePage();
                System.out.println("Clicked on the Transport button");
                test.pass("testedTransportPage successfully");
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
            } catch (Exception e){
                test.fail("OrderDetails Page test failed: " + e.getMessage());
            }

            //Mark the test as passed
            test.pass("All Tests passed successfully!");

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

    private void testLoginPage(Map<String, String> testData){
        LoginPageTest loginPageTest = new LoginPageTest(driver);
        loginPageTest.testAutomateTheLoginPage();
    }

    private void testHomePage(){
        HomePageTest homePageTest = new HomePageTest(driver);
        homePageTest.testAutomateTheHomePage();
    }

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
}