package com.amazon.pages;

import com.amazon.drivers.GUIDriver;
import org.openqa.selenium.By;

public class WishListPage {
    private GUIDriver driver;

    //Constructor
    public WishListPage(GUIDriver driver)
    {
        this.driver=driver;
    }

    //Locators
    private final By yourListsTab= By.id("my-lists-tab");

    // Verification
    public WishListPage verifyYourListsTabVisible()
    {
        driver.verification().isElementVisible(yourListsTab);
        return this;
    }
}
