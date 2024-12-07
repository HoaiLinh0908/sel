package com.senelium.pom.theinternet;

import com.senelium.assertion.SelAssert;
import com.senelium.element.Element;

public class BrokenImagesPage {
    public void shouldImageNotBroken(String src) {
        Element img = Element.byCssSelector("img[src=\"%s\"]", src);
        SelAssert.expect(img).imgToBeVisible();
    }
}
