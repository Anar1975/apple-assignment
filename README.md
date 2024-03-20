# Apple Assignment

## Technologies Overview
 - Java 19
 - TestNG
 - Maven
 - Rest Assured - REST APIs with the help of the Java libraries
 - FasterXML Jackson databind functionality that allows reading/parsing JSON files/responses

## Prerequisites
 - [Java](https://www.oracle.com/java/technologies/javase/jdk19-archive-downloads.html) should be installed and [configured](https://mkyong.com/java/how-to-set-java_home-environment-variable-on-mac-os-x/).
 - [Maven](https://mkyong.com/maven/install-maven-on-mac-osx/) should be installed and configured.
 - [Allure report](https://allurereport.org/docs/gettingstarted-installation/) should be installed.

## Test Plan
Please refer to the [TestPlan.xlsx](TestPlan.xlsx) located under project menu 

## Test Executions
There are multiple ways for tests execution.
1. Navigate to the desired test class and perform right-click on any place in the test class. Select run test.
2. Navigate to the desired test class and click Run Test green icon next to the test class name of test case name.
3. Navigate to the desired test class and perform Ctrl+Shift+R keyboard combination.
4. Under the project menu find regression.xml file and perform right click on the regression.xml file. Then select Run '.../regression.xml'
5. Execute tests from CLI
```sh
$ mvn clean install
```

## Generate Allure Report
```sh
$ allure serve allure-results
```
or simply
```sh
$ allure serve
```
To processes test results and save HTML report into the allure-report directory:
```sh
$ allure generate
```



