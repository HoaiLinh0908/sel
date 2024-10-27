package com.senelium.element;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Quotes;

public class Select {
    private final Element element;

    public Select(Element element) {
        this.element = element;
    }

    public static Select of(Element element) {
        return new Select(element);
    }

    public void selectByText(String text) {
        this.baseSelect().selectByVisibleText(text);
    }

    public void selectByIndex(int index) {
        this.baseSelect().selectByIndex(index);
    }

    //TODO: adapt other methods in Selenium Select

    public Element getOptionWithText(String text) {
        return element.getChild(By.xpath(".//option[normalize-space(.) = " + Quotes.escape(text) + "]"));
    }

    public Element getElement() {
        return this.element;
    }

    public org.openqa.selenium.support.ui.Select baseSelect() {
        return new org.openqa.selenium.support.ui.Select(element.findVisibleElement());
    }
}
