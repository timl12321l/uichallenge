package com.page.objects;

import org.openqa.selenium.WebDriver;

public abstract class Page {

    protected WebDriver driver;

    public Page()
    {

    }

    public Page(final WebDriver driver){
        this.driver = driver;
    }

    public WebDriver getWebDriver()
    {
        return this.driver;
    }


}
