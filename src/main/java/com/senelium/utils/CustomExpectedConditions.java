package com.senelium.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CustomExpectedConditions {
    public static ExpectedCondition<Boolean> elementToStopMoving(By locator) {
        return new ExpectedCondition<>() {
            Point initialLocation = null;

            @Override
            public Boolean apply(WebDriver driver) {
                WebElement element = driver.findElement(locator);
                Point newLocation = element.getLocation();
                if (initialLocation == null) {
                    initialLocation = newLocation;
                    return false;
                }
                return initialLocation.equals(newLocation);
            }

            @Override
            public String toString() {
                return "element to stop moving: " + locator;
            }
        };
    }

    public static ExpectedCondition<Boolean> alertToHaveText(String expectedText) {
        return new ExpectedCondition<>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    String currentText = driver.switchTo().alert().getText();
                    return expectedText.equals(currentText);
                } catch (NoAlertPresentException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "alert to have text: " + expectedText;
            }
        };
    }

    public static ExpectedCondition<Boolean> imageIsVisible(WebElement element) {
        return ExpectedConditions.not(ExpectedConditions.domPropertyToBe(element, "naturalWidth", "0"));
    }
}
