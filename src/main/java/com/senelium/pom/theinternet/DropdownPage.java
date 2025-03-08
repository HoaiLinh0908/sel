package com.senelium.pom.theinternet;

import com.senelium.assertion.SelAssert;
import com.senelium.element.Element;
import com.senelium.element.SelSelect;

public class DropdownPage {
    private final SelSelect dropdown;

    public DropdownPage() {
        this.dropdown = SelSelect.of(Element.byId("dropdown"));
    }

    public void selectDropdownOption(String text) {
        dropdown.selectByText(text);
    }

    public void shouldOptionSelected(String text) {
        Element option = dropdown.getOptionWithText(text);
        SelAssert.element(option).toBeSelected();
    }

    public void shouldOptionUnselected(String text) {
        Element option = dropdown.getOptionWithText(text);
        SelAssert.element(option).toBeUnselected();
    }
}
