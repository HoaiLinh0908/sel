package com.senelium.theinternet;

import com.senelium.pom.theinternet.DropdownPage;
import org.testng.annotations.Test;

public class DropdownTests extends TheInternetTestBase {
    DropdownPage dropdownPage = new DropdownPage();

    @Test
    void dropdownTest() {
        String option1 = "Option 1";
        String option2 = "Option 2";

        homePage.openPage("Dropdown");
        dropdownPage.selectDropdownOption(option1);

        dropdownPage.shouldOptionSelected(option1);
        dropdownPage.shouldOptionUnselected(option2);
    }
}
