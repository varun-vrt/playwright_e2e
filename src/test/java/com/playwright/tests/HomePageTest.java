package com.playwright.tests;

import com.playwright.basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void verifyTitle(){
        Assert.assertEquals(homePage.getTitle(),"Your Store");
    }
}
