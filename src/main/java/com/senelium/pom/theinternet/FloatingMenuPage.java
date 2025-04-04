package com.senelium.pom.theinternet;

import com.senelium.assertion.SelAssert;
import com.senelium.element.Element;

public class FloatingMenuPage extends BasePage {
    private final Element topNavigationMenu;
    private final Element pageHeading;

    public FloatingMenuPage() {
        this.topNavigationMenu = Element.byId("menu");
        this.pageHeading = Element.byXpath("//h3[text()='Floating Menu']");
    }

    public void verifyPageHeadingIsHidden() {
        SelAssert.element(pageHeading).toBeNotInViewport("Verify the page heading is not in the viewport");
    }

    public void verifyTopNavigationMenuIsVisible() {
        SelAssert.element(topNavigationMenu).toBeInViewport("Verify the top navigation menu is in the viewport.");
    }
}
