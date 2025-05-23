package com.senelium.element;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Quotes;
import org.openqa.selenium.support.ui.Select;

public class SelSelect {
    private final Element element;

    public SelSelect(Element element) {
        this.element = element;
    }

    public static SelSelect of(Element element) {
        return new SelSelect(element);
    }

    public void selectByText(String text) {
        this.baseSelect().selectByVisibleText(text);
    }

    public void selectByIndex(int index) {
        this.baseSelect().selectByIndex(index);
    }

    //TODO: adapt other methods in Selenium Select

    public Element getOptionWithText(String text) {
        return element.locateChild(By.xpath(".//option[normalize-space(.) = " + Quotes.escape(text) + "]"));
    }

    public Element getElement() {
        return this.element;
    }

    public Select baseSelect() {
        return new org.openqa.selenium.support.ui.Select(element.findVisibleElement());
    }
}
