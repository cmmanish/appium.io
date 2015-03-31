package com.lyve.qa.screens;

import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by mmadhusoodan on 2/24/15.
 */
public class StartupScreen {

    private static AppiumDriver driver;
    private CreateAccountScreen createAccountScreen;
    private SignInScreen signInScreen;
    final private static Logger log = Logger.getLogger(StartupScreen.class);

    public StartupScreen(AppiumDriver driver) {
        this.driver = driver;

        log.info("In StartupScreen ");
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "com.lyveminds.lyve:id/create_account")
    public WebElement createAccountButton;

    @FindBy(id = "com.lyveminds.lyve:id/sign_in")
    private WebElement SignInButton;

    public CreateAccountScreen clickCreateAccountButton() {
        log.info("click " + createAccountButton.getText());
        createAccountButton.click();
        return createAccountScreen;
    }

    public SignInScreen clickSignInButton() {
        log.info("click " + SignInButton.getText());
        SignInButton.click();
        return new SignInScreen(driver);
    }

}
