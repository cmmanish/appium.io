package com.lyve.android.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AndroidNativeAppsTest {

    final private static Logger log = Logger.getLogger(AndroidNativeAppsTest.class);
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "com.android.settings");
        capabilities.setCapability("appActivity", "Settings");

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
    }

    @Test
    public void T1scrollToSettings() throws Exception {
        driver.scrollTo("About ");

    }

    @Test
    public void T2launchMenu() {

//        driver.sendKeyEvent(AndroidKeyCode.MENU);
//        driver.sendKeyEvent(AndroidKeyCode.BACK);
//        driver.sendKeyEvent(AndroidKeyCode.HOME);
    }

//    @Test
//    public void T3testScreenshot() {
//        //make screenshot and get is as base64
//        WebDriver augmentedDriver = new Augmenter().augment(driver);
//        String screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BASE64);
//
//        assertNotNull(screenshot);
//        //make screenshot and save it to the local filesystem
//        File file = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
//        assertNotNull(file);
//    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}