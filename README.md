# Ecommerce Site- Example of Test Automation Project using Hybrid Framework

### E2E testing suite for “Let’s Shop” web app


## General info
Test automation suite for “Let’s Shop” web application written with 
Selenium-TestNG framework in Java using Maven. On top of the existing framework, 
I had incorporated and brought Cucumber into this framework. 
“Let’s Shop” web app is dummy website for learning test automation. 
Contains all basic flows of a e-commerce website. 
Test Cases for E2E Testing: Submit the order, Delete the order, etc. 


## Environment
`Java version 11.0.13`

`Google Chrome`

`Mozilla Firefox`

`Microsof Edge`



## Running Tests

To run tests, run the following command

```bash
mvn test -Dbrowser=chrome
```
```bash
mvn test -Dbrowser=firefox
```
To run tests using headless browser use: 
```bash
mvn test -Dbrowser=chromeheadless
```

To run test suites use: 
```bash
mvn test -PErrorValidation
```
```bash
mvn test -PPurchase
```
```bash
mvn test -PDeleteProduct
```
To run Java Cucumber framework use: 
```bash
mvn test -PCucumberTests
```


## Status
Currently project contains only example of smoke test script.
