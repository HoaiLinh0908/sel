package com.senelium.theinternet;

import com.senelium.pom.theinternet.FloatingMenuPage;
import org.testng.annotations.Test;

public class FloatingMenuTests extends TheInternetTestBase {
    final FloatingMenuPage floatingMenuPage = new FloatingMenuPage();

    @Test(description = "Scroll to bottom and verify the floating menu is in the viewport")
    public void testFloatingMenu() {
        homePage.openPage("Floating Menu");
        floatingMenuPage.scrollToPageFooter();
        floatingMenuPage.verifyPageHeadingIsHidden();
        floatingMenuPage.verifyTopNavigationMenuIsVisible();
    }
}
