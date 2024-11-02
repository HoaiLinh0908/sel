package com.senelium.pom.theinternet;

import com.senelium.element.Element;
import io.qameta.allure.Step;

public class HomePage {

    @Step("Open {0} page")
    public void openPage(String name) {
        Element.byXpath(String.format("//li/a[text()=\"%s\"]", name)).click();
        Element.byXpath("//h1[text()='Welcome to the-internet']").waitForInvisible();
    }
}
