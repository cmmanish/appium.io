package com.lyve.android;

import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class SignInAveryAppTest extends AbstractAppiumBaseClass {
    final private static Logger log = Logger.getLogger(SignInAveryAppTest.class);
    private AndroidDriver driver;

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

    /**
     * Debug
     * Run after each test *
     */
    @After
    public void tearDown() throws Exception {
        log.info("closing the app ");
        if (driver != null) driver.quit();
    }


    @Test
    public void testSignInAndroid() {

        String email = "mmadhusoodan+multiple@lyveminds.com";
        String password = "123456";

        log.info("Create Account / Sign In Screen ");
        try {
            List<WebElement> els = driver.findElements(By.className("android.widget.TextView"));

            assertEquals("Create Account", els.get(0).getText());
            assertEquals("Sign In", els.get(1).getText());

            WebElement signInWidget = els.get(1);

            signInWidget.click();

            List<WebElement> emailAndPassword = driver.findElementsByClassName("android.widget.EditText");

            emailAndPassword.get(0).clear();
            emailAndPassword.get(0).sendKeys(email);
            log.info("Type email");

            emailAndPassword.get(1).clear();
            emailAndPassword.get(1).sendKeys(password);
            log.info("Type password");

            WebElement signInButton = driver.findElementByClassName("android.widget.Button");

            signInButton.click();
            log.info("click signInButton");

//            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            FileUtils.copyFile(scrFile, new File(Screenshotpath + "RememberScreen.jpg"));

            WebElement OkButton = driver.findElementByClassName("android.widget.TextView");
            OkButton.click();
            log.info("click OkButton on Welcome Lyve Screen");

//            scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            FileUtils.copyFile(scrFile, new File(Screenshotpath + "LyveMainApplication.jpg"));

            WebElement OkButtonOneLastThing = driver.findElementByClassName("android.widget.TextView");
            OkButton.click();
            log.info("click OkButton on OneLastThing");



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
