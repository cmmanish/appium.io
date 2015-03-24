package com.lyve.lyveHome;

/**
 * Created by mmadhusoodan on 2/17/15.
 */

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class TestLyvehome {

    private AppiumDriver driver;
    private static final String url = "http://127.0.0.1:4723/wd/hub";


    @Before
    public void setUp() throws Exception {
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("automationName", "Selendroid");
        //capabilities.setCapability("device", "selendroid");
        capabilities.setCapability("deviceName", "HTC");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "com.android.settings");
        capabilities.setCapability("appActivity", ".Settings");
        driver = new AndroidDriver(new URL(url), capabilities);
    }

    @Test
    public void apiDemo() throws Exception {
        driver.scrollTo("about tablet");
        driver.scrollTo("Location Access");
        driver.scrollTo("Bluetooth");
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
