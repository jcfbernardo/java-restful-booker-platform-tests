package br.com.portfolio.booker.api;

import br.com.portfolio.booker.clients.AuthClient;
import br.com.portfolio.booker.config.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Authentication API")
@Feature("Login")
class AuthApiTest extends BaseApiTest {

    private final AuthClient authClient = new AuthClient();

    @Test
    @Story("API-001")
    @DisplayName("Should return token on valid login")
    @Severity(SeverityLevel.BLOCKER)
    void shouldReturnTokenOnValidLogin() {
        var response = authClient.login(
                props.getProperty("admin.username"),
                props.getProperty("admin.password")
        );

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("token")).isNotBlank();
    }

    @Test
    @Story("API-002")
    @DisplayName("Should return 403 on invalid login")
    @Severity(SeverityLevel.CRITICAL)
    void shouldReturn403OnInvalidLogin() {
        var response = authClient.login("wronguser", "wrongpass");

        assertThat(response.statusCode()).isEqualTo(403);
    }
}
