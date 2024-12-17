# SEL - Selenium Java 👨‍🔬
### Selenium framework for UI web application testing.

## Introduction

Sel is a Selenium framework with the following implementations:
- Auto-wait for element actions.
- Auto-retry for element assertions.
- Handle parallel execution.
- Cross-browser: supports Chrome, Firefox, and Edge.
- Screenshot of each failed assertion for Allure report.


## Prerequisites 🛠️
- Java 21
- Maven
- Selenium WebDriver 4

## Write tests 📝 
```java
//Init driver configuration.
DriverConfig driverConfig = DriverConfig.getInstance();
Sel.createDriver(driverConfig);

Sel.open("https://yoursample.com");

Element button = Element.byCssSelector("button");
Element message = Element.byId("#message");

//Auto-wait until the button is clickable.
button.click();

//Auto retry until the element is visible.
SelAssert.expect(message).toBeVisible();
```
