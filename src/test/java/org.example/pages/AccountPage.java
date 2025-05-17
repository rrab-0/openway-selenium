package org.example.pages;

import org.example.pages.product.SearchProductResultPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class AccountPage extends LoadableComponent<AccountPage>  {
    private WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
        try {
            WebElement title = driver.findElement(By.linkText("Account Details"));
            if (!title.getText().equals("Account Details")) Assert.fail("can't find AccountPage title");
        } catch (NoSuchElementException e) {
            Assert.fail("can't find AccountPage title: " + e.getMessage());
        }
    }

    public SearchProductResultPage searchProduct(String filterName) {
        WebElement searchInput = driver.findElement(By.id("filter_name"));
        searchInput.sendKeys(filterName);
        searchInput.submit();
        return new SearchProductResultPage(driver);
    }
}
