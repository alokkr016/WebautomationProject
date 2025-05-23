# 🌐 Web Automation Testing Framework

## 🚀 Project Overview
A scalable and maintainable web automation testing framework built with Selenium WebDriver and TestNG. Designed for modern teams with features like parallel execution, AWS Parameter Store integration, and visual reporting via Extent Reports.

## � Latest Execution
You can view the latest test execution results on LambdaTest:
[View Test Execution](https://automation.lambdatest.com/share?shareId=M5CVWIPAYGPXKFPJGTDP43A8OB5HI73DJLV0OY2ZT4JY2TUTD01PU8HWEH43098R&isThemeEnabled=true&themeVersion=v2)

## 🤔 Project Thought Process
This framework was designed with the following key principles in mind:

- **Solid framework creation**
  - Implemented Page Object Model (POM) pattern for maintainable test code
  - Modular architecture allowing easy extension and reusability
  - Thread-safe implementation for parallel execution

- **Dependency management (pom.xml)**
  - Clean Maven dependency management
  - Proper version control of all dependencies
  - Centralized dependency management for easy updates

- **Test libraries (TestNG)**
  - Utilizes TestNG for robust test management
  - Integrated TestNG listeners for enhanced reporting
  - Support for parameterized testing

- **Parallel test execution**
  - Thread-safe WebDriver implementation using ThreadLocal
  - Support for parallel execution across multiple browsers
  - Compatible with cloud testing platforms (LambdaTest, BrowserStack)

- **Clean logs + reports**
  - Integrated Extent Reports for detailed HTML reporting
  - Clean and structured logging
  - Easy-to-read test execution reports

- **Secure handling of sensitive data**
  - No hardcoded credentials
  - Secure credentials handling via AWS Parameter Store
  - Environment-specific configuration management

## �🔧 Prerequisites
- Java 17 or higher
- Maven
- LambdaTest (for remote execution)

## 🏃 Running the Tests

### 🏠 Local Execution
```bash
mvn test -e \
    -Dbrowser=chrome \
    -DisRemote=false \
    -Durl=https://the-internet.herokuapp.com/login \
    -DAWS_ACCESS_KEY=YOUR_ACCESS_KEY \
    -DAWS_SECRET_KEY=YOUR_SECRET_KEY
```
### 🌎 Remote Execution
```bash
mvn test -e \
    -Dbrowser=chrome \
    -DisRemote=true \
    -Durl=https://the-internet.herokuapp.com/login \
    -DAWS_ACCESS_KEY=YOUR_ACCESS_KEY \
    -DAWS_SECRET_KEY=YOUR_SECRET_KEY
```

_Note: You can override the default values defined in `testng.xml` using Maven `-D` parameters for flexible execution._  
Alternatively, you can still use <parameter> tags in your testng.xml file to define default values for `browser`, `isRemote`, and `url`, which will be overridden if the same are passed via Maven `-D` parameters.

## 📁 Project Structure
```
src/
├── main/
│   ├── java/
│   │   ├── pages/              # Page Object Model classes
│   │   ├── utils/              # Utility classes
│   │   └── base/               # Base classes
│   └── resources/             # Configuration files
└── test/
    ├── java/
    │   ├── tests/              # Test classes
    │   └── listeners/          # TestNG listeners
    └── resources/             # Test resources
```

## ✨ Features
- Page Object Model (POM) design pattern for maintainable test code.
- Thread-safe WebDriver management using ThreadLocal.
- Secure credentials handling via AWS Parameter Store.
- Support for local and remote test execution in parallel.
- Integrated Extent Reports for detailed HTML reports.
- Flexible configuration via testng.xml and CLI parameters.
- Compatibility with LambdaTest, BrowserStack, and Selenium Grid.

## 📊 Test Reports
- TestNG Surefire Reports: `target/surefire-reports`
- Extent HTML Reports: `reports/extent-report.html`

## 📋 Browser Support
- Chrome (local/remote)
- Firefox (local/remote)
- Cloud browsers via LambdaTest, BrowserStack, or Selenium Grid

## ⚙️ Test Configuration

| Parameter   | Purpose                      | Example               |
|-------------|------------------------------|-----------------------|
| browser     | Browser used for test runs   | chrome, firefox       |
| isRemote    | Enable remote WebDriver      | true, false           |
| url         | Target application base URL  | https://the-internet.herokuapp.com   |