package com.playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.ArrayList;
import java.util.List;

public class HomePage{
    Page page;
    private final String shopByCategory = "";

    public HomePage(Page page){
        this.page = page;
    }

    public String getTitle(){
        return page.title();
    }

    public void openShopByCategory() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Shop by Category")).click();
    }

    public Locator listOfCategories(){
        return  page.getByRole(AriaRole.NAVIGATION).filter(new Locator.FilterOptions().setHasText("Components Cameras Phone,")).locator("a");
    }
}