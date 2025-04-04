package com.senelium.pom.theinternet;

import com.senelium.element.Element;

public abstract class BasePage {
    public void scrollToPageFooter() {
        Element.byId("page-footer").scrollToView();
    }
}
