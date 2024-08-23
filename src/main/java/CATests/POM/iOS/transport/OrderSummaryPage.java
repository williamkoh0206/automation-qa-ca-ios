package CATests.POM.iOS.transport;

import CATests.POM.iOS.AbstractPageClass;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import CATests.utils.ConfigLoader;
import CATests.utils.JavaScriptHelper;

import java.sql.SQLOutput;
import java.util.HashMap;

public class OrderSummaryPage extends AbstractPageClass{
    // call the configloader to get the values we want to input
    private ConfigLoader configLoader;

    // call the javascript helper to scroll
    protected JavaScriptHelper jsHelper;

    public OrderSummaryPage(IOSDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.configLoader = new ConfigLoader();
    }

    //list of elements
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@value='No tip']/following::XCUIElementTypeStaticText[@value='$20'][1]")
    private WebElement twentyDollarTipsOption;

    @iOSXCUITFindBy(accessibility = "$20")
    private WebElement twentyDollarTipsForDelivery;

    @iOSXCUITFindBy(accessibility = "$30")
    private WebElement thirtyDollarTipsOption;

    @iOSXCUITFindBy(accessibility = "$40")
    private WebElement fortyDollarTipsOption;

    @iOSXCUITFindBy(accessibility = "$50")
    private WebElement fiftyDollarTipsOption;

    @iOSXCUITFindBy(accessibility = "Coupon")
    private WebElement couponOption;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]")
    private WebElement couponCode;

    @iOSXCUITFindBy(accessibility = "Apply")
    private WebElement applyCouponBtn;

    @iOSXCUITFindBy(accessibility = "Personal")
    private WebElement selectPersonalAccountPaymentOption;

    @iOSXCUITFindBy(accessibility = "Business")
    private WebElement selectBusinessAccountPaymentOption;

    @iOSXCUITFindBy(accessibility = "You can pay with any card added to this account or change account and payment methods")
    private WebElement creditCardPaymentOptionPopUp;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Cash']/following::XCUIElementTypeButton[1]")
    private WebElement cashOption;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@value, 'Visa')]/preceding::XCUIElementTypeButton[1]")
    private WebElement creditCardOption;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@value, 'Faster Payment System')]/preceding::XCUIElementTypeButton[1]")
    private WebElement fpsOption;

    @iOSXCUITFindBy(accessibility = "CANCEL_BUTTON")
    private WebElement fpsPopUp;

    @iOSXCUITFindBy(accessibility = "back")
    private WebElement afterFpsPopUpBackBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@value, 'Pay by recipient')]/following::XCUIElementTypeButton[1]")
    private WebElement payByRecipientOption;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@value, 'Wallet')]/preceding::XCUIElementTypeButton[1]")
    private WebElement walletOption;

    @iOSXCUITFindBy(accessibility = "ggv__order_summary__footer__button__place_order")
    private WebElement placeOrderBtn;

    public boolean selectTipsOption(){
        try{
            String tipAmount = configLoader.getProperty("TIP");
            if(tipAmount.equalsIgnoreCase("No tip")){
                return true;
            }
            else if(tipAmount.equalsIgnoreCase("20")){
                WebElement twentyDollarTipsVisible = waitForVisibility(twentyDollarTipsOption);
                twentyDollarTipsVisible.click();
            }
            else if(tipAmount.equalsIgnoreCase("30")){
                WebElement thirtyDollarTipsVisible = waitForVisibility(thirtyDollarTipsOption);
                thirtyDollarTipsVisible.click();
            }
            else if(tipAmount.equalsIgnoreCase("40")){
                WebElement fortyDollarTipsVisible = waitForVisibility(fortyDollarTipsOption);
                fortyDollarTipsVisible.click();
            }
            else if(tipAmount.equalsIgnoreCase("50")){
                WebElement fiftyDollarTipsVisible = waitForVisibility(fiftyDollarTipsOption);
                fiftyDollarTipsVisible.click();
            }
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean applyCoupon(){
        try{
            String couponFlag = configLoader.getProperty("COUPON");
            String orderType = configLoader.getProperty("ORDER_TYPE");
            if(couponFlag.equalsIgnoreCase("false")){
                return true;
            }
            else if(orderType.equalsIgnoreCase("T")){
                WebElement couponOptionVisible = waitForVisibility(couponOption);
                couponOptionVisible.click();
                WebElement couponCodeVisible = waitForVisibility(couponCode);
                couponCodeVisible.click();
                WebElement applyCouponVisible = waitForVisibility(applyCouponBtn);
                applyCouponVisible.click();
                return true;
            }
            else if(orderType.equalsIgnoreCase("D"))
            {
                WebElement couponOptionVisible = waitForVisibilityWithScroll(couponOption,"Coupon");
                couponOptionVisible.click();
                WebElement couponCodeVisible = waitForVisibility(couponCode);
                couponCodeVisible.click();
                WebElement applyCouponVisible = waitForVisibility(applyCouponBtn);
                applyCouponVisible.click();
                return true;
            }
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean selectPaymentMethod(){
        try{
            String accountType = configLoader.getProperty("ACCOUNT_TYPE");
            if(accountType.equalsIgnoreCase("personal")){
                WebElement selectPersonalPaymentOptionVisible = waitForVisibility(selectPersonalAccountPaymentOption);
                selectPersonalPaymentOptionVisible.click();
            }
            else if(accountType.equalsIgnoreCase("business")){
                WebElement selectBusinessPaymentOptionVisible = waitForVisibility(selectBusinessAccountPaymentOption);
                selectBusinessPaymentOptionVisible.click();
            }
            String paymentMethod = configLoader.getProperty("PAYMENT_METHOD");

            //In case if the payment card popup comes out
            boolean creditCardPaymentOptionPopUpVisible = clickIfVisible(creditCardPaymentOptionPopUp,2 );
            if (creditCardPaymentOptionPopUpVisible) {
                if (paymentMethod.equalsIgnoreCase("Cash")) {
                    WebElement cashOptionVisible = waitForVisibility(cashOption);
                    cashOptionVisible.click();
                } else if (paymentMethod.equalsIgnoreCase("FPS")) {
                    WebElement fpsOptionVisible = waitForVisibility(fpsOption);
                    fpsOptionVisible.click();
                    // Check if FPS popup occurs
                    clickIfVisible(fpsPopUp, 2);
                    clickIfVisible(afterFpsPopUpBackBtn, 1);
                } else if (paymentMethod.equalsIgnoreCase("Pay by recipient")) {
                    WebElement payByRecipientOptionVisible = waitForVisibility(payByRecipientOption);
                    payByRecipientOptionVisible.click();
                } else if (paymentMethod.equalsIgnoreCase("Wallet")) {
                    WebElement walletOptionVisible = waitForVisibility(walletOption);
                    walletOptionVisible.click();
                } else if (paymentMethod.equalsIgnoreCase("Card")) {
                    WebElement creditCardOptionVisible = waitForVisibility(creditCardOption);
                    creditCardOptionVisible.click();
                } else {
                    throw new IllegalArgumentException("Invalid payment method: " + paymentMethod);
                }
            } else {
                if (paymentMethod.equalsIgnoreCase("Cash")) {
                    WebElement cashOptionVisible = waitForVisibility(cashOption);
                    cashOptionVisible.click();
                } else if (paymentMethod.equalsIgnoreCase("FPS")) {
                    WebElement fpsOptionVisible = waitForVisibility(fpsOption);
                    fpsOptionVisible.click();
                    // Check if FPS popup occurs
                    clickIfVisible(fpsPopUp, 2);
                    clickIfVisible(afterFpsPopUpBackBtn, 1);
                } else if (paymentMethod.equalsIgnoreCase("Pay by recipient")) {
                    WebElement payByRecipientOptionVisible = waitForVisibility(payByRecipientOption);
                    payByRecipientOptionVisible.click();
                } else if (paymentMethod.equalsIgnoreCase("Wallet")) {
                    WebElement walletOptionVisible = waitForVisibility(walletOption);
                    walletOptionVisible.click();
                } else if (paymentMethod.equalsIgnoreCase("Card")) {
                    WebElement creditCardOptionVisible = waitForVisibility(creditCardOption);
                    creditCardOptionVisible.click();
                } else {
                    throw new IllegalArgumentException("Invalid payment method: " + paymentMethod);
                }
            }
            return true;
        } catch (Exception e){
            System.out.println("Error on selecting the payment method" + e.getMessage());
            return false;
        }
    }

    public boolean placeOrder() {
        try {
            WebElement placeOrderBtnVisible = waitForVisibility(placeOrderBtn);
            placeOrderBtnVisible.click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to click the place order button" + e.getMessage());
            return false;
        }
    }
}
