# SEL - Selenium Java 👨‍🔬
### Selenium framework for UI web application testing.

## Introduction

Sel is a personal Selenium framework with the following implementations:
- Auto-wait for element actions.
- Auto-retry for element assertions.
- Handle parallel execution.
- Supports Chrome, Firefox, and Edge.
- Screenshot of each failed assertion for Allure report.


## Tools 🛠️
- Java 21
- Selenium WebDriver 4
- Maven
- TestNG

## Write tests 📝 
```java
//Init driver configuration.
DriverConfig driverConfig = DriverConfig.getInstance();
Sel.createDriver(driverConfig);

Sel.open("https://yoursample.com");

Element button = Element.byCssSelector("button");
button.click(); //Auto-wait until the button is clickable.

Element message = Element.byId("#message");
SelAssert.element(message).toBeVisible(); //Auto retry until the element is visible.
```
