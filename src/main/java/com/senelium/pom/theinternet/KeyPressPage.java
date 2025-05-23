package com.senelium.pom.theinternet;

import com.senelium.assertion.SelAssert;
import com.senelium.element.Element;
import org.openqa.selenium.Keys;

public class KeyPressPage {
    private final Element inputTextBox;
    private final Element result;

    public KeyPressPage() {
        this.inputTextBox = Element.byCssSelector("input[id='target']");
        this.result = Element.byId("result");
    }

    public void fillInput(String input) {
        this.inputTextBox.type(input);
    }

    public void verifyKeyIsDisplayed(Keys key) {
        SelAssert.element(result).toHaveText("You entered: %s".formatted(key.name()));
    }
}
