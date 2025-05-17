package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class HomePage extends LoadableComponent<HomePage> {
    private WebDriver driver;
    private String pageLink = "https://www.periplus.com/";

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void load() {
        driver.get(pageLink);
    }

    @Override
    protected void isLoaded() throws Error {
        try {
            WebElement signInLink = driver.findElement(By.linkText("Sign In"));
            if (!signInLink.getText().equals("Sign In")) Assert.fail("can't find HomePage 'Sign In' link");
        } catch (NoSuchElementException e) {
            Assert.fail("can't find HomePage 'Sign In' link: " + e.getMessage());
        }
    }

    public LoginPage goToLoginPage() {
        driver.findElement(By.linkText("Sign In")).click();
        return new LoginPage(driver);
    }
}
