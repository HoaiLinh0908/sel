package com.senelium.pom.theinternet;

import com.senelium.assertion.SelAssert;
import com.senelium.element.Element;
import com.senelium.element.Select;

public class DropdownPage {
    private final Select dropdown;

    public DropdownPage() {
        this.dropdown = Select.of(Element.byId("dropdown"));
    }

    public void selectDropdownOption(String text) {
        dropdown.selectByText(text);
    }

    public void shouldOptionSelected(String text) {
        Element option = dropdown.getOptionWithText(text);
        SelAssert.expect(option).toBeSelected();
    }

    public void shouldOptionUnselected(String text) {
        Element option = dropdown.getOptionWithText(text);
        SelAssert.expect(option).toBeUnselected();
    }
}
