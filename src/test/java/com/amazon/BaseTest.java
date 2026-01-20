package com.amazon;

import com.amazon.drivers.GUIDriver;
import com.amazon.drivers.WebDriverProvider;
import com.amazon.utils.dataReader.JsonReader;
import org.openqa.selenium.WebDriver;

public class BaseTest implements WebDriverProvider {

    protected GUIDriver driver;
    protected JsonReader signIn_data;

    @Override
    public WebDriver getWebDriver() {
        return driver.get();
    }

}
