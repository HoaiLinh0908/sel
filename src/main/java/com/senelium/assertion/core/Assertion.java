package com.senelium.assertion.core;

import com.senelium.Sel;
import com.senelium.config.DriverConfig;
import com.senelium.reports.AllureReport;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedCondition;
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

    protected void onFailedCheck(String message) {
        AllureReport.takeScreenshot();
        if (!this.isSoft) {
            throw new AssertionError(message);
        }
        this.errors.add(message);
    }

    protected <T> String composeMessage(T expected, T actual, String message, Integer timeout) {
        var to = timeout == null ? getDefaultTimeout() : timeout;
        var logMsg = message + "\nTimeout after " + to + " millisecond(s).";
        return logMsg + "\nExpected: " + expected + "\nActual:   " + actual;
    }

    private int getDefaultTimeout() {
        return DriverConfig.getInfo().getTimeout().getElementWait();
    }

    protected WebDriverWait waiter(Integer mil) {
        return mil != null ? Sel.getWaiter(mil) : Sel.getDefaultWaiter();
    }

    protected void toBe(ExpectedCondition<?> expectedCondition, Integer timeout, AssertMessage message) {
        try {
            this.waiter(timeout).until(expectedCondition);
        } catch (TimeoutException e) {
            this.onFailedCheck(this.composeMessage(message.getExpected(), message.getActual().get(), message.getDetail(), timeout));
        }
    }

    public Builder builder() {
        return new Builder(this);
    }

    public static class Builder {
        private Assertion assertion;
        private ExpectedCondition<?> condition;
        private Integer timeout;
        private AssertMessage message;

        public Builder(Assertion assertion) {
            this.assertion = assertion;
        }

        public Builder condition(ExpectedCondition<?> condition) {
            this.condition = condition;
            return this;
        }

        public Builder timeout(Integer timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder message(AssertMessage message) {
            this.message = message;
            return this;
        }

        public void execute() {
            this.assertion.toBe(condition, timeout, message);
        }
    }
}
