package com.amazon;

import com.amazon.drivers.GUIDriver;
import com.amazon.pages.SignInPage;
import com.amazon.pages.WishListPage;
import com.amazon.pages.componets.Header;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Amazon")
@Story("Header Component Tests")
@Owner("Fatma Eltahan")
@Severity(SeverityLevel.CRITICAL)
public class HeaderTests extends  BaseTest {

    @Description("Verify that  the user can't see his order if he isn't logged in")
    @Test
    public void userCannotSeeHisOrderWhenHeIsNotLoggedInTC()
    {
        new Header(driver).navigateToHomePage()
                .hoverOverAccountAndLists()
                .clickOnYourOrders();

        new SignInPage(driver).verifyUserIsNavigatedToSignInPage("Sign in");


    }

    @Description("Verify that  the user can't see his address if he isn't logged in")
    @Test
    public void userCannotSeeHisAddressWhenHeIsNotLoggedInTC()
    {
        new Header(driver).navigateToHomePage()
                .hoverOverAccountAndLists()
                .clickOnYourAddress();

        new SignInPage(driver).verifyUserIsNavigatedToSignInPage("Sign in");


    }

    @Description("Verify the user can see his list if he isn't logged in")
    @Test
    public void userCanSeeHisListsWhenHeIsLoggedInTC()
    {
        new Header(driver).navigateToHomePage()
                .hoverOverAccountAndLists()
                .clickOnYourLists();

        new WishListPage(driver).verifyYourListsTabVisible();


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
