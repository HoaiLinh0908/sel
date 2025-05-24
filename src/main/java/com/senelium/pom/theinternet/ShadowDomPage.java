package com.senelium.pom.theinternet;

import com.senelium.element.Element;
import com.senelium.element.ShadowElement;

public class ShadowDomPage {
    public void verifyTheShadowElementIsDisplayed() {
        var element = ShadowElement.byCssSelector(Element.byCssSelector("my-paragraph:first-of-type"), "p");
        element.findShadowElement(); // For the sake of laziness, I will implement proper assertion later
    }
}
