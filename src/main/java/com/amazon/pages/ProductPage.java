package com.amazon.pages;

import com.amazon.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductPage {

    //Variables
    private GUIDriver driver;
    public static String productPrice;
    public static String expectedQuantity;

    //Constructor
    public ProductPage(GUIDriver driver)
    {
        this.driver=driver;
    }

    //Locators
    private  final By secondItem=By.cssSelector("[id='color_name_1']");
    private final By  addToCardButton=By.id("add-to-cart-button");
    private final  By priceWhole=By.className("a-price-whole");
    private final  By priceFraction=By.className("a-price-fraction");
    private final  By quantityDropdown=By.className("a-dropdown-prompt");




    //Actions
    @Step("Select the second item")
    public ProductPage selectSecondItem()
    {
        driver.element().click(secondItem);
        return this;
    }

    @Step("Clilck on the quantity dropdown")
    public ProductPage clickOnQuantityDropdown()
    {
        driver.element().click(quantityDropdown);
        return this;
    }

    @Step("Select Quantity option {qty}")
    public ProductPage SelectQuantityOption(int qty) {
        By option=By.xpath("//a[starts-with(@id,'quantity_') and normalize-space()='" + qty + "']");
        driver.element().click(option);
        return this;
    }

    @Step("Click on  Add to cart button")
    public ProductPage clickOnAddToCardButton()
    {
        driver.element().click(addToCardButton);
        return this;
    }

    @Step("Get the product price")
    public ProductPage getProductPrice()
    {
        String wholePrice = driver.element().getText(priceWhole).trim();
        String fractionPrice = driver.element().getText(priceFraction).trim();

        productPrice = wholePrice + "." + fractionPrice;
        return this;
    }

    @Step("Get the product quantity")
    public ProductPage  getProductQuantity()
    {
        expectedQuantity = driver.element().getText(quantityDropdown);
        return this;
    }


}
