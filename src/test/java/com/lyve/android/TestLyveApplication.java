package com.lyve.android;

import com.lyve.qa.screens.HomeScreen;
import com.lyve.qa.screens.MixDetailsScreen;
import com.lyve.qa.screens.MixScreen;
import com.lyve.qa.screens.SettingsScreen;
import com.lyve.qa.util.QaCalendar;
import com.lyve.qa.util.QaRandom;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestLyveApplication extends AbstractAppiumBaseClass {
    final private static Logger log = Logger.getLogger(TestLyveApplication.class);
    private AndroidDriver driver;
    private String apkVersionAndName = "avery-debug-armeabi-v7a-3.0.0.1448.apk";

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
    public void T0testClickAndVerifySettings() {
        String email = "mmadhusoodan+111@lyveminds.com";
        String password = "123456";
        log.info("T0testClickAndVerifySettings");
        assertTrue("Sign in Failed ", SignInLyveApp(driver, email, password));
        try {
            log.info("You are in HomeScreen");

            HomeScreen homeScreen = new HomeScreen(driver);
            homeScreen.clickHomeMenuItem();

            log.info("Title: " + homeScreen.getActionBarTitle());

            homeScreen.clickSettingsMenuItem();

            log.info("Title: " + homeScreen.getActionBarTitle());

            SettingsScreen settingsScreen = new SettingsScreen(driver);
            String appVersion = settingsScreen.getAppVersion();
            String account = settingsScreen.getAccount();

            String apkversion = apkVersionAndName.split("-")[1];
            apkversion = apkversion.split(".apk")[0];
            assertEquals("App Version doesn't match ", "3.1.0 (1448)", appVersion);
            assertEquals("Account Version doesn't match ", email, account);

            String photoVideoCount = settingsScreen.getPhotoVideoCount();
            String photo = photoVideoCount.split(" ")[0];
            String video = photoVideoCount.split(" ")[3];
            log.info("photo: " + photo);
            log.info("video: " + video);


        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void T1testCreateAndVerifyMix() {
        String email = "mmadhusoodan+0330@lyveminds.com";
        String password = "123456";
        String mixLocation = "10001 N De Anza Blvd Cupertino, CA 95014";
        String mixPublicPrivate = "Anyone nearby can join.";
        SignInLyveApp(driver, email, password);
        log.info("From HomeScreen ");
        try {
            HomeScreen homeScreen = new HomeScreen(driver);
            homeScreen.clickHomeMenuItem();

            assertEquals("Screen Title doesn't Match ", "Lyve", homeScreen.getActionBarTitle());

            homeScreen.clickMixMenuItem();
            assertEquals("Screen Title doesn't Match ", "Mix", homeScreen.getActionBarTitle());

            MixScreen mixScreen = new MixScreen(driver);
            mixScreen.clickRefreshMix();

            mixScreen.clickAddNewMix();

            assertEquals("New Mix Title doesn't match ", "New Mix", mixScreen.getNewMixScreenTitle());

            assertEquals("Mix EditBox Content doesn't match ", "Mix Name", mixScreen.getMixTitle());
            String mixName = "Automation_" + QaCalendar.getInstance().getCurrentDate() + QaRandom.getInstance().getRandomString(5);
            mixScreen.setMixTitle(mixName);

            MixDetailsScreen mixDetailsScreen = mixScreen.clickCreateMixButton();

            if (mixName.equalsIgnoreCase(homeScreen.getActionBarTitle())) {
                mixScreen.getMixDetailsScreen();
                log.info("MixName: " + mixDetailsScreen.getMixTitle());
                assertEquals("Mix Name doesn't match ", mixName, mixDetailsScreen.getMixTitle());

                log.info("Mix StartDate: " + mixDetailsScreen.getMixStartDateTitle());
                log.info("Mix TimeRange: " + mixDetailsScreen.getMixTimeRangeTitle());
                log.info("Location: " + mixDetailsScreen.getMixLocationTitle());
                assertEquals("Mix Location doesn't match ", mixLocation, mixDetailsScreen.getMixLocationTitle());
                assertEquals("Mix Public Private Text doesn't match ", mixPublicPrivate, mixDetailsScreen.getMixPublicPrivateText());

            } else {
                log.info("Not in the right screen");
                mixDetailsScreen.pressBackKey();
            }


        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void T2testClickAndVerifyTimeline() {
        String email = "mmadhusoodan+111@lyveminds.com";
        String password = "123456";
        SignInLyveApp(driver, email, password);
        try {
            log.info("You are in HomeScreen");

            WebElement homeMenu = driver.findElementById("android:id/home");
            homeMenu.click();

            WebElement actionBarTitle = driver.findElementById("android:id/action_bar_title");
            log.info("Tittle: " + actionBarTitle.getText());

            WebElement timelineItemLayout = driver.findElementById("com.lyveminds.lyve:id/listitem_cmd_bar_block");
            timelineItemLayout.click();

            actionBarTitle = driver.findElementById("android:id/action_bar_title");
            assertEquals("Action Title doesn't match ", "Timeline", actionBarTitle.getText());
            log.info("Tittle: " + actionBarTitle.getText());

            homeMenu.click();

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void T10testClickAndVerifyMeshStatus() {
        String email = "mmadhusoodan+111@lyveminds.com";
        String password = "123456";
        SignInLyveApp(driver, email, password);
        try {
            log.info("You are in HomeScreen");

            WebElement homeMenu = driver.findElementById("android:id/home");
            homeMenu.click();

            WebElement actionBarTitle = driver.findElementById("android:id/action_bar_title");
            log.info("Tittle: " + actionBarTitle.getText());

            WebElement mixItemLayout = driver.findElementByXPath("//android.widget.RelativeLayout[@index='10']");
            log.info("Click Timeline");
            mixItemLayout.click();
            homeMenu.click();

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void loop() {
//        List<WebElement> leftDrawerMenuList = driver.findElementsByClassName("android.widget.RelativeLayout");
//        log.info("leftDrawerMenu Count " + leftDrawerMenuList.size());
//
//        for (int i = 0; i < leftDrawerMenuList.size(); i++) {
//
//            WebElement eachMenulabel = driver.findElementById("com.lyveminds.lyve:id/label");
//            log.info(eachMenulabel.getText());
//            log.info("android.widget.RelativeLayout[@index='" + i + "']");
//
//            WebElement eachMenuLayout = driver.findElementByXPath("android.widget.RelativeLayout[@index='" + i + "']");
//            eachMenuLayout.click();
//
//            WebElement actionBarTitle = driver.findElementById("android:id/action_bar_title");
//            log.info(actionBarTitle.getText());
//
//            WebElement eachMenuItem = driver.findElementByClassName("android.widget.RelativeLayout");
//            log.info(eachMenulabel.getText());
//
//            eachMenuItem.click();
//            WebElement homeMenu = driver.findElementById("android:id/home");
//            homeMenu.click();
//
//        }
//    }
}
