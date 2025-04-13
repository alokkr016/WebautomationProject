# 🌐 Web Automation Testing Framework

## 🚀 Project Overview
A scalable and maintainable web automation testing framework built with Selenium WebDriver and TestNG. Designed for modern teams with features like parallel execution, AWS Parameter Store integration, and visual reporting via Extent Reports.

## 🔧 Prerequisites
- Java 17 or higher
- Maven
- LambdaTest or Selenium Grid setup (for remote execution)

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
- Extent HTML Reports: `target/extent-reports/index.html`

## 📋 Browser Support
- Chrome (local/remote)
- Firefox (local/remote)
- Cloud browsers via LambdaTest, BrowserStack, or Selenium Grid

## ⚙️ Test Configuration

| Parameter   | Purpose                      | Example               |
|-------------|------------------------------|-----------------------|
| browser     | Browser used for test runs   | chrome, firefox       |
| isRemote    | Enable remote WebDriver      | true, false           |
| url         | Target application base URL  | https://example.com   |