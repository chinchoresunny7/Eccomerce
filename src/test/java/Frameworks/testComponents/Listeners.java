package Frameworks.testComponents;

import Frameworks.Resources.ExtentReportNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends baseTest implements ITestListener {
    ExtentReports extent = ExtentReportNG.getReportObject();
    ExtentTest test ;
    ThreadLocal<ExtentTest> ts = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        test = extent.createTest(String.valueOf(result.getMethod().getMethodName()));
        ts.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        ts.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        ts.get().log(Status.FAIL,result.getThrowable());

        String screenshotPath = null;
        try {
            try {
                driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
            screenshotPath = getScreenshot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ts.get().addScreenCaptureFromPath(screenshotPath,result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        extent.flush();
    }
}
