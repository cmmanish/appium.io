package com.lyve.qa.screens;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by mmadhusoodan on 2/24/15.
 */
public class HomeScreen {
    final private static Logger log = Logger.getLogger(HomeScreen.class);
    private final WebDriver driver;

    public HomeScreen(WebDriver driver) {
        this.driver = driver;

        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);

    }


    @FindBy(id = "android:id/action_bar_title")
    WebElement actionBarTitle;

    @FindBy(id = "android:id/home")
    WebElement homeMenuItem;

    @FindBy(xpath = "//android.widget.RelativeLayout[@index='2']")
    WebElement mixMenuItem;

    @FindBy(xpath = "//android.widget.RelativeLayout[@index='6']")
    WebElement photosMenuItem;
    @FindBy(xpath = "//android.widget.RelativeLayout[@index='7']")
    WebElement videosMenuItem;

    @FindBy(xpath = "//android.widget.RelativeLayout[@index='8']")
    WebElement statusMenuItem;

    @FindBy(xpath = "//android.widget.RelativeLayout[@index='9']")
    WebElement settingsMenuItem;

    public void clickHomeMenuItem() {
        homeMenuItem.click();
        log.info(homeMenuItem.getText() + " clicked ");
    }

    public void clickMixMenuItem() {
        mixMenuItem.click();
        log.info(" MixMenuItem  clicked ");
    }

    public void clickSettingsMenuItem() {
        settingsMenuItem.click();
        log.info(" SettingsMenuItem clicked ");
    }


    public String getActionBarTitle() {

        return actionBarTitle.getText().trim().toString();
    }


}