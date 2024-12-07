package com.senelium.assertion.core;

import com.senelium.Sel;
import org.openqa.selenium.TimeoutException;

public class AlertAssertion extends Assertion {

    public AlertAssertion(boolean isSoft) {
        super(isSoft);
    }

    public void toBeVisible() {
        try {
            Sel.toAlert();
        } catch (TimeoutException e) {
            this.onFailedCheck(
                    this.composeMessage(
                            "visible",
                            "invisible",
                            "Expect an alert is visible but found invisible.",
                            null
                    )
            );
        }
    }

    public void toHaveText(String expectedText) {
        try {
            String actualText = Sel.toAlert().getText();
            if (expectedText == null || !expectedText.equals(actualText)) {
                this.onFailedCheck(
                        this.composeMessage(
                                "has expectedText {" + expectedText + "}",
                                "has expectedText {" + actualText + "}",
                                String.format("Expect an alert with expectedText {%s} but found a different expectedText.", expectedText),
                                null
                        )
                );
            }
        } catch (TimeoutException e) {
            this.onFailedCheck(
                    this.composeMessage(
                            "has expectedText {" + expectedText + "}",
                            "invisible",
                            String.format("Expect an alert with expectedText {%s} but found alert invisible.", expectedText),
                            null
                    )
            );
        }
    }
}
