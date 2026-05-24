package br.com.portfolio.booker.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By welcomeHeading = By.xpath("//h1[contains(text(),'Welcome')]");
    private final By bookingSection = By.id("booking");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void navigate(String baseUrl) {
        driver.get(baseUrl);
    }

    public String getPageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeHeading)).getText();
    }

    public boolean isRoomSectionVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(bookingSection)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
