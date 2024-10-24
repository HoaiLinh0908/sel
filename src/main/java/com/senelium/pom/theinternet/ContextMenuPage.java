package com.senelium.pom.theinternet;

import com.senelium.Senelium;
import com.senelium.assertion.SeAssert;
import com.senelium.element.Element;

public class ContextMenuPage {
    private final Element hotSpot;

    public ContextMenuPage() {
        this.hotSpot = Element.byId("hot-spot");
    }

    public void rightClickOnHotSpot() {
        this.hotSpot.rightClick();
    }

    public void shouldAlertDisplayWithMessage(String message) {
        SeAssert.expectAlertHasText(message);
    }

    public void closeAlert(boolean accept) {
        if(accept) {
            Senelium.toAlert().accept();
            return;
        }
        Senelium.toAlert().dismiss();
    }
}
