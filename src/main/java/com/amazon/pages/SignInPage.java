package com.amazon.pages;

import com.amazon.drivers.GUIDriver;
import com.amazon.pages.componets.Header;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignInPage {
    private final GUIDriver driver;

    //Constructor
    public SignInPage(GUIDriver driver)
    {
        this.driver=driver;
    }


    //Locators
    private  final By email=By.id("ap_email_login");
    private final By continueButton=By.className("a-button-input");
   private final By   titleOfUnsignInPage =  By.xpath("//h1");
   private final By titleOfSigInPage=By.xpath("//h1");






    //Actions
    @Step("Enter  a valid registered email")
    public SignInPage enterEmail(String email)
    {
        driver.element().type(this.email,email);
        return this;
    }

    @Step("Click on Continue Button")
    public SignInPage clickOnContinueButton()
    {
        driver.element().click(continueButton);
        return this;
    }

    //Validations

    @Step("Verify user can't Sign in or logiin with a valided unregistered email" )
    public boolean verifyCannotLoginWithUnregisteredEmail(String expectedTitle)
    {
        String actualTitle=driver.element().getText(titleOfUnsignInPage);
    return actualTitle.equalsIgnoreCase(expectedTitle);}


    @Step("Verify user is navigated to sign in page")
    public SignInPage verifyUserIsNavigatedToSignInPage(String expectedTitle)
    {
        //driver.verification().assertPageTitle(expectedTitle);
        String actualTitle=driver.element().getText(titleOfSigInPage);
        driver.verification().assertTrue(actualTitle.contains(expectedTitle),"User is not navigated to Sign In Page");
        return this;
    }




}
