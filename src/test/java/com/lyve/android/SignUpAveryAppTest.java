package com.lyve.android;

import com.lyve.qa.screens.CreateAccountScreen;
import com.lyve.qa.screens.StartupScreen;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SignUpAveryAppTest extends AbstractAppiumBaseClass {
    final private static Logger log = Logger.getLogger(SignUpAveryAppTest.class);
    private String apkVersionAndName = "avery-debug-armeabi-v7a-3.0.0.1448.apk";
    private AndroidDriver driver;

    @Before
    public void setUp() throws Exception {

        File userDir = new File(System.getProperty("user.dir"));
        File appDir = new File(userDir, "/APK/");
        File app = new File(appDir, apkVersionAndName);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "5.1");

        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.lyveminds.lyve");
        capabilities.setCapability("appActivity", ".StartupActivity");
        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        } catch (UnreachableBrowserException ube) {
            ube.printStackTrace();
            System.exit(1);
        } catch (SessionNotCreatedException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
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
    public void T1testSignUpAvery() {

        String first = "Manish";
        String last = "Madhu";
        String email = "mm+0331@lyveminds.com";
        String password = "123456";

        log.info("Create Account / Sign In Screen ");
        try {

            StartupScreen startupScreen = new StartupScreen(driver);
            startupScreen.clickCreateAccountButton();

            CreateAccountScreen createAccountScreen = new CreateAccountScreen(driver);
            createAccountScreen.typeFirstName(first);
            createAccountScreen.typeLastName(last);
            createAccountScreen.typeEmail(email);
            createAccountScreen.typePassword(password);
            createAccountScreen.checkIAgreeTOSCheckBox();
            createAccountScreen.clickCreateAccount();


            log.info("Sign In Complete ");

//            return true;
        } catch (Exception e) {
            e.printStackTrace();
//            return false;
        }
    }
}
