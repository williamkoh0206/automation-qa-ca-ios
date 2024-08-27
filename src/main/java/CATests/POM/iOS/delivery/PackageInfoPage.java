package CATests.POM.iOS.delivery;

import CATests.POM.iOS.AbstractPageClass;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import CATests.utils.ConfigLoader;
import com.google.common.collect.ImmutableMap;

public class PackageInfoPage extends AbstractPageClass {
    // call the configloader to get the values we want to input
    private ConfigLoader configLoader;

    public PackageInfoPage(IOSDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.configLoader = new ConfigLoader();
    }

    //list of elements
    @iOSXCUITFindBy(accessibility = "Food / Drinks")
    private WebElement foodDrinksOption;

    @iOSXCUITFindBy(accessibility = "Flowers / Gifts")
    private WebElement flowersGiftsOption;

    @iOSXCUITFindBy(accessibility = "Electronics")
    private WebElement ElectronicsOption;

    @iOSXCUITFindBy(accessibility = "Medication")
    private WebElement MedicationOption;

    @iOSXCUITFindBy(accessibility = "Household")
    private WebElement HouseholdOption;

    @iOSXCUITFindBy(accessibility = "Other")
    private WebElement otherOption;

    @iOSXCUITFindBy(accessibility = "Size")
    private WebElement selectSizeOption;

    @iOSXCUITFindBy(accessibility = "≤ 20 × 20 × 10 cm")
    private WebElement selectTwentyCmOption;

    @iOSXCUITFindBy(accessibility = "≤ 30 × 30 × 30 cm")
    private WebElement selectThirtyCmOption;

    @iOSXCUITFindBy(accessibility = "≤ 40 × 40 × 40 cm")
    private WebElement selectFortyCmOption;

    @iOSXCUITFindBy(accessibility = "≤ 50 × 50 × 50 cm")
    private WebElement selectFiftyCmOption;

    @iOSXCUITFindBy(accessibility = "≤ 60 × 60 × 60 cm")
    private WebElement selectSixtyCmOption;

    @iOSXCUITFindBy(accessibility = "Weight")
    private WebElement selectWeightOption;

    @iOSXCUITFindBy(accessibility = "≤ 5 kg")
    private WebElement selectFiveKgOption;

    @iOSXCUITFindBy(accessibility = "≤ 10 kg")
    private WebElement selectTenKgOption;

    @iOSXCUITFindBy(accessibility = "≤ 15 kg")
    private WebElement selectFifteenKgOption;

    @iOSXCUITFindBy(accessibility = "≤ 20 kg")
    private WebElement selectLessThanTwentyKgOption;

    @iOSXCUITFindBy(accessibility = "Buy for you")
    private WebElement buyForYouOption;


    @iOSXCUITFindBy(accessibility = "Items value <$500")
    private WebElement itemsValueLessThanFiveHundred;

    @iOSXCUITFindBy(accessibility = "Items value $501 - $1,000")
    private WebElement itemsValueWithinOneThousand;

