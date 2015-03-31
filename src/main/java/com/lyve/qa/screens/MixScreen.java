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
public class MixScreen {

    private static AppiumDriver driver;
    final private static Logger log = Logger.getLogger(MixScreen.class);

    public MixScreen(AppiumDriver driver) {
        this.driver = driver;

        log.info("In MixScreen ");
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "com.lyveminds.lyve:id/listitem_cmd_bar_block")
    public List<WebElement> mixItemList;

    @FindBy(id = "com.lyveminds.lyve:id/tvMainTitle")
    public WebElement newMixScreenTittle;

    @FindBy(id = "com.lyveminds.lyve:id/etEventName")
    public WebElement mixTittleEditBox;

    @FindBy(id = "com.lyveminds.lyve:id/tvLocation")
    public WebElement locationTittleText;

    @FindBy(id = "com.lyveminds.lyve:id/btnCreateEvent")
    public WebElement createMixButton;

    @FindBy(id = "com.lyveminds.lyve:id/action_add_photos")
    public WebElement addPhotosButton;

    @FindBy(id = "com.lyveminds.lyve:id/action_camera")
    public WebElement cameraButton;

    @FindBy(className = "android.widget.ImageButton")
    public WebElement moreOptions;

    @FindBy(className = "android.widget.RelativeLayout")
    public List<WebElement> moreMixItemList;

    @FindBy(className = "android.widget.TextView")
    public List<WebElement> moreMixItemListText;
    //

    /////////////////////Methods/////////////////////////
    public void clickAddNewMix() {

        try {
            Thread.sleep(5000);

            if (mixItemList.size() > 0) {
                WebElement addNewMixItem = mixItemList.get(0);
                addNewMixItem.click();
                log.info("click addNewMixItem");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickRefreshMix() {

        try {
            Thread.sleep(5000);
            if (mixItemList.size() > 0) {
                WebElement refreshMixItem = mixItemList.get(1);
                refreshMixItem.click();
                log.info("click refreshMixItem");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getNewMixScreenTitle() {

        return newMixScreenTittle.getText().trim().toString();

    }

//    public String getLocationTitle() {
//
//        return locationTittleText.getText().trim().toString();
//
//    }

    public String getMixTitle() {

        return mixTittleEditBox.getText().trim().toString();

    }

    public void setMixTitle(String eventName) {

        mixTittleEditBox.sendKeys(eventName);
        hideKeyboard();
        log.info("Set EvenName to : " + mixTittleEditBox.getText().toString());

    }

    public MixDetailsScreen clickCreateMixButton() {

        createMixButton.click();
        driver.navigate().back();
        log.info(" clickCreateMixButton");
        return new MixDetailsScreen(driver);

    }

    public MixDetailsScreen clickMoreOptionsButton() {

        moreOptions.click();
        log.info(moreOptions.getText().toString() + " clicked ");
        return new MixDetailsScreen(driver);

    }

    public MixDetailsScreen getMixDetailsScreen() {

        moreOptions.click();
//        log.info(moreOptions.getText().toString() + " clicked ");

        log.info("going to click " + moreMixItemListText.get(2).getText().trim().toString());
        moreMixItemList.get(2).click();

        return new MixDetailsScreen(driver);

    }

    public void hideKeyboard() {
        driver.navigate().back();
    }

}
