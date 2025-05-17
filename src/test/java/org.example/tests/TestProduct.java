package org.example.tests;

import org.example.config.ConfigProperties;
import org.example.pages.*;
import org.example.pages.product.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestProduct {
    private String email = "";
    private String password = "";
    private WebDriver driver;

    @BeforeClass
    public void classSetup() {
        ConfigProperties.setup();
        email = ConfigProperties.properties.getProperty("periplus_email");
        password = ConfigProperties.properties.getProperty("periplus_password");
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test(description = "able to add product to cart")
    public void ableToAddProductToCart() {
        AccountPage accountPage = new HomePage(driver).get()
                .goToLoginPage().get()
                .login(email, password).get();

        String productName = "dune";
        SearchProductResultPage searchProductResultPage = accountPage
                .searchProduct(productName).get();

        Product foundProduct = searchProductResultPage.getFirstProductFound();
        String foundProductName = foundProduct.getName();

        CartPage cartPage = searchProductResultPage
                .clickProductLink(foundProduct).get()
                .addProductToCart()
                .goToCartPage().get();

        Assert.assertTrue(cartPage.isProductInCart(foundProductName));

        ///

        // // go to homepage
        // HomePage homePage = new HomePage(driver);
        // homePage.get();
        //
        // // click Sign In
        // LoginPage loginPage = homePage.goToLoginPage();
        // loginPage.get();
        //
        // // sign in
        // AccountPage accountPage = loginPage.login(email, password);
        // accountPage.get();
        //
        // // search product by name
        // String productName = "dune";
        // SearchProductPage searchProductPage = accountPage.searchProduct(productName);
        // searchProductPage.get();
        //
        // // click the link of the first product found
        // Product foundProduct = searchProductPage.getFirstProductFound();
        // String foundProductName = foundProduct.getName();
        // ProductPage productPage = searchProductPage.clickProductLink(foundProduct);
        //
        // // click add to cart
        // productPage.addProductToCart();
        //
        // // go to cart page
        // CartPage cartPage = productPage.goToCartPage();
        // cartPage.get();
        //
        // Assert.assertTrue(cartPage.isProductInCart(foundProductName));
    }
}