    @iOSXCUITFindBy(accessibility = "Items value $1,001 - $1,500")
    private WebElement itemsValueWithinOneThousandAndFiveHundred;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@value='Remarks (Optional)']/following::XCUIElementTypeStaticText[1]")
    private WebElement remarksInputText;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@value='Remarks (Optional)']/following::XCUIElementTypeTextView")
    private WebElement remarksInputField;

    @iOSXCUITFindBy(accessibility = "Merchant order number (optional)")
    private WebElement enterMerchantOrderText;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@value='Merchant order number (optional)']/preceding::XCUIElementTypeTextView[1]")
    private WebElement enterMerchantOrderInputField;

    @iOSXCUITFindBy(accessibility = "Done")
    private WebElement doneRemarksPopUp;

    @iOSXCUITFindBy(accessibility = "ggv__order_detail__footer__button__review_order")
    private WebElement reviewOrderBtn;

    public boolean selectPackageContent() {
        try {
            String packageContent = configLoader.getProperty("CONTENT_TYPE");
            if (packageContent.equalsIgnoreCase("Food / Drinks")) {
                WebElement foodDrinksOptionVisibility = waitForVisibility(foodDrinksOption);
                foodDrinksOptionVisibility.click();
                return true;
            } else if (packageContent.equalsIgnoreCase("Flowers / Gifts")) {
                WebElement flowerOrGiftsVisibility = waitForVisibility(flowersGiftsOption);
                flowerOrGiftsVisibility.click();
                return true;
            } else if (packageContent.equalsIgnoreCase("Electronics")) {
                WebElement electronicsOptionVisibility = waitForVisibility(ElectronicsOption);
                electronicsOptionVisibility.click();
                return true;
            } else if (packageContent.equalsIgnoreCase("Medication")) {
                WebElement medicationOptionVisibility = waitForVisibility(MedicationOption);
                medicationOptionVisibility.click();
                return true;
            } else if (packageContent.equalsIgnoreCase("Household")) {
                WebElement householdOptionVisibility = waitForVisibility(HouseholdOption);
                householdOptionVisibility.click();
                return true;
            } else if (packageContent.equalsIgnoreCase("Other")) {
                WebElement otherOptionVisibility = waitForVisibility(otherOption);
                otherOptionVisibility.click();
                return true;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the package content : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean selectSizeOption() {
        try {
            WebElement selectSizeOptionVisibility = waitForVisibility(selectSizeOption);
            selectSizeOptionVisibility.click();

            String selectedSize = configLoader.getProperty("SIZE");
            if (selectedSize.equalsIgnoreCase("20")) {
                WebElement twentyCmOptionVisibility = waitForVisibility(selectTwentyCmOption);
                twentyCmOptionVisibility.click();
                return true;
            } else if (selectedSize.equalsIgnoreCase("30")) {
                WebElement thirtyCmOptionVisibility = waitForVisibility(selectThirtyCmOption);
                thirtyCmOptionVisibility.click();
                return true;
            } else if (selectedSize.equalsIgnoreCase("40")) {
                WebElement fortyCmOptionVisibility = waitForVisibility(selectFortyCmOption);
                fortyCmOptionVisibility.click();
                return true;
            } else if (selectedSize.equalsIgnoreCase("50")) {
                WebElement fiftyCmOptionVisibility = waitForVisibility(selectFiftyCmOption);
                fiftyCmOptionVisibility.click();
                return true;
            } else if (selectedSize.equalsIgnoreCase("60")) {
                WebElement sixtyCmOptionVisibility = waitForVisibility(selectSixtyCmOption);
                sixtyCmOptionVisibility.click();
                return true;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the size option : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean selectWeightOption() {
        try {
            WebElement selectWeightOptionVisibility = waitForVisibility(selectWeightOption);
            selectWeightOptionVisibility.click();

            String weightOption = configLoader.getProperty("WEIGHT");
            if (weightOption.equalsIgnoreCase("5")) {
                WebElement fiveKgOptionVisibility = waitForVisibility(selectFiveKgOption);
                fiveKgOptionVisibility.click();
                return true;
            } else if (weightOption.equalsIgnoreCase("10")) {
                WebElement tenKgOptionVisibility = waitForVisibility(selectTenKgOption);
                tenKgOptionVisibility.click();
                return true;
            } else if (weightOption.equalsIgnoreCase("15")) {
                WebElement fifteenKgOptionVisibility = waitForVisibility(selectFifteenKgOption);
                fifteenKgOptionVisibility.click();
                return true;
            } else if (weightOption.equalsIgnoreCase("20")) {
                WebElement lessThanTwentyKgOptionVisibility = waitForVisibility(selectLessThanTwentyKgOption);
                lessThanTwentyKgOptionVisibility.click();
                return true;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the weight option : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean selectBuyForYouOption() {
        try {
            String buyForYouFlag = configLoader.getProperty("BUY_FLAG");
            if (buyForYouFlag.equalsIgnoreCase("true")) {
                WebElement buyForYouOptionVisibility = waitForVisibility(buyForYouOption);
                buyForYouOptionVisibility.click();
                String buyOption = configLoader.getProperty("BUY_OPTION");
                if (buyOption.equalsIgnoreCase("500")) {
                    WebElement itemsValueLessThanFiveHundredVisibility = waitForVisibility(itemsValueLessThanFiveHundred);
                    itemsValueLessThanFiveHundredVisibility.click();
                    return true;
                } else if (buyOption.equalsIgnoreCase("1000")) {
                    WebElement itemsValueLessThanOneThousandVisibility = waitForVisibility(itemsValueWithinOneThousand);
                    itemsValueLessThanOneThousandVisibility.click();
                    return true;
                } else if (buyOption.equalsIgnoreCase("1500")) {
                    WebElement itemsValueLessThanOneFiveThousandVisibility = waitForVisibility(itemsValueWithinOneThousandAndFiveHundred);
                    itemsValueLessThanOneFiveThousandVisibility.click();
                    return true;
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the buy for you option : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean enterRemarks() {
        try {
            String remarksFlag = configLoader.getProperty("REMARKS_FLAG");
            if (remarksFlag.equalsIgnoreCase("true")) {
                String remarks = configLoader.getProperty("DELIVERY_REMARKS");
                WebElement remarksInputTextVisibility = waitForVisibility(remarksInputText);
                remarksInputTextVisibility.click();
                WebElement remarksInputFieldVisibility = waitForVisibility(remarksInputField);
                remarksInputFieldVisibility.sendKeys(remarks);
                System.out.println("Remarks sent is: " + remarks);
                //press the enter button
                Actions actions = new Actions(driver);
                actions.sendKeys(remarksInputFieldVisibility, Keys.RETURN).perform();
                clickIfVisible(doneRemarksPopUp,2);
                return true;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error entering the remarks : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean enterMerchantOrderNumber() {
        try {
            String enterMerchantOrderNumberFlag = configLoader.getProperty("MERCHANT_ORDER_FLAG");
            String accountType = configLoader.getProperty("ACCOUNT_TYPE");
            if (accountType.equalsIgnoreCase("business") && enterMerchantOrderNumberFlag.equalsIgnoreCase("true")) {
                String merchantOrderNumber = configLoader.getProperty("MERCHANT_REMARKS");
                WebElement enterMerchantOrderTextVisibility = waitForVisibilityWithScroll(enterMerchantOrderText,"Merchant order number (optional)");
                enterMerchantOrderTextVisibility.click();
                WebElement enterMerchantOrderFieldVisibility = waitForVisibility(enterMerchantOrderInputField);
                enterMerchantOrderFieldVisibility.sendKeys(merchantOrderNumber);
                System.out.println("Merchant order number sent is: " + merchantOrderNumber);
                //press the enter button
                Actions actions = new Actions(driver);
                actions.sendKeys(enterMerchantOrderFieldVisibility, Keys.RETURN).perform();
                return true;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error entering the merchant order numbers " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean clickReviewOrderBtn() {
        try {
            WebElement reviewOrderBtnVisibility = waitForVisibility(reviewOrderBtn);
            reviewOrderBtnVisibility.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking the review order button : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}

