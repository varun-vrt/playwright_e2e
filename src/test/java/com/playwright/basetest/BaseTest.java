package com.playwright.basetest;

import com.microsoft.playwright.Page;
import com.playwright.browser.BrowserFactory;
import com.playwright.pages.HomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.FileNotFoundException;
import java.util.Properties;

public class BaseTest {
    BrowserFactory bf;
    Page page;
    protected HomePage homePage;

    @BeforeTest
    @Parameters("browser")
    public void launchBrowser(String browser){
        bf.initProperties();
        page = bf.initBrowser("chrome");
        homePage = new HomePage(page);
    }

    @AfterMethod
    public void quitBrowser(){
        bf.quitBrowser();
    }
}