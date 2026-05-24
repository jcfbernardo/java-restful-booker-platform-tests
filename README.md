# Restful Booker Platform — Java Test Automation

Automated test suite for [automationintesting.online](https://automationintesting.online/) built as a portfolio project.

## Stack

- Java 21
- Maven
- JUnit 5
- REST Assured
- Selenium WebDriver (with Selenium Manager)
- AssertJ
- Jackson
- DataFaker
- Allure Report
- GitHub Actions

## Test Scope

### API Tests
| ID | Description |
|----|-------------|
| API-001 | Login with valid credentials |
| API-002 | Login with invalid credentials |
| API-003 | List available rooms |
| API-004 | Create room with valid token |
| API-005 | Attempt to create room without auth |
| API-006 | Create booking with valid data |
| API-007 | Create booking with invalid payload |
| API-008 | Validate JSON contract of room listing |

### UI Tests
| ID | Description |
|----|-------------|
| UI-001 | Load public home page |
| UI-002 | Display available rooms |
| UI-003 | Access admin login page |
| UI-004 | Admin login with valid credentials |
| UI-005 | Admin login with invalid credentials |

## Prerequisites

- Java 21+
- Maven 3.9+
- Google Chrome (latest stable)

Selenium Manager (bundled in selenium-java 4.6+) downloads chromedriver automatically — no manual setup needed.

## Running Tests

```bash
# Run all tests (headless by default)
mvn test

# Run headful locally
mvn test -Dbrowser.headless=false

# Run a specific test class
mvn test -Dtest=AuthApiTest

# Run only API tests
mvn test -Dtest="AuthApiTest,RoomApiTest,BookingApiTest"
```

## Allure Report

```bash
# Generate HTML report after mvn test
mvn allure:report

# Open report in browser (starts a local server)
mvn allure:serve
```

Report is published to GitHub Pages on every push to `main`.

## Project Structure

```
src/test/java/br/com/portfolio/booker/
├── api/          # API test classes
├── ui/           # UI test classes
├── pages/        # Page Object Model
├── clients/      # REST Assured API clients
├── factories/    # Test data factories (DataFaker)
├── models/       # JSON POJOs
└── config/       # Base test classes
```
