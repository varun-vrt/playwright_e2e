package com.playwright.basetest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    @BeforeTest
    public void launchBrowser(){

    }

    @AfterMethod
    public void quitBrowser(){

    }
}