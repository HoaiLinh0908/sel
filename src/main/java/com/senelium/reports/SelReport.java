package com.senelium.reports;

public class SelReport {

    // TODO: Read config to find matching report, for now only Allure
    public static void takeScreenshot() {
        AllureReport.takeScreenshot();
    }

    public static void attachLog(String message) {
        AllureReport.attachTextLog(message);
    }
}
