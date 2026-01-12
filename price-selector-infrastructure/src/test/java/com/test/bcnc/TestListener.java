package com.test.bcnc;

import com.test.bcnc.logger.PriceLogger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        PriceLogger.info(this.getClass(), "### Test started '" + result.getMethod().getMethodName() + "' from '" + result.getTestClass().getName() + "'.");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        PriceLogger.info(this.getClass(), "### Test finished '" + result.getMethod().getMethodName() + "' from '" + result.getTestClass().getName() + "' (" +
                (result.getEndMillis() - result.getStartMillis()) + "ms).");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        PriceLogger.info(this.getClass().getName(),
                "### Test failed '" + result.getMethod().getMethodName() + "' from '" + result.getTestClass().getName() +
                        "' (" + (result.getEndMillis() - result.getStartMillis()) + "ms).");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        //Nothing
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        //Nothing
    }

    @Override
    public void onStart(ITestContext context) {
        PriceLogger.info(this.getClass(), "##### Starting tests from '" + context.getName() + "'.");
    }

    @Override
    public void onFinish(ITestContext context) {
        PriceLogger.info(this.getClass(), "##### Tests finished from '" + context.getName() + "'.");
    }
}
