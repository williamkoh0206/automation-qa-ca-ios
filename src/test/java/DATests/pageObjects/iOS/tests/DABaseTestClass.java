package DATests.pageObjects.iOS.tests;

import CATests.POM.iOS.DATests.LoginPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Map;

import CATests.utils.*;
import DATests.pageObjects.iOS.tests.LoginPageTest;
import DATests.pageObjects.iOS.tests.LandingPageTest;

import org.openqa.selenium.NoSuchSessionException;


public class DABaseTestClass {
    private IOSDriver driver;
    private ExtentReports extent;
    private ExtentTest test;

    private ConfigLoader configLoader;

    public DABaseTestClass(IOSDriver driver, ExtentReports extent, ExtentTest test) {
        this.driver = driver;
        this.extent = extent;
        this.test = test;
        configLoader = new ConfigLoader();
    }

    //a method to run tests with Order ID
    public void runTestsWithOrderID(String orderID, String pickupCode) throws MalformedURLException{
        try{
            //open the driver app
            switchToDriverApp();
            test.info("Switched to DA app");
            test.pass("Switched to DA app successfully");

            //Reload properities to ensure latest value is ready
            configLoader.reload();
            String orderType = configLoader.getProperty("ORDER_TYPE");
            System.out.println("The type of order is " + orderType);

            //Login DA
            try{
                test.info("DA login page");
                testAutomateTheDALoginPage();
                test.pass("Entered login successfully");
            } catch (Exception e){
                test.fail("Test failed: " + e.getMessage());
            }

            //check the transport order
            if(orderType.equalsIgnoreCase("T")){
                try{
                    test.info("DA Landing Page");
                    testAutomatedTheDALandingPage();
                    test.pass("Clicked the ASAP sorting option successfully");
                    test.pass("Select the latest option successfully");
                    test.pass("Pickup the order successfully");
                    test.pass("complete the order successfully");
                } catch (Exception e){
                    test.fail("Test failed: " + e.getMessage());
                }
            }
            //check the delivery order
            else if(orderType.equalsIgnoreCase("D")){
                try{
                    test.info("DA Delivery Page");
                    testAutomatedTheDALandingDeliveryPage();
                    test.pass("Clicked the delivery option");
                    test.pass("Picked the delivery order");
                } catch (Exception e){
                    test.fail("Test failed " + e.getMessage());
                }
            }
            //Logout page
//            try{
//                test.info("DA Logout Page");
//                testAutomateTheLogoutPage();
//                test.pass("Clicked logout successfully");
//            } catch (Exception e){
//                test.fail("Test failed: " + e.getMessage());
//            }
            test.pass("Tested pass successfully");

        } catch (Exception e){
            //Mark the tests as failed
            test.fail("Test failed: " + e.getMessage());
        } finally {
            //close the app and switch back to the client app
            switchBackToClientApp();
            System.out.println("in the finally block and going back to CA");
        }
    }

    public void switchToDriverApp(){
        if (driver != null){
            try{
                //Activate the driver app
                driver.activateApp("hk.gogovan.GoGoDriver.staging");
                Thread.sleep(10000);
                System.out.println("Switched to the driver app successfully");
                } catch (NoSuchSessionException e) {
                    System.err.println("Session is terminated or not started: " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("Error while switching apps: " + e.getMessage());
                }
        }
    }

    public void switchBackToClientApp(){
        if (driver != null){
            try{
                //Terminate the driver app
                System.out.println("terminating the driver app");
                driver.terminateApp("hk.gogovan.GoGoDriver.staging");

                //Activate the CA app
                driver.activateApp("hk.gogovan.GoGoVanClient.staging");
                System.out.println("Switched back to the client app");

                //wait for 5 seconds to further check
                Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.err.println("Thread was interrupted: " + e.getMessage());
                    Thread.currentThread().interrupt(); // Preserve interrupt status
                }
        }
    }

    private void testAutomateTheDALoginPage(){
        LoginPageTest loginPageTest = new LoginPageTest(driver);
        loginPageTest.testAutomateTheDALoginPage();
    }

    private void testAutomatedTheDALandingPage(){
        LandingPageTest landingPageTest = new LandingPageTest(driver);
        landingPageTest.testAutomatedTheDALandingPage();
    }

    private void testAutomatedTheDALandingDeliveryPage(){
        LandingDeliveryPageTest landingDeliveryPageTest = new LandingDeliveryPageTest(driver);
        landingDeliveryPageTest.testAutomatedTheDALandingDeliveryPage();
    }

    private void testAutomateTheLogoutPage(){
        LogoutPageTest logoutPageTest = new LogoutPageTest(driver);
        logoutPageTest.testAutomateTheDALogoutPage();
    }
}
