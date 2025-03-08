package com.senelium.pom.theinternet;

import com.senelium.assertion.SelAssert;
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
        SelAssert.element(checkbox).toBeVisible();
    }

    public void expectCheckboxToInvisible() {
        SelAssert.element(checkbox).toBeInvisible();
    }

    public void enableTextBox() {
        enableButton.click();
    }

    public void disableTextBox() {
        disableButton.click();
    }

    public void expectTextBoxToEnabled() {
        SelAssert.element(textBox).toBeEnabled();
    }

    public void expectTextBoxToDisabled() {
        SelAssert.element(textBox).toBeDisabled();
    }
}
