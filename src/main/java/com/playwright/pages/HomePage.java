package com.playwright.pages;

import com.microsoft.playwright.Page;

public class HomePage{
    Page page;

    public HomePage(Page page){
        this.page = page;
    }

    public String getTitle(){
        return page.title();
    }

}