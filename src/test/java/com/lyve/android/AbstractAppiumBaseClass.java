package com.lyve.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by mmadhusoodan on 2/19/15.
 */
public class AbstractAppiumBaseClass {

    private AppiumDriver driver;
    protected String Screenshotpath = "screenshot/";


    @Before
    public void setUp() throws Exception {

        File userDir = new File(System.getProperty("user.dir"));
        File appDir = new File(userDir, "/APK/");
        File app = new File(appDir, "avery-debug-armeabi-v7a-2.2.0.1725.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Nexus7");
        capabilities.setCapability("platformVersion", "4.4");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.lyveminds.lyve");
        capabilities.setCapability("appActivity", ".StartupActivity");
        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        } catch (SessionNotCreatedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitForElementPresent(By by) {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        try {
            driver.findElement(by);
        } catch (org.openqa.selenium.NoSuchElementException e) {
        }
    }
}
