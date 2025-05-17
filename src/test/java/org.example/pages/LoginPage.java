package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage extends LoadableComponent<LoginPage> {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
        try {
            WebElement title = driver.findElement(By.className("signin_main"));
            if (!title.getText().equals("Sign In to Your Account")) Assert.fail("can't find LoginPage title");
        } catch (NoSuchElementException e) {
            Assert.fail("can't find LoginPage title: " + e.getMessage());
        }
    }

    public AccountPage login(String email, String password) {
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.id("button-login")).click();
        return new AccountPage(driver);
    }
}
