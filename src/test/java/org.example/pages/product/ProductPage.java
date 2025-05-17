package org.example.pages.product;

import org.example.pages.BasePage;
import org.example.pages.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ProductPage extends LoadableComponent<ProductPage> {
    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
        try {
            WebElement addToCartButton = driver.findElement(By.className("btn-add-to-cart"));
            if (!addToCartButton.getText().equals("ADD TO CART")) Assert.fail("can't find add to card button");
        } catch (NoSuchElementException e) {
            Assert.fail("can't find add to card button: " + e.getMessage());
        }
    }

    public ProductPage addProductToCart() {
        WebDriverWait wait = BasePage.waitUntilLoadingSpinnerIsInvisible(driver);

        wait.until(ExpectedConditions.elementToBeClickable(
                driver.findElement(By.className("btn-add-to-cart"))
        )).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                driver
                    .findElement(By.id("Notification-Modal"))
                    .findElement(By.className("btn-modal-close"))
        )).click();

        return this;
    }

    public CartPage goToCartPage() {
        driver.findElement(By.id("show-your-cart")).click();
        return new CartPage(driver);
    }
}
