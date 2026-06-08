package com.playwright.basetest;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import com.playwright.browser.BrowserFactory;
import com.playwright.pages.HomePage;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import static com.playwright.browser.BrowserFactory.getBrowserContext;

public class BaseTest {
    BrowserFactory bf;
    Page page;
    protected HomePage homePage;

    public BaseTest(){
        bf = new BrowserFactory();
    }

    @BeforeSuite
    public void cleanupPreviousRun() throws IOException {
        Path traces = Paths.get("target/traces");
        if (Files.exists(traces)){
            Files.walk(traces).sorted(Comparator.reverseOrder()).forEach(path -> {
                try {
                    Files.deleteIfExists(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }


    @BeforeClass
    @Parameters("browser")
    public void initializeBrowser(String browser){
        bf.initProperties();
        bf.initBrowser("chrome");
    }

    @BeforeMethod
    public void initSession(){
        page = bf.initSession();
        homePage = new HomePage(page);
    }

    @AfterMethod
    public void tearDownContext(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String testName = result.getMethod().getMethodName();
            getBrowserContext().tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("target/traces/" + testName + "-trace.zip")));
        } else {
            getBrowserContext().tracing().stop();
        }
        getBrowserContext().close();
    }

    @AfterTest
    public void quitBrowser(){
        bf.quitBrowser();
    }

}