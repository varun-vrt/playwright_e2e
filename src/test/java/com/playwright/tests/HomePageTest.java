package com.playwright.tests;

import com.playwright.basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePageTest extends BaseTest {

    @Test
    public void verifyTitle(){
        Assert.assertEquals(homePage.getTitle(),"Your Store");
    }

    @Test
    public void verifyCategories(){
        homePage.openShopByCategory();
        System.out.println(homePage.listOfCategories());
    }
}
