package org.example.pages.product;

import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SearchProductResultPage extends LoadableComponent<SearchProductResultPage> {
    private final WebDriver driver;

    public SearchProductResultPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
        try {
            WebElement pageTitle = driver.findElement(By.className("title"));
            if (!pageTitle.getText().equals("Filter Your Search")) Assert.fail("can't find SearchProductPage title");
        } catch (NoSuchElementException e) {
            Assert.fail("can't find SearchProductPage title: " + e.getMessage());
        }
    }

    public Product getFirstProductFound() {
        return new Product(driver.findElement(By.className("single-product")));
    }

    public ProductPage clickProductLink(Product product) {
        WebDriverWait wait = BasePage.waitUntilLoadingSpinnerIsInvisible(driver);

        wait.until(ExpectedConditions.elementToBeClickable(
                product.getLink()
        )).click();

        return new ProductPage(driver);
    }
}
