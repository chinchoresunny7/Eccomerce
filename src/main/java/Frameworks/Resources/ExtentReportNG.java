package Frameworks.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {


    public static ExtentReports getReportObject(){
        String path = System.getProperty("user.dir")+"//Reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setDocumentTitle("Ecommerce Automation Result");
        reporter.config().setReportName("Web Automation Result");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Sunny");
//
        return extent;

    }

}
