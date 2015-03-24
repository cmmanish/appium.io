package com.lyve.android;

import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

public class SignUpAveryAppTest extends AbstractAppiumBaseClass {
    final private static Logger log = Logger.getLogger(SignUpAveryAppTest.class);
    private AndroidDriver driver;

    @Before
    public void setUp() throws Exception {
        // setup appium

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
     * Run after each test *
     */
    @After
    public void tearDown() throws Exception {
        if (driver != null) driver.quit();
    }

    @Test
    public void testSignUpAvery() {

        String first = "Manish";
        String last = "Madhu";
        String email = "mm+0219dog1@lyveminds.com";
        String password = "123456";

        log.info("Create Account / Sign In Screen ");
        try {
            List<WebElement> els = driver.findElements(By.className("android.widget.TextView"));
            assertEquals("Create Account", els.get(0).getText());
            assertEquals("Sign In", els.get(1).getText());

            if (els.size() > 0) {
                WebElement createAccountWidget = els.get(0);
                createAccountWidget.click();

                List<WebElement> editTextList = driver.findElementsByClassName("android.widget.EditText");

                editTextList.get(0).sendKeys(first);
                editTextList.get(1).sendKeys(last);
                editTextList.get(2).sendKeys(email);
                editTextList.get(3).sendKeys(password);

                WebElement readAndAgreeCheckbox = driver.findElement(By.className("android.widget.CheckBox"));
                readAndAgreeCheckbox.click();

                WebElement createAccountButton = driver.findElement(By.className("android.widget.Button"));

                if (createAccountButton.isEnabled()) {
                    createAccountButton.click();
                }


            } else {
                ;
            }

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
