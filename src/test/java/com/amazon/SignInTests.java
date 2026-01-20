package com.amazon;

import com.amazon.drivers.GUIDriver;
import com.amazon.pages.SignInPage;
import com.amazon.pages.componets.Header;
import com.amazon.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Amazon")
@Story("User Sign In")
@Owner("Fatma Eltahan")
@Severity(SeverityLevel.CRITICAL)
public class SignInTests extends BaseTest {

    @Description("Verify that user can't login with  a valid unregistered email")
    @Test
    public void signInWithValidUnregisteredEmailTC()
    {
        new SignInPage(driver).enterEmail(signIn_data.getJsonData("validUnregisteredEmail"))
                .clickOnContinueButton()
                .verifyCannotLoginWithUnregisteredEmail(signIn_data.getJsonData("primaryTitle"));
    }

    @Step("Setting up preconditions ")
    @BeforeClass
    public void preConditions()
    {
        signIn_data=new JsonReader("signIn-data");
        driver=new GUIDriver();
        new Header(driver).navigateToHomePage()
                .hoverOverAccountAndLists()
                .clickOnSignInButton();
    }


    @Step("Tearing down the driver")
    @AfterClass
    public void tearDown()
    {
        driver.quitDriver();

    }

}
