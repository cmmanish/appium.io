package com.lyve.android.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

public class TestAndroidWhatsapp {

    final private static Logger log = Logger.getLogger(TestAndroidWhatsapp.class);
    private AppiumDriver driver;

   // private static String screenshotsDir = QaProperties.getScreenShotsDir();


    @Before
    public void setUp() throws Exception {

        try {

            File appDir = new File("APK/");
            File app = new File(appDir, "WhatsApp.apk");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
            capabilities.setCapability("deviceName","Android");
            capabilities.setCapability("platformVersion", "4.4.2");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("app", app.getAbsolutePath());

            capabilities.setCapability("appPackage", "com.whatsapp");
            capabilities.setCapability("appActivity", ".Main");
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void launchWhatsapp() {

        //log.info(driver.getAppStrings());
        log.info("launchWhatsapp");
//        try {
//            log.info("Taking Screen Grab now");
//            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            FileUtils.copyFile(scrFile, new File(screenshotsDir + "Whatsapp.png"));
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
    }

}
