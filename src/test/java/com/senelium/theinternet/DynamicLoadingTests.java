package com.senelium.theinternet;

import com.senelium.pom.theinternet.DynamicLoadingExamplePage;
import com.senelium.pom.theinternet.DynamicLoadingPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DynamicLoadingTests extends TheInternetTestBase {
    DynamicLoadingPage dynamicLoadingPage = new DynamicLoadingPage();

    @BeforeMethod(alwaysRun = true)
    void beforeDynamicLoadingTest() {
        homePage.openPage("Dynamic Loading");
    }

    @Test(description = "Test the dynamic loading with hidden element")
    void testDynamicLoadingWithHiddenElement() {
        DynamicLoadingExamplePage examplePage = dynamicLoadingPage.openExample1();
        examplePage.expectFinishSectionExist();
        examplePage.startLoading();
        examplePage.expectFinishSectionVisible();
    }

    @Test(description = "Test the dynamic loading with rendered element")
    void testDynamicLoadingWithRenderedElement() {
        DynamicLoadingExamplePage examplePage = dynamicLoadingPage.openExample2();
        examplePage.expectFinishSectionNotExist();
        examplePage.startLoading();
        examplePage.expectFinishSectionVisible();
    }
}
