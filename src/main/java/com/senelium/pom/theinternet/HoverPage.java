package com.senelium.pom.theinternet;

import com.senelium.assertion.SelAssert;
import com.senelium.element.Element;

public class HoverPage {
    private final Element firstFigure;

    public HoverPage() {
        this.firstFigure = Element.byCssSelector(".figure:first-of-type");
    }

    public void hoverOnImage() {
        this.firstFigure.locateChildByCssSelector("img").hover();
    }

    public void verifyCaptionIsDisplayed() {
        var caption = this.firstFigure.locateChildByCssSelector(".figcaption");
        SelAssert.element(caption).toBeVisible();
    }
}
