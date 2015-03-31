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
public class MixDetailsScreen {

    private static AppiumDriver driver;
    final private static Logger log = Logger.getLogger(MixDetailsScreen.class);

    public MixDetailsScreen(AppiumDriver driver) {
        this.driver = driver;

        log.info("In MixDetailsScreen ");
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "com.lyveminds.lyve:id/listitem_cmd_bar_block")
    public List<WebElement> mixItemList;

    @FindBy(id = "com.lyveminds.lyve:id/tvMainTitle")
    public WebElement mixDetailTittle;

    @FindBy(id = "com.lyveminds.lyve:id/etEventName")
    public WebElement mixTitleEditBox;

    @FindBy(id = "com.lyveminds.lyve:id/tvStartTime")
    public WebElement mixStartDateTittleText;

    @FindBy(id = "com.lyveminds.lyve:id/tvTimeRange")
    public WebElement mixTimeRangeTittleText;

    @FindBy(id = "com.lyveminds.lyve:id/tvLocation")
    public WebElement mixLocationTittleText;

    @FindBy(id = "com.lyveminds.lyve:id/btnCreateEvent")
    public WebElement shareInviteButton;

    @FindBy(id = "com.lyveminds.lyve:id/tvEventPublicPrivate")
    public WebElement mixPublicPrivateText;

    /////////////////////Methods/////////////////////////

    public String getMixTitle() {

        return mixTitleEditBox.getText().trim().toString();

    }

    public String getMixDetailsScreenTitle() {

        return mixDetailTittle.getText().trim().toString();

    }

    public String getMixStartDateTitle() {

        return mixStartDateTittleText.getText().trim().toString();

    }

    public String getMixTimeRangeTitle() {

        return mixTimeRangeTittleText.getText().trim().toString();

    }

    public String getMixLocationTitle() {

        return mixLocationTittleText.getText().trim().toString();

    }

    public void setMixTitle(String mixName) {

        mixTitleEditBox.sendKeys(mixName);
        hideKeyboard();
        log.info("Set EvenName to : " + mixTitleEditBox.getText().toString());

    }

    public void clickshareInviteButton() {

        shareInviteButton.click();
        hideKeyboard();
        log.info("click shareInviteButton");

    }

    public String getMixPublicPrivateText() {
        return mixPublicPrivateText.getText().trim().toString();
    }

    public void hideKeyboard() {
        driver.navigate().back();
    }

    public void pressBackKey() {
        driver.navigate().back();
    }

}
