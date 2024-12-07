package com.senelium.assertion.core;

import com.senelium.Sel;
import com.senelium.config.DriverConfig;
import com.senelium.reports.AllureReport;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Assertion {
    protected final boolean isSoft;
    protected final List<String> errors;

    protected Assertion(boolean isSoft) {
        this.isSoft = isSoft;
        this.errors = new ArrayList<>();
    }

    protected <T> void onFailedCheck(String message) {
        AllureReport.takeScreenshot();
        if (!this.isSoft) {
            throw new AssertionError(message);
        }
        this.errors.add(message);
    }

    protected <T> String composeMessage(T expected, T actual, String message, Integer timeout) {
        int to = timeout == null ? getDefaultTimeout() : timeout;
        String logMsg = message + " Timeout " + to + " millisecond(s).";
        return logMsg + "\nExpected: " + expected + "\nActual:   " + actual;
    }

    private int getDefaultTimeout() {
        return DriverConfig.getInstance().getTimeout().getElementWait();
    }

    protected WebDriverWait waiter(Integer mil) {
        return mil != null ? Sel.getWaiter(mil) : Sel.getDefaultWaiter();
    }
}
