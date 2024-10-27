# SEL - Selenium Java 👨‍🔬
### Selenium framework for UI web application testing.

## Introduction

Sel is a Selenium framework with the following implementations:
- Auto-wait for element actions.
- Auto-retry for element assertions.
- Handle parallel execution.
- Cross-browser: support Chrome, Firefox, and Edge.
- Screenshot on each failed verification and support Allure report.


## Prerequisites 🛠️
- Java 11
- Maven
- Selenium WebDriver 4

## Write tests 📝 
```java
//Init driver configuration
DriverConfig driverConfig = DriverConfig.getInstance();
Sel.createDriver(driverConfig);

Sel.open("url");

Element button = Element.byCssSelector("button");

//Auto wait until the button is Clickable
button.click();

Element message = Element.byId("#message");

//Auto retry until the element is visible or timeout
SeAssert.expect(message).toBeVisible();
```
