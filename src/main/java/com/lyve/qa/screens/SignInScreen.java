package com.lyve.qa.screens;

import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by mmadhusoodan on 2/24/15.
 */
public class SignInScreen {
    final private static Logger log = Logger.getLogger(SignInScreen.class);
    private AppiumDriver driver;

    public SignInScreen(AppiumDriver driver) {
        this.driver = driver;

        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//android.widget.EditText[@index='0']")
    public WebElement emailTextBox;

    @FindBy(xpath = "//android.widget.EditText[@index='1']")
    public WebElement passwordTextBox;

    @FindBy(className = "android.widget.Button")
    public WebElement signInButton;


    public void typeUsername(String username) {
        emailTextBox.sendKeys(username);
        log.info("type username " + username);
    }

    public void typePassword(String password) {
        passwordTextBox.sendKeys(password);
        log.info("type password " + password);
    }

    public void clickSignInButton() {
        signInButton.click();
        log.info("clickSignInButton()");

    }
}