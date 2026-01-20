package com.amazon;

import com.amazon.drivers.GUIDriver;
import com.amazon.pages.CartPage;
import com.amazon.pages.DealPage;
import com.amazon.pages.ProductPage;
import com.amazon.pages.componets.Header;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Amazon")
@Story("Add product to Cart")
@Owner("Fatma Eltahan")
@Severity(SeverityLevel.CRITICAL)
public class CartPageTests extends  BaseTest {

    @Description("Verify the user can add products to the cart correctly")
    @Test
    public void addProductsToCartTC()
    {
        new Header(driver).navigateToHomePage()
                .clickOnTodayDeal();
        new DealPage(driver)
                .clickOnEndsToNightCategory()
                .clickOnFirstEndTonightProduct();
        new ProductPage(driver)
                .clickOnQuantityDropdown()
                .SelectQuantityOption(2)
                .getProductPrice()
                .getProductQuantity()
                .clickOnAddToCardButton();
        new Header(driver).clickOnCartIcon();
       new CartPage(driver).verifyPriceInCartMatchesPriceOfProduct()
               .verifyQuantityInCartMatchesQuantityOfProduct()
               .verifySubtotalPriceIsCorrect();

    }

    @Step("Set up")
    @BeforeClass
    public void setups()
    {
        driver=new GUIDriver();

    }


    @Step("Tearing down the driver")
    @AfterClass
    public void tearDown()
    {
      driver.quitDriver();
    }



}
