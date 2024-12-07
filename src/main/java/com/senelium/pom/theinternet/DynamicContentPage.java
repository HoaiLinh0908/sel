package com.senelium.pom.theinternet;

import com.senelium.assertion.SelAssert;
import com.senelium.element.Element;

public class DynamicContentPage {
    private Element clickHereLink;
    private Element dynamicRowContent;

    public DynamicContentPage() {
        this.clickHereLink = Element.byXpath("//a[text()='click here']");
        this.dynamicRowContent = Element.byCssSelector("div#content > div.row:nth-of-type(3) > div:nth-of-type(2)");
    }

    public void clickOnClickHere() {
        clickHereLink.click();
    }

    public String getContentOfDynamicRow() {
        return dynamicRowContent.getText();
    }

    public void expectTextChanged(String oldText) {
        SelAssert.expect(dynamicRowContent).toNotHaveText(oldText);
    }
}
