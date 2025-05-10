package com.senelium.assertion.core;

import com.senelium.Sel;
import com.senelium.assertion.SelAssert;
import com.senelium.reports.SelReport;
import io.qameta.allure.Allure;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
@Setter
@Accessors(fluent = true)
public abstract class Assertion {
    protected boolean isSoft;

    protected Assertion(boolean isSoft) {
        this.isSoft = isSoft;
    }

    protected void onFailedCheck(String message) {
        SelReport.takeScreenshot();
        try {
            throw new AssertionError(message);
        } catch (AssertionError error) {
            var stackTrace = Arrays.stream(error.getStackTrace())
                    .map(StackTraceElement::toString)
                    .collect(Collectors.joining("\n\tat "));
            SelAssert.addError("\n" + message + "\n" + stackTrace);
            if (!this.isSoft) {
                var messages = SelAssert.errors();
                SelAssert.clearErrors();
                throw new AssertionError(String.join("\n\n", messages));
            }
        }
    }

    protected <T> String composeMessage(T expected, T actual, String message, Integer timeout) {
        var to = timeout == null ? getDefaultTimeout() : timeout;
        var logMsg = ">>>FAILED: " + message + "\nTimeout after " + to + " millisecond(s).";
        return logMsg + "\nExpected: " + expected + "\nActual:   " + actual;
    }

    private int getDefaultTimeout() {
        return Sel.driverConfig().timeout().elementWait();
    }

    protected WebDriverWait waiter(Integer mil) {
        return mil != null ? Sel.waiter(mil) : Sel.defaultWaiter();
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
