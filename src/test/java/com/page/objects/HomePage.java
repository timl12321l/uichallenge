package com.page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage extends Page {

    private static final String url = "https://cneos.jpl.nasa.gov/sentry/";
    private By dropDownDisplayNum = new By.ByName("riskTable_length");
    private By tableElementContainingLinks = new By.ById("riskTable");

    private Select displayNumMenu;


    public HomePage(final WebDriver driver)
    {
        super(driver);
    }

    public void goToPage()
    {
        super.driver.get("https://cneos.jpl.nasa.gov/sentry/");
    }

    public String getTitle()
    {
        goToPage();
        return driver.getTitle();
    }

    public Select getDisplayNumMenu()
    {
        displayNumMenu =  new Select(super.driver.findElement(dropDownDisplayNum));
        return displayNumMenu;
    }

    public WebElement getFirstSelectedDisplayNumMenuElement()
    {
        return displayNumMenu.getFirstSelectedOption();
    }

    public List<WebElement> getEntryLinkElements()
    {
        WebElement tableElement = super.driver.findElement(tableElementContainingLinks);
        List<WebElement> linkElements = tableElement.findElements(By.tagName("a"));
        return linkElements;
    }

}
