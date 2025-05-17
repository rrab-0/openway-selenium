package org.example.pages.product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Product {
    private WebElement root;

    public Product(WebElement root) {
        this.root = root;
    }

    public String getName() {
        return root
            .findElement(By.tagName("h3"))
            .findElement(By.tagName("a"))
            .getText();
    }

    public WebElement getLink() {
        return root
            .findElement(By.className("product-img"))
            .findElement(By.tagName("a"));
    }
}
