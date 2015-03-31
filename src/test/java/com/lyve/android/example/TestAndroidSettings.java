package com.lyve.android.example;

import com.google.gson.JsonParser;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestAndroidSettings {

    final private static Logger log = Logger.getLogger(TestAndroidSettings.class);
    private AppiumDriver driver;
    private static final String url = "http://127.0.0.1:4723/wd/hub";
    private static final HttpClient client = HttpClients.createDefault();
    private static final JsonParser parser = new JsonParser();

    @Before
    public void setUp() throws Exception {
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("appPackage", "com.android.settings");
        capabilities.setCapability("appActivity", ".Settings");
        capabilities.setCapability("takesScreenshot", true);
        driver = new AndroidDriver(new URL(url), capabilities);
    }

    @Test
    public void T1CleanLyveCacheAndUserData() throws Exception {
        try {
            driver.scrollTo("Apps").click();
            MobileElement me = driver.scrollTo("Lyve");
            me.click();
            log.info(me.getTagName());
            log.info(me.getText());
            me.click();

        } catch (NoSuchElementException nse) {
            nse.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}