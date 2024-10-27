package com.senelium.pom.theinternet;

import com.senelium.assertion.SeAssert;
import com.senelium.element.Element;

public class DynamicControlsPage {
    private Element removeButton;
    private Element addButton;
    private Element checkbox;
    private Element enableButton;
    private Element textBox;
    private Element disableButton;

    public DynamicControlsPage() {
        this.removeButton = Element.byXpath("//button[text()='Remove']");
        this.addButton = Element.byXpath("//button[text()='Add']");
        this.checkbox = Element.byCssSelector("input[type='checkbox']");
        this.enableButton = Element.byXpath("//button[text()='Enable']");
        this.disableButton = Element.byXpath("//button[text()='Disable']");
        this.textBox = Element.byCssSelector("form > input");
    }

    public void removeCheckbox() {
        removeButton.click();
    }

    public void addCheckbox() {
        addButton.click();
    }

    public void expectCheckboxToVisible() {
        SeAssert.expect(checkbox).toBeVisible();
    }

    public void expectCheckboxToInvisible() {
        SeAssert.expect(checkbox).toBeInvisible();
    }

    public void enableTextBox() {
        enableButton.click();
    }

    public void disableTextBox() {
        disableButton.click();
    }

    public void expectTextBoxToEnabled() {
        SeAssert.expect(textBox).toBeEnabled();
    }

    public void expectTextBoxToDisabled() {
        SeAssert.expect(textBox).toBeDisabled();
    }
}
