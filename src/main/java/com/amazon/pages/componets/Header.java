package com.amazon.pages.componets;

import com.amazon.drivers.GUIDriver;
import com.amazon.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.devtools.v139.heapprofiler.model.HeapSnapshotObjectId;

public class Header {

    private final GUIDriver driver;

    //Constructor
    public Header(GUIDriver driver)
    {
        this.driver=driver;
    }

    //Locators
    //private final By AccountAndLists= By.id("nav-link-accountList");
    private final By AccountAndLists= By.cssSelector("[data-csa-c-content-id='nav_ya_signin']");


    private final By signInButton=By.className("nav-action-inner");
    private  final By todayDealsLink= By.cssSelector("[data-csa-c-content-id='nav_cs_gb']");
    private final By cartIcon=By.id("nav-cart-count-container");
    private final By yourOrders =By.linkText("Your Orders");
    private final  By yourAddress=By.linkText("Your Addresses");
    private final By yourLists= By.linkText("Your Lists");




    //Actions
    @Step("Hover over Account and Lists")
    public Header hoverOverAccountAndLists()
    {
        driver.element().hover(AccountAndLists);
        return this;
    }

    @Step("Click on sign in button")
    public Header clickOnSignInButton()
    {
        driver.element().click(signInButton);
        return this;
    }

    @Step("Click on Today's Deal")
    public Header clickOnTodayDeal()
    {
        driver.element().click(todayDealsLink);
        return this;
    }



    @Step("Navigate to home page")
    public Header navigateToHomePage()
    {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb"));
        return this;
    }

    @Step("Click on cart icon")
    public Header clickOnCartIcon()
    {
        driver.element().click(cartIcon);
        return this;
    }

    @Step("Click on Your Orders")
    public  Header clickOnYourOrders()
    {
        driver.element().click(yourOrders);
        return  this;
    }

    @Step("Click on Your Addresses")
    public  Header clickOnYourAddress()
    {
        driver.element().click(yourAddress);
        return  this;
    }

    @Step("Click on Your Lists")
    public  Header clickOnYourLists()
    {
        driver.element().click(yourLists);
        return  this;
    }

    //Ve


}
