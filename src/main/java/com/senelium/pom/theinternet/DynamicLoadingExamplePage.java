package com.senelium.pom.theinternet;

import com.senelium.assertion.SeAssert;
import com.senelium.element.Element;

public class DynamicLoadingExamplePage {
    private Element startButton;
    private Element finishSection;

    public DynamicLoadingExamplePage() {
        startButton = Element.byXpath("//button[text()='Start']");
        finishSection = Element.byId("finish");
    }

    public void startLoading() {
        startButton.click();
    }

    public void expectFinishSectionExist() {
        SeAssert.expect(finishSection).toBeExisting("The Finish section does not exist in the DOM");
    }

    public void expectFinishSectionNotExist() {
        SeAssert.expect(finishSection).toBeNotExisting();
    }

    public void expectFinishSectionVisible() {
        SeAssert.expect(finishSection).toBeVisible("The Finish section is not visible!", 10000);
    }
}
