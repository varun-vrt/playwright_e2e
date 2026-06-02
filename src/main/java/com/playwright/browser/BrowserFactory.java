package com.playwright.browser;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class BrowserFactory {
    Properties prop;

    public static ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<BrowserContext> browserContextThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<Page> pageThreadLocal =new ThreadLocal<>();

    public static Playwright getPlaywright(){
        return playwrightThreadLocal.get();
    }

    public static Browser getBrowser(){
        return browserThreadLocal.get();
    }

    public static BrowserContext getBrowserContext(){
        return browserContextThreadLocal.get();
    }

    public static Page getPage(){
        return pageThreadLocal.get();
    }

    public void initBrowser(String browserName) {
        playwrightThreadLocal.set(Playwright.create());
        switch (browserName.toLowerCase()){
            case "chrome":
                browserThreadLocal.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "firefox":
                browserThreadLocal.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "safari":
                browserThreadLocal.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            default:
                throw new RuntimeException("Not a supported browser type");
        }
    }

    public Page initSession(){
        browserContextThreadLocal.set(getBrowser().newContext());
        getBrowserContext().tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        pageThreadLocal.set(getBrowserContext().newPage());
        getPage().navigate(prop.getProperty("url"));
        return getPage();
    }

    public Properties initProperties() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/config/config.properties");
            prop = new Properties();
            prop.load(fileInputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return prop;
    }

    public void quitBrowser(){
        getBrowser().close();
        getPlaywright().close();
    }
}