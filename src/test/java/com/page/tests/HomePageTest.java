package com.page.tests;

import com.page.objects.Page;
import com.page.objects.HomePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class HomePageTest
{
    public WebDriver driver;

    @BeforeTest
    public void beforeTest()
    {
        System.setProperty("webdriver.gecko.driver","./driver/geckodriver");
        this.driver = new FirefoxDriver();
        this.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        this.driver.manage().deleteAllCookies();
    }

    @Test(priority = 1)
    public void testHomePageExist()
    {
        Page homePage = new HomePage(driver);
        String title = ((HomePage) homePage).getTitle();
        Assert.assertEquals(title,"Sentry: Earth Impact Monitoring");
    }


    //Test Drop down should display 4 options, i.e. 10, 25, 50 and 75
    @Test(priority = 2)
    public void testDropDownMenuEntries()
    {
        Page homePage = new HomePage(driver);
        Select displayNumMenu = ((HomePage) homePage).getDisplayNumMenu();
        List<WebElement> options = displayNumMenu.getOptions();

        List<String> actualEntryValues = new ArrayList<>();

        for (WebElement element : options)
        {
            actualEntryValues.add(element.getText());
        }
        Assert.assertEquals(actualEntryValues.toString(), "[10, 25, 50, 75]");
    }

    //Test 25 entries should be selected by default
    @Test(priority = 3)
    public void testDefaultEntry()
    {
        Page homePage = new HomePage(driver);
        Select displayNumMenu = ((HomePage) homePage).getDisplayNumMenu();
        WebElement firstSelectedOptionElement = displayNumMenu.getFirstSelectedOption();
        int defaultEntry = Integer.parseInt(firstSelectedOptionElement.getText());
        Assert.assertEquals(defaultEntry, 25);
    }


    //Validate the the value in query paramter to the opened page after clicking entry
    @Test(priority=4)
    public void testEntryToPageClick() throws FileNotFoundException, IOException, InterruptedException
    {

        Thread.sleep(500);
        Page homePage = new HomePage(driver);
        List<WebElement> linkElements = ((HomePage) homePage).getEntryLinkElements();


        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        List<String> hrefs = new ArrayList<String>();
        List<String> expectedText = new ArrayList<>();

        for (WebElement linkElement : linkElements)
        {
            String text = linkElement.getText();
            if (!text.contains(".")){
                String href = linkElement.getAttribute("href");
                hrefs.add(href);
                expectedText.add(linkElement.getText());
            }
        }

        int count = 0;
        for (String href : hrefs) {
            driver.switchTo().window(tabs.get(1));
            driver.get(href);
            driver.navigate().refresh();
            Thread.sleep(1000);
            String url=driver.getCurrentUrl();
            String actual = url.substring(url.indexOf("des=")+4, url.length());
            String expectedStr = expectedText.get(count++).toString().replace('(',' ');
            String expected = expectedStr.substring(0,expectedStr.indexOf(' '));
            Assert.assertTrue(href.contains(expected));
        }

    }

    @AfterTest
    public void afterTest()
    {
        driver.quit();
    }

}
