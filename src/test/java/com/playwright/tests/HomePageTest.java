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
        assertThat(homePage.listOfCategories()).hasText(new String[]{"Components" ,"Cameras" ,"Phone, Tablets & Ipod" ,"Software" ,"MP3 Players" ,"Laptops & Notebooks" ,"Desktops and Monitors" ,"Printers & Scanners" ,"Mice and Trackballs" ,"Fashion and Accessories" ,"Beauty and Saloon" ,"Autoparts and Accessories" ,"Washing machine" ,"Gaming consoles" ,"Air conditioner" ,"Web Cameras"});
    }
}
