package com.senelium.utils;

import com.senelium.Sel;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
}
