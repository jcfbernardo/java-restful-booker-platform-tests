package br.com.portfolio.booker.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdminPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // After login the app redirects to /admin/rooms which shows the rooms management table
    private final By roomsTableHeader = By.xpath("//p[text()='Room #']");
    private final By logoutButton     = By.xpath("//button[text()='Logout']");

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isDashboardVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(roomsTableHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
}
