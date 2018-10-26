package com.testautomation.framework.driverconfig;


import com.testautomation.framework.base.DataConfig;
import com.testautomation.framework.driverconfig.drivers.Android;
import com.testautomation.framework.driverconfig.drivers.IOS;
import io.appium.java_client.AppiumDriver;
import java.net.MalformedURLException;
import javax.annotation.Nullable;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ConfigDriver {

    private DataConfig dataConfig=null;
    public ConfigDriver(DataConfig dataConfig) {
        this.dataConfig = dataConfig;
    }

    public void initDriver() throws Exception {
        dataConfig.driver = getMobileDriver(null);
    }
    public AppiumDriver getMobileDriver(@Nullable Capabilities capabilities)throws MalformedURLException {
        MobileDriver mobileDriver = null;
        switch (dataConfig.mb_platformName.toLowerCase()) {
            case "android":
                mobileDriver  = new Android(dataConfig);
                break;
            case "ios":
                mobileDriver  = new IOS(dataConfig);
                break;
        }
        return mobileDriver.buildMobileDriver(capabilities);
    }


}
