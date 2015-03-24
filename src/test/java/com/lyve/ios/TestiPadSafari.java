package com.lyve.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 *
 * Simple test which demonstrates how util test can be run against Mobile Safari running on an Appium instance.
 *
 * The test is based on https://github.com/appium/appium/blob/master/sample-code/examples/node/safari.js
 *
 * @author Ross Rowe
 */
public class TestiPadSafari {

    final private static Logger log = Logger.getLogger(TestiPadSafari.class);
    private static final String url = "http://127.0.0.1:4723/wd/hub";
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {

        // set up appium
        //File appDir = new File(System.getProperty("user.dir"), "../../../apps/TestApp/build/Release-iphonesimulator");
        //File app = new File(appDir, "TestApp.app");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability(CapabilityType.BROWSER_NAME, "iOS");

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("deviceName", "iPad Air");

        driver = new IOSDriver(new URL(url), capabilities);

    }

    @Test
    public void runTest() throws Exception {
        driver.navigate().to("https://wwwfront.test.blackpearlsystems.net/");
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

    @Test
    public void T2TestjQuery() {
        String query = "$('util:contains(\"Sign in\")')[0].click()";

        try {
            driver.navigate().to("https://wwwfront.dogfood.blackpearlsystems.net");
            System.out.println("TestjQuery: " + driver.getTitle());
            String retval = (String) ((JavascriptExecutor) driver).executeScript(query);
            wait(5000);
            log.info(driver.getTitle());
            assertEquals("Lyve: Sign In", driver.getTitle());
            driver.manage().window().maximize();
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
