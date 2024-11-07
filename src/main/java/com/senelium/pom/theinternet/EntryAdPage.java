package com.senelium.pom.theinternet;

import com.senelium.assertion.SeAssert;
import com.senelium.element.Element;

public class EntryAdPage {
    private final Element modalTitle;
    private final Element modalCloseButton;
    private final Element restartAdLink;

    public EntryAdPage() {
        this.modalTitle = Element.byCssSelector("div.modal h3");
        this.modalCloseButton = Element.byXpath("//div[@class='modal-footer']/p");
        this.restartAdLink = Element.byId("restart-ad");
    }

    public void closeEntryAdModal() {
        modalCloseButton.click();
    }

    public void restartEntryAd() {
        restartAdLink.click();
    }

    public void expectEntryModalVisible() {
        modalTitle.waitForStopMoving();
        SeAssert.expect(modalTitle).toBeVisible();
    }
}
