stack:

Java 21
Maven
JUnit 5
REST Assured
Selenium WebDriver
AssertJ
Jackson
DataFaker
Allure Report
GitHub Actions

(https://automationintesting.online/)
Escopo do MVP

Eu faria primeiro só API, para entregar rápido:

API-001 - Login com credenciais válidas
API-002 - Login com credenciais inválidas
API-003 - Listar quartos disponíveis
API-004 - Criar quarto com token válido
API-005 - Tentar criar quarto sem autenticação
API-006 - Criar reserva com dados válidos
API-007 - Criar reserva com payload inválido
API-008 - Validar contrato JSON da listagem de quartos

Depois adicionaria UI:

UI-001 - Acessar home pública
UI-002 - Validar exibição dos quartos
UI-003 - Acessar login admin
UI-004 - Login admin com sucesso
UI-005 - Login admin inválido

Isso mostra que você sabe automatizar:

API com REST Assured
UI com Selenium
Organização com Page Object Model
Execução com JUnit 5
Relatórios com Allure
Pipeline com GitHub Actions
Estrutura com Selenium
restful-booker-platform-java-tests/
├── src/
│   └── test/
│       ├── java/
│       │   └── br/com/portfolio/booker/
│       │       ├── api/
│       │       │   ├── AuthApiTest.java
│       │       │   ├── RoomApiTest.java
│       │       │   └── BookingApiTest.java
│       │       ├── ui/
│       │       │   ├── HomeTest.java
│       │       │   └── AdminLoginTest.java
│       │       ├── pages/
│       │       │   ├── HomePage.java
│       │       │   ├── LoginPage.java
│       │       │   └── AdminPage.java
│       │       ├── clients/
│       │       │   ├── AuthClient.java
│       │       │   ├── RoomClient.java
│       │       │   └── BookingClient.java
│       │       ├── factories/
│       │       │   ├── RoomFactory.java
│       │       │   └── BookingFactory.java
│       │       └── config/
│       │           ├── BaseApiTest.java
│       │           └── BaseUiTest.java
│       └── resources/
│           └── application.properties
├── .github/
│   └── workflows/
│       └── tests.yml
├── pom.xml
├── README.md
└── .gitignore
Dependências principais no pom.xml
<dependencies>
    <!-- API Tests -->
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>5.5.0</version>
        <scope>test</scope>
    </dependency>

    <!-- UI Tests -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.27.0</version>
        <scope>test</scope>
    </dependency>

    <!-- Test Runner -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.11.4</version>
        <scope>test</scope>
    </dependency>

    <!-- Assertions -->
    <dependency>
        <groupId>org.assertj</groupId>
        <artifactId>assertj-core</artifactId>
        <version>3.26.3</version>
        <scope>test</scope>
    </dependency>

    <!-- Fake data -->
    <dependency>
        <groupId>net.datafaker</groupId>
        <artifactId>datafaker</artifactId>
        <version>2.4.2</version>
        <scope>test</scope>
    </dependency>

    <!-- Report -->
    <dependency>
        <groupId>io.qameta.allure</groupId>
        <artifactId>allure-junit5</artifactId>
        <version>2.29.1</version>
        <scope>test</scope>
    </dependency>
</dependencies>

Hoje o Selenium também possui Selenium Manager, que ajuda no gerenciamento automático dos drivers dos navegadores, evitando parte daquele problema antigo de baixar chromedriver manualmente.

Exemplo de teste UI com Selenium
package br.com.portfolio.booker.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HomeTest extends BaseUiTest {

    @Test
    @DisplayName("Deve carregar a home pública")
    void shouldLoadHomePage() {
        driver.get("https://automationintesting.online");

        String pageText = driver.findElement(By.tagName("body")).getText();

        assertThat(pageText).contains("Welcome");
    }
}
Exemplo de Page Object
package br.com.portfolio.booker.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;

    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("doLogin");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        driver.findElement(usernameInput).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}
Escopo UI com Selenium

Eu começaria com poucos testes:

UI-001 - Deve carregar a home pública
UI-002 - Deve exibir quartos disponíveis
UI-003 - Deve acessar a tela de login admin
UI-004 - Deve realizar login admin com sucesso
UI-005 - Não deve realizar login com credenciais inválidas
UI-006 - Deve realizar logout
Veredito

Sim, usaria Selenium para UI nesse projeto Java.

A stack final ficaria muito boa para GitHub:

Java + JUnit 5 + REST Assured + Selenium WebDriver + Allure + GitHub Actions

Ela mostra uma base sólida de automação tanto para API quanto para interface web, usando tecnologias bem reconhecidas no mercado.