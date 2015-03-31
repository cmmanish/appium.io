package com.lyve.android.example;

/**
 * Created by mmadhusoodan on 3/28/15.
 */

import io.appium.java_client.android.AndroidDriver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class TestAndroidSample {

    public AndroidDriver driver;
    private String appPackage = "com.lyveminds.lyve";
    private String appActivity = "StartupActivity";
    private DesiredCapabilities capabilities = new DesiredCapabilities();

    public AndroidDriver StartDriverAndroidApp(String appLocation, String appPackage, String appActivity) throws MalformedURLException {

        capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");
        capabilities.setCapability("platformVersion", "4.4.2");
        capabilities.setCapability(CapabilityType.PLATFORM, "Windows");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("app", appLocation);
        capabilities.setCapability("app-package", appPackage);
        capabilities.setCapability("app-activity", appActivity);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        return driver;

    }

    @Before
    public void StartDriver() {
        try {
            File userDir = new File(System.getProperty("user.dir"));
            File appDir = new File(userDir, "/APK/");
            File app = new File(appDir, "avery-debug-armeabi-v7a-3.0.0.1448.apk");
            //capabilities.setCapability("app", app.getAbsolutePath());
            driver = StartDriverAndroidApp(app.getAbsolutePath(), appPackage, appActivity);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test() {
        driver.findElement(By.id("naukriApp.appModules.login:id/tv_location")).click();    //click on the DD

        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.50);
        swipeObject.put("startY", 0.2);
        swipeObject.put("endX", 0.50);
        swipeObject.put("endY", 0.9);
        swipeObject.put("duration", 1.0);
        js.executeScript("mobile: swipe", swipeObject);

    }

}