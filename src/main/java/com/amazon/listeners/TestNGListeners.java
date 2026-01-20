package com.amazon.listeners;


import com.amazon.drivers.UITest;
import com.amazon.drivers.WebDriverProvider;
import com.amazon.media.ScreenRecordManager;
import com.amazon.media.ScreenshotsManager;
import com.amazon.utils.FileUtils;
import com.amazon.utils.dataReader.PropertyReader;
import com.amazon.utils.logs.LogsManager;
import com.amazon.utils.report.AllureAttachmentManager;
import com.amazon.utils.report.AllureConstants;
import com.amazon.utils.report.AllureEnvironmentManager;
import com.amazon.utils.report.AllureReportGenerator;
import com.amazon.validations.Validation;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;

public class TestNGListeners implements ISuiteListener, IExecutionListener, IInvokedMethodListener, ITestListener {
    public void onStart(ISuite suite) {
        suite.getXmlSuite().setName("General Framework Suite");
    }
    public void onExecutionStart() {
        LogsManager.info("Test Execution started");
        cleanTestOutputDirectories();
        LogsManager.info("Directories cleaned");
        createTestOutputDirectories();
        LogsManager.info("Directories created");
        PropertyReader.loadProperties();
        LogsManager.info("Properties loaded");
        AllureEnvironmentManager.setEnvironmentVariables();
        LogsManager.info("Allure environment set");
    }

    public void onExecutionFinish() {
        AllureReportGenerator.copyHistory();
        AllureReportGenerator.generateReports(false);
        AllureReportGenerator.generateReports(true);
        AllureReportGenerator.openReport(AllureReportGenerator.renameReport());
        LogsManager.info("Test Execution Finished");
    }


    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            if (testResult.getInstance() instanceof UITest)
            {
                ScreenRecordManager.startRecording();
            }
            LogsManager.info("Test Case " + testResult.getName() + " started");
        }
    }


    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        WebDriver driver = null;
        if (method.isTestMethod())
        {

            ScreenRecordManager.stopRecording(testResult.getName());
            Validation.assertAll(testResult);
            if (testResult.getInstance() instanceof WebDriverProvider provider)
                driver = provider.getWebDriver(); //initialize driver from WebDriverProvider
            switch (testResult.getStatus()){
               // case ITestResult.SUCCESS -> ScreenshotsManager.takeFullPageScreenshot(driver,"passed-" + testResult.getName());
                case ITestResult.FAILURE -> ScreenshotsManager.takeFullPageScreenshot(driver,"failed-" + testResult.getName());
                case ITestResult.SKIP -> ScreenshotsManager.takeFullPageScreenshot(driver,"skipped-" + testResult.getName());
            }
            AllureAttachmentManager.attachRecords(testResult.getName());
            AllureAttachmentManager.attachScreenshot("Full Page - " + testResult.getName() , ScreenshotsManager.SCREENSHOTS_PATH);
            AllureAttachmentManager.attachLogs();

        }
    }


    public void onTestSuccess(ITestResult result) {
        LogsManager.info("Test Case " + result.getName() + " passed");
        if (result.getInstance() instanceof WebDriverProvider provider)
        {
            WebDriver driver = provider.getWebDriver(); //initialize driver from WebDriverProvider
            ScreenshotsManager.takeFullPageScreenshot(driver, "passed-" + result.getName());
        }
    }

    public void onTestFailure(ITestResult result) {
        LogsManager.info("Test Case " + result.getName() + " failed");
        if (result.getInstance() instanceof WebDriverProvider provider)
        {
            WebDriver driver = provider.getWebDriver(); //initialize driver from WebDriverProvider
            ScreenshotsManager.takeFullPageScreenshot(driver, "failed-" + result.getName());
        }
    }

    public void onTestSkipped(ITestResult result) {
        LogsManager.info("Test Case " + result.getName() + " skipped");
    }


    // cleaning and creating dirs (logs, screenshots, recordings,allure-results)
    private void cleanTestOutputDirectories() {
        // Implement logic to clean test output directories
        FileUtils.cleanDirectory(AllureConstants.RESULTS_FOLDER.toFile());
        FileUtils.cleanDirectory(new File(ScreenshotsManager.SCREENSHOTS_PATH));
        FileUtils.cleanDirectory(new File(ScreenRecordManager.RECORDINGS_PATH));
        FileUtils.cleanDirectory(new File("src/test/resources/downloads/"));
        FileUtils.forceDelete(new File(LogsManager.LOGS_PATH +"logs.log"));
    }

    private void createTestOutputDirectories() {
        // Implement logic to create test output directories
        FileUtils.createDirectory(ScreenshotsManager.SCREENSHOTS_PATH);
        FileUtils.createDirectory(ScreenRecordManager.RECORDINGS_PATH);
        FileUtils.createDirectory("src/test/resources/downloads/");

    }
}