package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;
import utils.ExtentTestManager;

public class ExtentTestNGListener implements ITestListener {
    private static ExtentReports extent;

    @Override
    public void onStart(ITestContext context) {
        extent = ExtentManager.createInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String className = result.getTestClass().getName();
        String methodName = result.getMethod().getMethodName();
        ExtentTest test = extent.createTest(className + " :: " + methodName);
        ExtentTestManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = ExtentTestManager.getTest();
        if (test != null) {
            test.log(Status.PASS, "Test passed");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = ExtentTestManager.getTest();
        if (test != null) {
            test.log(Status.FAIL, "Test failed: " + result.getThrowable());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = ExtentTestManager.getTest();
        if (test != null) {
            test.log(Status.SKIP, "Test skipped: " + result.getThrowable());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentTestManager.removeTest();
        extent.flush();
    }
}