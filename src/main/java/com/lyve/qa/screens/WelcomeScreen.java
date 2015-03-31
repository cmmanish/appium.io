package com.lyve.qa.screens;

import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by mmadhusoodan on 3/30/15.
 */
public class WelcomeScreen {

    private static AppiumDriver driver;
    final private static Logger log = Logger.getLogger(WelcomeScreen.class);

    public WelcomeScreen(AppiumDriver driver) {
        this.driver = driver;

        log.info("In WelcomeScreen ");
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "com.lyveminds.lyve:id/greeting")
    public WebElement rememberLabel;

    @FindBy(id = "com.lyveminds.lyve:id/welcome_blurb")
    public WebElement seeYourMemoriesLabel;

    @FindBy(id = "com.lyveminds.lyve:id/welcome_ok")
    public WebElement OKButton;

    public void printGreeting() {
        log.info(rememberLabel.getText());

    }

    public void printSeeYouThenMessage() {
        log.info(rememberLabel.getText());
    }

    public HomeScreen clickOKButton() {
        OKButton.click();
        log.info("Click Ok Button");
        return new HomeScreen(driver);
    }

}
