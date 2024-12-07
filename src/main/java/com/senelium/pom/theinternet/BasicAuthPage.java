package com.senelium.pom.theinternet;

import com.senelium.assertion.SelAssert;
import com.senelium.element.Element;

public class BasicAuthPage {
    private final Element congratulationText;

    public BasicAuthPage() {
        congratulationText = Element.byXpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]");
    }

    public void shouldBasicAuthCongratulationDisplay() {
        SelAssert.expect(congratulationText).toBeVisible("The Basic auth congratulation is not displayed.");
    }
}
