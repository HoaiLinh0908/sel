package com.senelium.pom.theinternet;

import com.senelium.assertion.SelAssert;
import com.senelium.element.Element;

public class MultipleWindowsPage {
    private final Element clickHereLink;

    public MultipleWindowsPage() {
        this.clickHereLink = Element.byXpath("//a[text()='Click Here']");
    }

    public void openNewTab() {
        this.clickHereLink.click();
    }

    public void verifyNewWindowIsDisplayed() {
        SelAssert.element(Element.byXpath("//h3[text()='New Window']")).toBeVisible();
    }
}
