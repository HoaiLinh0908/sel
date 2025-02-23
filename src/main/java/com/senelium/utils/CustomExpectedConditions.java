package com.senelium.utils;

import com.senelium.Sel;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomExpectedConditions {
    public static ExpectedCondition<Boolean> elementToStopMoving(By locator) {
        return new ExpectedCondition<>() {

            @Override
            public Boolean apply(WebDriver driver) {
                WebElement element = driver.findElement(locator);
                Point initialLocation = element.getLocation();
                Sel.freeze(100);
                Point newLocation = element.getLocation();
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
}
