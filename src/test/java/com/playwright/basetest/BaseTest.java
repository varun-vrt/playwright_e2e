package com.playwright.basetest;

import com.microsoft.playwright.Page;
import com.playwright.browser.BrowserFactory;
import com.playwright.pages.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
    BrowserFactory bf;
    Page page;
    protected HomePage homePage;

    public BaseTest(){
        bf = new BrowserFactory();
    }

    @BeforeMethod
    @Parameters("browser")
    public void launchBrowser(String browser){
        bf.initProperties();
        page = bf.initBrowser("chrome");
        homePage = new HomePage(page);
    }

    @AfterTest
    public void quitBrowser(){
        bf.quitBrowser();
    }
}