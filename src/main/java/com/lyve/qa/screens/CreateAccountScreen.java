package com.lyve.qa.screens;

import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by mmadhusoodan on 2/24/15.
 */
public class CreateAccountScreen {
    final private static Logger log = Logger.getLogger(CreateAccountScreen.class);
    private AppiumDriver driver;

    public CreateAccountScreen(AppiumDriver driver) {
        this.driver = driver;

        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "android.widget.EditText")
    private List<WebElement> editTextList;

    @FindBy(className = "android.widget.CheckBox")
    private WebElement iAgreeTOSCheckBox;

    @FindBy(className = "android.widget.Button")
    private WebElement createAccountButton;

    public void typeFirstName(String firstName) {
        if (editTextList.size() > 0) {

            editTextList.get(0).sendKeys(firstName);
            log.info("type username " + firstName);
        }
    }

    public void typeLastName(String lastName) {
        if (editTextList.size() > 0) {
            editTextList.get(1).sendKeys(lastName);
            log.info("type username " + lastName);
        }
    }

    public void typeEmail(String email) {
        if (editTextList.size() > 0) {
            editTextList.get(2).sendKeys(email);
            log.info("type username " + email);
        }
    }

    public void typePassword(String password) {
        if (editTextList.size() > 0) {
            editTextList.get(3).sendKeys(password);
            log.info("type username " + password);
        }
    }

    public void checkIAgreeTOSCheckBox() {

        iAgreeTOSCheckBox.click();
        log.info("check TOS ");

    }

    public void clickCreateAccount() {

        createAccountButton.click();
        log.info("click createAccountButton ");

    }
}
