package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class CartPage extends LoadableComponent<CartPage> {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
        try {
            WebElement title = driver
                    .findElement(By.className("active"))
                    .findElement(By.tagName("a"));
            if (!title.getText().equals("Shopping Cart")) {
                Assert.fail("can't find CartPage title");
            }
        } catch (NoSuchElementException e) {
            Assert.fail("can't find CartPage title: " + e.getMessage());
        }
    }

    public boolean isProductInCart(String productName) {
        try {
            String foundProductName = driver
                .findElement(By.className("product-name"))
                .findElement(By.tagName("a"))
                .getText();
            System.out.println("expected product: " + productName);
            System.out.println("cart product: " + foundProductName);
            return foundProductName.equals(productName);
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
