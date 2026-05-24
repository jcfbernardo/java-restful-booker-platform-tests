package br.com.portfolio.booker.ui;

import br.com.portfolio.booker.config.BaseUiTest;
import br.com.portfolio.booker.pages.HomePage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("UI - Public Pages")
@Feature("Home Page")
class HomeTest extends BaseUiTest {

    private HomePage homePage;

    @BeforeEach
    void setupPage() {
        homePage = new HomePage(driver);
    }

    @Test
    @Story("UI-001")
    @DisplayName("Should load public home page")
    void shouldLoadHomePage() {
        homePage.navigate(props.getProperty("base.url"));

        assertThat(homePage.getPageText()).containsIgnoringCase("Welcome");
    }

    @Test
    @Story("UI-002")
    @DisplayName("Should display available rooms")
    void shouldDisplayAvailableRooms() {
        homePage.navigate(props.getProperty("base.url"));

        assertThat(homePage.isRoomSectionVisible()).isTrue();
    }
}
