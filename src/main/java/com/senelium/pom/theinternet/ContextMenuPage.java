package com.senelium.pom.theinternet;

import com.senelium.Sel;
import com.senelium.assertion.SelAssert;
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
        SelAssert.expectAlert().toHaveText(message);
    }

    public void closeAlert(boolean accept) {
        if(accept) {
            Sel.toAlert().accept();
            return;
        }
        Sel.toAlert().dismiss();
    }
}
