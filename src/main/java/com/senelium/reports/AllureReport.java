package com.senelium.reports;

import com.senelium.Sel;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

public class AllureReport {

    public static void takeScreenshot() {
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) Sel.getDriver()).getScreenshotAs(OutputType.BYTES)));
    }

    public static void attachTextLog(String message) {
        Allure.addAttachment("Text log", message);
    }
}
