package com.senelium.listener;

import com.senelium.assertion.SelAssert;
import io.qameta.allure.listener.TestLifecycleListener;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class TestListener implements ITestListener, TestLifecycleListener {

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (!(SelAssert.errors() == null || SelAssert.errors().isEmpty())) {
            var messages = SelAssert.errors();
            SelAssert.clearErrors();
            throw new AssertionError(String.join("\n", messages));
//            System.err.println(String.join("\n", messages));
//            result.setStatus(ITestResult.FAILURE);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onFinish(ITestContext result) {
    }
}
