package com.demoproject.pagemodel;

import com.testautomation.framework.base.DataConfig;
import com.testautomation.framework.utils.Log;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomeScreen {

    private DataConfig dataConfig=null;
    public HomeScreen(DataConfig dataConfig){
        this.dataConfig=dataConfig;
        PageFactory.initElements(new AppiumFieldDecorator(dataConfig.driver), this);
    }
    // Variable Declaration


    //********* HomePage Page Web Elements by using Page Factory*********

    @CacheLookup
    @FindAll({
            @FindBy(xpath = "//XCUIElementTypeButton[@label='just for U']"),
            @FindBy(xpath = "//andr oid.widget.TextView[@text='just for U']")
    })
    public WebElement btnJustforU;

    @CacheLookup
    @FindAll({
            @FindBy(xpath = "//XCUIElementTypeButton[@label='My Store']"),
            @FindBy(xpath = "//android.widget.TextView[@text='My Store']")
    })
    public WebElement btnMyStore;

    @CacheLookup
    @FindBy(how = How.XPATH, using = "//a[text()=\"Gmail\"]")
    public WebElement lnkGmail;

    @CacheLookup
    @FindBy(how = How.XPATH, using = "//a[text()=\"Images\"]")
    public WebElement lnkImages;


    //#################################################################
    //#################################################################
    //##################        METHODS        #######################
    //#################################################################
    //#################################################################
    public boolean gotoJustForU() throws Exception{
        dataConfig.stepDescription = "Just for u screen displayed";
        Log.startLog("'gotoJustForU'");
        btnJustforU.click();
        Log.endLog("gotoJustForU");
        return true;

    }
    public boolean gotoMyStore() throws Exception{
        dataConfig.stepDescription = "My Store screen displayed";
        Log.startLog("'gotoMyStore'");
        btnMyStore.click();
        Log.endLog("gotoMyStore");
        return true;
    }

}
