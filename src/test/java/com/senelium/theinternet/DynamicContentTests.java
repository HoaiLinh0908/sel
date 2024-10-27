package com.senelium.theinternet;

import com.senelium.Sel;
import com.senelium.pom.theinternet.DynamicContentPage;
import org.testng.annotations.Test;

public class DynamicContentTests extends TheInternetTestBase {
    DynamicContentPage dynamicContentPage = new DynamicContentPage();

    @Test
    void testTextChanged() {
        homePage.openPage("Dynamic Content");
        String oldText = dynamicContentPage.getContentOfDynamicRow();
        dynamicContentPage.clickOnClickHere();
        dynamicContentPage.expectTextChanged(oldText);
        Sel.freeze(3000);
    }
}
