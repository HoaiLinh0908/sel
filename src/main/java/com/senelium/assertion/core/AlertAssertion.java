package com.senelium.assertion.core;

import com.senelium.Sel;
import com.senelium.utils.CustomExpectedConditions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AlertAssertion extends Assertion {

    public AlertAssertion(boolean isSoft) {
        super(isSoft);
    }

    public void toBeVisible(Integer timeout, String message) {
        super.toBe(
                () -> waiter(timeout).until(ExpectedConditions.alertIsPresent()),
                timeout,
                String.format("%s\nExpect an alert to present but not", message),
                "Alert is presented",
                "Alert is not presented"
        );
    }

    public void toBeVisible(String message) {
        this.toBeVisible(null, message);
    }

    public void toBeVisible() {
        this.toBeVisible(null, "");
    }

    public void toHaveText(String expectedText, Integer timeout, String message) {
        this.toBeVisible(timeout, message);
        super.toBe(
                () -> waiter(timeout).until(CustomExpectedConditions.alertToHaveText(expectedText)),
                timeout,
                String.format("%s\nExpect an alert with text {%s} but not.", message, expectedText),
                expectedText,
                Sel.selDriver().getWebDriver().switchTo().alert().getText()
        );
    }

    public void toHaveText(String expectedText) {
        this.toHaveText(expectedText, null, "");
    }
}
