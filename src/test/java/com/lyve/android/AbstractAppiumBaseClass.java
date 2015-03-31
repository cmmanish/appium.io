package com.lyve.android;

import com.lyve.qa.screens.CreateAccountScreen;
import com.lyve.qa.screens.SignInScreen;
import com.lyve.qa.screens.StartupScreen;
import com.lyve.qa.screens.WelcomeScreen;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by mmadhusoodan on 2/19/15.
 */
abstract public class AbstractAppiumBaseClass {

    private AppiumDriver driver;
    protected String Screenshotpath = "screenshot/";
    final private static Logger log = Logger.getLogger(AbstractAppiumBaseClass.class);
    private String apkVersionAndName = "avery-debug-armeabi-v7a-3.0.0.1448.apk";

//    @Before
//    public void setUp() throws Exception {
//
//        File userDir = new File(System.getProperty("user.dir"));
//        File appDir = new File(userDir, "/APK/");
//
//        File app = new File(appDir, apkVersionAndName);
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
//        capabilities.setCapability("deviceName", "Android Emulator");
//        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("platformVersion", "5.1");
//
//        capabilities.setCapability("app", app.getAbsolutePath());
//        capabilities.setCapability("appPackage", "com.lyveminds.lyve");
//        capabilities.setCapability("appActivity", ".StartupActivity");
//        try {
//            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//
//        } catch (UnreachableBrowserException ube) {
//            ube.printStackTrace();
//            System.exit(1);
//        } catch (SessionNotCreatedException e) {
//            e.printStackTrace();
//            System.exit(1);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(1);
//        }
//    }

    public void waitForVisible(final By by, int waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait = new WebDriverWait(driver, 1000);
        for (int attempt = 0; attempt < waitTime; attempt++) {
            try {
                driver.findElement(by);
                break;
            } catch (Exception e) {
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForElementPresent(By by) {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        try {
            driver.findElement(by);
        } catch (org.openqa.selenium.NoSuchElementException e) {
        }
    }

    public boolean SignInLyveApp(AppiumDriver driver, String email, String password) {

        log.info("StartupScreen Create Account / Sign In Screen ");
        try {

            StartupScreen startupScreen = new StartupScreen(driver);
            startupScreen.clickSignInButton();

            SignInScreen signInScreen = new SignInScreen(driver);
            signInScreen.typeUsername(email);
            signInScreen.typePassword(password);
            signInScreen.clickSignInButton();

            WelcomeScreen welcomeScreen = new WelcomeScreen(driver);
            welcomeScreen.printGreeting();
            welcomeScreen.printSeeYouThenMessage();
            welcomeScreen.clickOKButton();

            log.info("Sign In Complete ");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean CreateAccountLyveApp(AppiumDriver driver, String email, String password) {

        String first = "Manish";
        String last = "Madhu";
      //  String email = "mm+0331@lyveminds.com";
      //  String password = "123456";
        log.info("StartupScreen Create Account / Sign In Screen ");
        try {

            StartupScreen startupScreen = new StartupScreen(driver);
            startupScreen.clickSignInButton();

            CreateAccountScreen createAccountScreen = new CreateAccountScreen(driver);
            createAccountScreen.typeFirstName(first);


            log.info("Sign In Complete ");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public void SignInLyveAppOLD(AppiumDriver driver, String email, String password) {

        log.info("Create Account / Sign In Screen ");
        try {

            StartupScreen startupScreen = new StartupScreen(driver);
            startupScreen.clickSignInButton();

            WebElement createAccountSignIn = driver.findElementByClassName("android.widget.LinearLayout");

            WebElement createAccount = driver.findElement(By.id("com.lyveminds.lyve:id/create_account"));

            WebElement signIn = driver.findElement(By.id("com.lyveminds.lyve:id/sign_in"));

            if (null != createAccountSignIn) {
                assertEquals("Create Account", createAccount.getText());
                assertEquals("Sign In", signIn.getText());
                WebElement signInWidget = driver.findElement(By.id("com.lyveminds.lyve:id/sign_in"));

                signInWidget.click();
                if (null != driver.findElementsByClassName("android.widget.EditText")) {
                    List<WebElement> emailAndPassword = driver.findElementsByClassName("android.widget.EditText");

                    if (emailAndPassword.size() != 0) {
                        emailAndPassword.get(0).clear();
                        emailAndPassword.get(0).sendKeys(email);
                        log.info("Type email: " + email);

                        emailAndPassword.get(1).clear();
                        emailAndPassword.get(1).sendKeys(password);
                        log.info("Type password: " + password);

                        WebElement signInButton = driver.findElementByClassName("android.widget.Button");
                        signInButton.click();
                        log.info("click signInButton");

                        log.info(driver.findElementById("com.lyveminds.lyve:id/greeting").getText());
                        log.info(driver.findElementById("com.lyveminds.lyve:id/welcome_blurb").getText());

                        WebElement welcomeOK = driver.findElementById("com.lyveminds.lyve:id/welcome_ok");
                        log.info("Click " + welcomeOK.getText());
                        welcomeOK.click();

                        log.info("You are in HomeScreen");
                    }
                }
            }

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
