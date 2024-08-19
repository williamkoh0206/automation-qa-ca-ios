package CATests.POM.iOS.DATests;

import CATests.POM.iOS.AbstractPageClass;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import CATests.utils.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogoutPage extends AbstractPageClass{

    //call the configLoader to get the values for input
    private ConfigLoader configLoader;

    public LogoutPage(IOSDriver driver){
        super (driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.configLoader = new ConfigLoader();
    }

    //list of elements
    @iOSXCUITFindBy(accessibility = "menu")
    private WebElement sideMenuIcon;

    @iOSXCUITFindBy(accessibility = "Settings")
    private WebElement settingsButton;

    @iOSXCUITFindBy(accessibility = "Logout")
    private WebElement logoutButton;

    public boolean openSideMenu(){
        try{
            WebElement buttonSideMenuVisible = waitForVisibility(sideMenuIcon);
            buttonSideMenuVisible.click();
            return true;
        } catch (Exception e){
            System.out.println("Error opening the sidebar menu: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean goToSettings(){
        try{
            WebElement settingsButtonVisible = waitForVisibility(settingsButton);
            settingsButtonVisible.click();
            return true;
        } catch (Exception e){
            System.out.println("Error opening the settings page: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean logout(){
        try{
            WebElement logoutButtonVisible = waitForVisibility(logoutButton);
            logoutButtonVisible.click();
            return true;
        } catch (Exception e){
            System.out.println("Error logging out: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
