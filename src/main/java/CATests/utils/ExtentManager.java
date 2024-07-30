package CATests.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.IOException;

public class ExtentManager {
    private static ExtentReports extent;

    public synchronized static ExtentReports getInstance(){
        if (extent == null){
            extent = createInstance(generateFilename());
        }
        return extent;
        }

    private static String generateFilename() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm");
        String timestamp = LocalDateTime.now().format(formatter);
        return "extentReports/CAEndToEndAutomationTestReport" + timestamp + ".html";
    }

    public static ExtentReports createInstance(String filename){
        ExtentSparkReporter spark = new ExtentSparkReporter(filename);
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("GOGOX CA EndToEnd Automation Suite");
        spark.config().setReportName("Test Report");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        return extent;
        }
    public static void clearReportDirectory() throws IOException {
        File reportDirectory = new File("extentReports/");
        if (reportDirectory.exists()) {
            FileUtils.cleanDirectory(reportDirectory);
            System.out.println("Report directory cleaned.");
        } else {
            System.out.println("Report directory does not exist.");
        }
    }
}
