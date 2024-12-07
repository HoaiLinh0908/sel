package com.senelium.pom.theinternet;

import com.senelium.assertion.SelAssert;
import com.senelium.element.Element;
import io.qameta.allure.Step;

public class DynamicLoadingExamplePage {
    private Element startButton;
    private Element finishSection;

    public DynamicLoadingExamplePage() {
        startButton = Element.byXpath("//button[text()='Start']");
        finishSection = Element.byId("finish");
    }

    @Step("Start loading element")
    public void startLoading() {
        startButton.click();
    }

    @Step("Verify the Finish section exist")
    public void expectFinishSectionExist() {
        SelAssert.expect(finishSection).toBeExisting("The Finish section does not exist in the DOM");
    }

    @Step("Verify the Finish section does not exist")
    public void expectFinishSectionNotExist() {
        SelAssert.expect(finishSection).toBeNotExisting();
    }

    @Step("Verify the Finish section is visible")
    public void expectFinishSectionVisible() {
        SelAssert.expect(finishSection).toBeVisible("The Finish section is not visible!", 10000);
    }
}
