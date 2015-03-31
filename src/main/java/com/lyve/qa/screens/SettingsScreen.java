package com.lyve.qa.screens;

import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by mmadhusoodan on 3/30/15.
 */
public class SettingsScreen {

    private static AppiumDriver driver;
    final private static Logger log = Logger.getLogger(SettingsScreen.class);

    public SettingsScreen(AppiumDriver driver) {
        this.driver = driver;

        log.info("In SettingsScreen ");
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "android.widget.TextView")
    public List<WebElement> TextViewList;

    @FindBy(xpath = "//android.widget.LinearLayout[@index='1']")
    private WebElement RefeshLayout;


    public String getAppVersion() {

        log.info(TextViewList.get(5).getText().toString());
        return TextViewList.get(5).getText();
    }

    public String getAccount() {

        log.info(TextViewList.get(7).getText().toString());
        return TextViewList.get(7).getText();
    }

    public String getPhotoVideoCount() {

        String photoVideoCount = TextViewList.get(8).getText();
        photoVideoCount = photoVideoCount.split("Copyright")[0].trim();
        log.info(photoVideoCount);
        return photoVideoCount;
    }
}
