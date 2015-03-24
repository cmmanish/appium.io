package com.lyve.ios;

/**
 * Created by mmadhusoodan on 2/17/15.
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 *
 * Simple test which demonstrates how util test can be run against Mobile Safari running on an Appium instance.
 *
 * The test is based on https://github.com/appium/appium/blob/master/sample-code/examples/node/safari.js
 *
 * @author Ross Rowe
 */
public class TestiPhone5Safari {

    private WebDriver driver;

    /**
     * Instantiates the {@link #driver} instance by using DesiredCapabilities which specify the
     * 'iPhone Simulator' device and 'safari' app.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "iPhone 5");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("browserName", "safari");
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"),
                capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    /**
     * Navigates to http://saucelabs.com/test/guinea-pig and interacts with the browser.
     *
     * @throws Exception
     */
    @Test
    public void runTest() throws Exception {
        driver.get("https://wwwfront.dogfood.blackpearlsystems.net/");
        Thread.sleep(1000);
        System.out.println(driver.getCurrentUrl());
    }

    @Test
    public void T1TestLaunchBrowser() {

        try {
            driver.navigate().to("https://wwwfront.test.blackpearlsystems.net/");
            System.out.println("TestLaunchBrowser: " + driver.getTitle());
            assertEquals("Collect, Protect, and Rediscover Your Photos and Videos | Lyve", driver.getTitle());

            if (driver instanceof JavascriptExecutor) {
                ((JavascriptExecutor) driver).executeScript("alert('hello world');");
            }
        } catch (Exception ne) {
            ne.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    /**
     * Closes the {@link #driver} instance.
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}