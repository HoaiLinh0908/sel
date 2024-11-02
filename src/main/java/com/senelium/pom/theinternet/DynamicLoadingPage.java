package com.senelium.pom.theinternet;

import com.senelium.element.Element;
import io.qameta.allure.Step;

public class DynamicLoadingPage {
    private Element example1;
    private Element example2;

    public DynamicLoadingPage() {
        this.example1 = Element.byXpath("//a[contains(text(), 'Example 1')]");
        this.example2 = Element.byXpath("//a[contains(text(), 'Example 2')]");
    }

    @Step("Open Example 1")
    public DynamicLoadingExamplePage openExample1() {
        example1.click();
        return new DynamicLoadingExamplePage();
    }

    @Step("Open Example 2")
    public DynamicLoadingExamplePage openExample2() {
        example2.click();
        return new DynamicLoadingExamplePage();
    }
}
