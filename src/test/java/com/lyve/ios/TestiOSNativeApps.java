package com.lyve.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by mmadhusoodan on 2/17/15.
 */

/**
 * Simple test which demonstrates how util test can be run against Mobile Safari running on an Appium instance.
 *
 * @author Manish Madhusoodan
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestiOSNativeApps {

    final private static Logger log = Logger.getLogger(TestiOSNativeApps.class);
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "iPhone 5");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("app", "settings");

        try {
            driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Navigates to http://saucelabs.com/test/guinea-pig and interacts with the browser.
     *
     * @throws Exception
     */
    @Test
    public void runTest() throws Exception {
        log.info(driver.getCapabilities());
        log.info(driver.getAppStrings("en"));
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}