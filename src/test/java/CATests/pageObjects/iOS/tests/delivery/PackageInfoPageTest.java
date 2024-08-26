package CATests.pageObjects.iOS.tests.delivery;

import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import CATests.POM.iOS.delivery.PackageInfoPage;

public class PackageInfoPageTest {
    private IOSDriver driver;

    public PackageInfoPageTest(IOSDriver driver){
        this.driver = driver;
    }

    @Test
    public void testPackageInfoPage(){
        PackageInfoPage packageInfoPage = new PackageInfoPage(driver);
        System.out.println("-- Package Info Page --");

        //select the package content type
        boolean isPackageContentTypeSelected = packageInfoPage.selectPackageContent();
        assertTrue(isPackageContentTypeSelected, "failed to select the package content type");
        System.out.println("Select package content type: Success");

        //select size option
        boolean isSizeOptionSelected = packageInfoPage.selectSizeOption();
        assertTrue(isSizeOptionSelected, "failed to select the size option");
        System.out.println("Select size option: Success");

        //select weight option
        boolean isWeightOptionSelected = packageInfoPage.selectWeightOption();
        assertTrue(isWeightOptionSelected, "failed to select the weight option");
        System.out.println("Select weight option: Success");

        //select buy for you option
        boolean isBuyForYouOptionSelected = packageInfoPage.selectBuyForYouOption();
        assertTrue(isBuyForYouOptionSelected, "failed to select the buy for you option");
        System.out.println("Select buy for you option: Success");

        //enter remarks
        boolean isRemarksEntered = packageInfoPage.enterRemarks();
        assertTrue(isRemarksEntered, "failed to enter the remarks");
        System.out.println("Enter remarks: Success");

        //enter merchant order number
        boolean isMerchantOrderEntered = packageInfoPage.enterMerchantOrderNumber();
        assertTrue(isMerchantOrderEntered, "failed to enter the merchant order number");
        System.out.println("Enter merchant order number: Success");

        //click review order button
        boolean isReviewOrderBtnClicked = packageInfoPage.clickReviewOrderBtn();
        assertTrue(isReviewOrderBtnClicked, "failed to click on the review order button");
        System.out.println("Click review order button: Success");
    }
}
