package com.senelium.assertion.core;

import com.senelium.Sel;
import com.senelium.element.CustomExpectedConditions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AlertAssertion extends Assertion {

    public AlertAssertion(boolean isSoft) {
        super(isSoft);
    }

    public void toBeVisible() {
        this.toBeVisible(null, "");
    }

    public void toBeVisible(String message) {
        this.toBeVisible(null, message);
    }

    public void toBeVisible(Integer timeout) {
        this.toBeVisible(timeout, "");
    }

    public void toBeVisible(Integer timeout, String message) {
        this.builder().condition(ExpectedConditions.alertIsPresent())
                .timeout(timeout)
                .message(new AssertMessage("%s\nExpect an alert to display but not.".formatted(message),
                        "Alert is displayed", "Alert is not presented"))
                .execute();
    }

    public void toHaveText(String expectedText) {
        this.toHaveText(expectedText, null, "");
    }

    public void toHaveText(String expectedText, Integer timeout) {
        this.toHaveText(expectedText, timeout, "");
    }

    public void toHaveText(String expectedText, String message) {
        this.toHaveText(expectedText, null, message);
    }

    public void toHaveText(String expectedText, Integer timeout, String message) {
        this.toBeVisible(timeout, message);
        this.builder().condition(CustomExpectedConditions.alertToHaveText(expectedText))
                .timeout(timeout)
                .message(new AssertMessage("%s\nExpect an alert with text {%s} but not.".formatted(message, expectedText),
                        expectedText, () -> Sel.toAlert().getText()))
                .execute();
    }
}
