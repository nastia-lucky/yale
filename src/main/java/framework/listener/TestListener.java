package framework.listener;

import framework.Browser;
import framework.logger.Log;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {

    String filePath = "target/FailedTest/";

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.logInfo(iTestResult.getName() + " started");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        //No need to use this method
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.logInfo(iTestResult.getName() + " test failed");
        Log.logInfo("Error" + iTestResult.getName() + " test has failed");
        String methodName = iTestResult.getName().trim();
        takeScreenShot(methodName, Browser.getDriver());
    }

    @Attachment
    public byte[] takeScreenShot(String methodName, WebDriver driver) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(filePath + methodName + ".png"));
            Log.logInfo("Placed screen shot in " + filePath + " ***");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        //No need to use this method
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        //No need to use this method
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        //No need to use this method
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        //No need to use this method
    }
}
