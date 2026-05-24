package br.com.portfolio.booker.ui;

import br.com.portfolio.booker.config.BaseUiTest;
import br.com.portfolio.booker.pages.AdminPage;
import br.com.portfolio.booker.pages.LoginPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("UI - Admin")
@Feature("Admin Login")
class AdminLoginTest extends BaseUiTest {

    private LoginPage loginPage;
    private AdminPage adminPage;

    @BeforeEach
    void setupPages() {
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
    }

    @Test
    @Story("UI-003")
    @DisplayName("Should access admin login page")
    void shouldAccessAdminLoginPage() {
        loginPage.navigate(props.getProperty("base.url"));

        assertThat(loginPage.isLoginFormVisible()).isTrue();
    }

    @Test
    @Story("UI-004")
    @DisplayName("Should login as admin successfully")
    void shouldLoginAsAdminSuccessfully() {
        loginPage.navigate(props.getProperty("base.url"));
        loginPage.login(
                props.getProperty("admin.username"),
                props.getProperty("admin.password")
        );

        assertThat(adminPage.isDashboardVisible()).isTrue();
    }

    @Test
    @Story("UI-005")
    @DisplayName("Should show error on invalid credentials")
    void shouldShowErrorOnInvalidCredentials() {
        loginPage.navigate(props.getProperty("base.url"));
        loginPage.login("wronguser", "wrongpass");

        assertThat(loginPage.isErrorMessageVisible()).isTrue();
    }
}
