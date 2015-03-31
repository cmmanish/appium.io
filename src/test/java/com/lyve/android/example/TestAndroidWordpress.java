package com.lyve.android.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.io.File;
import java.net.URL;

/**
 * Created by mmadhusoodan on 3/25/15.
 */
public class TestAndroidWordpress {

    final private static Logger log = Logger.getLogger(TestAndroidWordpress.class);
    private AppiumDriver driver;
    private static final String url = "http://127.0.0.1:4723/wd/hub";

    @Before
    public void setUp() throws Exception {

        try {

            File appDir = new File("/Users/mmadhusoodan/APK/");
            File app = new File(appDir, "org.wordpress.android-3.6-160-minAPI14.apk");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
            capabilities.setCapability("deviceName", "Android");
            capabilities.setCapability("platformVersion", "4.4.2");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("app", app.getAbsolutePath());

            capabilities.setCapability("appPackage", "org.wordpress.android");
            capabilities.setCapability("appActivity", ".Main");
            capabilities.setCapability("appWaitActivity", ".Dashboard");
            capabilities.setCapability("takesScreenshot", true);
            driver = new AndroidDriver(new URL(url), capabilities);

        } catch (UnreachableBrowserException server) {
            server.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Run after each test *
     */
    @After
    public void tearDown() throws Exception {
        if (driver != null) driver.quit();
    }

    @Test
    public void testUI() {

        log.info("launchWordPress");
    }
}
