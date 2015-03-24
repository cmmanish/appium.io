package com.lyve.qa.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

public class QaScreenshot extends TestWatcher {

    private static Logger log = Logger.getLogger(QaScreenshot.class);
    private static QaScreenshot instance;
    private WebDriver driver;

    private static QaCalendar calendar = QaCalendar.getInstance();
    private static String captureDate = calendar.getCaptureTime();
    private static String screenshotsDir = QaProperties.getScreenShotsDir();
    private static String reportsDir = QaProperties.getReportsDir();

    private static String screenshotPath = screenshotsDir + File.separator + captureDate + File.separator;
    private static String reportsPath = "";

    private QaScreenshot() {
    }

    public QaScreenshot(WebDriver driver) {
        this.driver = driver;
    }

    public static synchronized QaScreenshot getInstance() {
        if (instance == null) {
            instance = new QaScreenshot();
        }
        return instance;
    }

    @Override
    protected void failed(Throwable e, Description description) {

        String path = capture(driver);
        log.info("Image Path: " + path);
        log.info(description.getAnnotations());
    }

    /*
    * Since this object requires util driver object, I don't want to create it without one. So create util private no
    * argument constructor to block direct creation.
    */
    public String capture(WebDriver driver) {

        try {
            WebDriver augmentedDriver = new Augmenter().augment(driver);
            File source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);

            screenshotPath = screenshotPath.replace(":","_");

            screenshotPath = screenshotPath + source.getName();
            log.info("screenshotPath: " + screenshotPath);
            FileUtils.copyFile(source, new File(screenshotPath));
        } catch (IOException e) {
            screenshotPath = "Failed to capture screenshot: " + e.getMessage();
        } catch (Exception e) {
            screenshotPath = "Failed to capture screenshot: " + e.getMessage();
        }
        return screenshotPath;
    }

    /**
     * Function over load to the grab screen and palace it in util different folder
     * argument constructor to block direct creation.
     */
    public String reportCapture(WebDriver driver, String reportSubDir) {

        try {
            WebDriver augmentedDriver = new Augmenter().augment(driver);
            File source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);

            reportsPath = reportsDir + File.separator +  reportSubDir +File.separator + source.getName();
            log.info("reportsPath " + reportsPath);
            FileUtils.copyFile(source, new File(reportsPath));
        } catch (IOException e) {
            reportsPath = "Failed to capture screenshot: " + e.getMessage();
        } catch (Exception e) {
            reportsPath = "Failed to capture screenshot: " + e.getMessage();
        }
        return reportsPath;
    }

}