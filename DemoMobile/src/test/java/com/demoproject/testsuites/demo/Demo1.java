package com.demoproject.testsuites.demo;


import com.aventstack.extentreports.Status;
import com.demoproject.base.TestBase;
import com.demoproject.constants.module;
import com.testautomation.framework.utils.Prop;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import java.text.MessageFormat;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Demo1 extends TestBase {

    @BeforeClass(alwaysRun = true)
    public void setTestData() {
        dataConfig.TEST_DATA_FILE_PATH = MessageFormat.format(Prop.getProperty("testdata.path"),System.getProperty("user.dir"),dataConfig.testEnvironment.toLowerCase(), module.Module1+".json");
        System.out.println("testdatapath::"+dataConfig.TEST_DATA_FILE_PATH);
    }

    @Test(groups={"Demo"})
    public void Demo1() throws Exception {
        if(testExecute) {
            //Variable Declarion
            System.out.println("Driver::"+dataConfig.driver);


            // Step 1: Go to Just for U Tab
            verify(homeScreen.gotoJustForU(), "Just for u is displayed");
            // Step 2: Go to My Store Tab
            verify(homeScreen.gotoMyStore(), "My Store is displayed");


        } else{
            dataConfig.finalTestCaseStatus = Status.SKIP;
            throw new SkipException("Execute variable <> 'YES'. Skipping execution...");
        }

    }

}
