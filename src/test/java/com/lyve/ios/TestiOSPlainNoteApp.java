package com.lyve.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

public class TestiOSPlainNoteApp {

    final private static Logger log = Logger.getLogger(TestiOSPlainNoteApp.class);
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {

        try {

            File appDir = new File("/Users/mmadhusoodan/workspace/PlainNote/build/Release-iphonesimulator/");
            File app = new File(appDir, "PlainNote.app");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
            capabilities.setCapability("platformVersion", "8.1");
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPad 2");
            capabilities.setCapability("app", app.getAbsolutePath());

            driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void launchPlainNote() {

        //log.info(driver.getAppStrings());
        log.info("launchPlainNote");
    }

}
