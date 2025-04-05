package com.senelium.pom.theinternet;

import com.senelium.Sel;
import com.senelium.assertion.SelAssert;
import com.senelium.element.Element;

public class FramesPage {
    public void openFrameType(String type) {
        Element.byLinkText(type).click();
    }

    public void shouldLeftFrameDisplay() {
        Sel.toFrame("frame-top");
        Sel.toFrame("frame-left");
        SelAssert.assertTrue(Sel.webDriver().getPageSource().contains("LEFT"), "Verify the left iframe contains LEFT");
    }

    public void shouldBottomFrameDisplay() {
        Sel.toFrame("frame-bottom");
        SelAssert.assertTrue(Sel.webDriver().getPageSource().contains("BOTTOM"), "Verify the bottom iframe contains BOTTOM");
    }

    public void shouldTextInMceFrameDisplay() {
        Sel.toFrame("mce_0_ifr");
        SelAssert.element(Element.byTag("p")).toHaveText("Your content goes here.", "Verify the text inside iframe is displayed");
    }
}
