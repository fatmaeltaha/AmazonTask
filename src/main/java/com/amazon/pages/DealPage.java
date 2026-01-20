package com.amazon.pages;

import com.amazon.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.security.Guard;

public class DealPage {
    private GUIDriver driver;

    //Constructor
    public DealPage(GUIDriver driver)
    {
        this.driver=driver;
    }

    //Locators
    private final By endsToNightCategory= By.cssSelector("[data-csa-c-element-id='filter-bubble-discounts-collection-deals-ending-today']");
    private final By firstEndTonightProduct=By.cssSelector("div[data-testid='product-card'][data-csa-c-posx='1']");

    //Actions
    @Step("Click on Ends Tonight category")
    public DealPage clickOnEndsToNightCategory()
    {
        driver.element().click(endsToNightCategory);
        return this;
    }

    @Step("Click on the first product in Ends tonight category")
    public DealPage clickOnFirstEndTonightProduct()
    {
        driver.element().click(firstEndTonightProduct);
        return this;
    }



}
