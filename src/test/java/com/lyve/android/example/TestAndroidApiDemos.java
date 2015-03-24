package com.lyve.android.example;

import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by mmadhusoodan on 2/18/15.
 */
public class TestAndroidApiDemos {

    final private static Logger log = Logger.getLogger(TestAndroidApiDemos.class);
    public static AndroidDriver driver;
    private static Date date = new Date();
    private String sessionId;

    @Before
    public void setUp() throws Exception {

        try {

            File appDir = new File("/Users/mmadhusoodan/APK/");
            File app = new File(appDir, "api.apk");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("appium-version", "1.1.0");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "Android");
            capabilities.setCapability("platformVersion", "4.3");

            // Set job name on Sauce Labs
            capabilities.setCapability("name", "Java Android tutorial " + date);
            capabilities.setCapability("app", app.getAbsolutePath());
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            sessionId = driver.getSessionId().toString();

            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Run after each test **/
    @After
    public void tearDown() throws Exception {
        if (driver != null) driver.quit();
    }

    @Test
    public void launchApiDemos() {

        //log.info(driver.getAppStrings());
        log.info("launchApiDemos");
    }

}
