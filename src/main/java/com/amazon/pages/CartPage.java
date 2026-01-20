package com.amazon.pages;

import com.amazon.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CartPage {
    private GUIDriver driver;

    //Constructor
    public CartPage(GUIDriver driver)
    {
        this.driver=driver;
    }

    //Locators

    private final By priceOfProductInCart= By.xpath("//span[contains(@class,'sc-product-price')]/span[@aria-hidden='true']");
    private final By quantityOfProductInCart=By.cssSelector("[data-a-selector='inner-value']");
    private final By subtotalPrice=By.xpath("//span[@id='sc-subtotal-amount-activecart']/span");

    //Validations

    @Step("Verify price in cart matches price of product")
    public CartPage verifyPriceInCartMatchesPriceOfProduct()
    {

        // Cart price (remove currency only)
        String cartPrice = driver.element()
                .getText(priceOfProductInCart)
                .replace("EGP", "")
                .trim();

        // Compare strings
        driver.verification()
                .Equals(cartPrice,ProductPage.productPrice ,
                        "The price in cart doesn't match the product price");

        return this;
    }

    @Step("Verify quantity in cart matches quantity of product")
    public CartPage verifyQuantityInCartMatchesQuantityOfProduct() {
       String actualQuantity = driver.element().getText(quantityOfProductInCart);

        driver.verification().Equals(actualQuantity,ProductPage.expectedQuantity , "The quantity in cart doesn't match the product quantity");
        return this;
    }

    @Step("Verify subtotal price is correct")
    public CartPage verifySubtotalPriceIsCorrect()
    {
        // Calculate expected subtotal
        double price = Double.parseDouble(ProductPage.productPrice);
        int quantity = Integer.parseInt(ProductPage.expectedQuantity);
        double expectedSubtotal = price * quantity;

        // Get actual subtotal from cart (remove currency only)
        String actualSubtotalText = driver.element()
                .getText(subtotalPrice)
                .replace("EGP", "")
                .trim();
        double actualSubtotal = Double.parseDouble(actualSubtotalText);

        // Compare values
        driver.verification()
                .assertTrue(actualSubtotal==expectedSubtotal,"The subtotal price is incorrect");

        return this;
    }

    }

