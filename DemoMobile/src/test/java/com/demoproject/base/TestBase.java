package com.demoproject.base;



import com.aventstack.extentreports.Status;

import com.demoproject.pagemodel.HomeScreen;

import com.testautomation.framework.base.ConfigBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;


public class TestBase extends ConfigBase {


    public HomeScreen homeScreen = null;


    @BeforeMethod(alwaysRun = true)
    public void initTestBase(){
        initilizeClasses();
    }



    public void verify(boolean condition, String message){
        dataConfig.stepNo = dataConfig.stepNo+1;
        if(condition){
            extentManager.addExecutionStep(Status.PASS,message);
        } else{
            message=message.replaceAll("is","is not");
            extentManager.addExecutionStep(Status.FAIL,message);
            dataConfig.finalTestCaseStatus = Status.FAIL;
        }

    }

    public String getTestDatavalue(String key){
        String value=null;
        value = jsonUtils.getValue(jsonUtils.parseStringToJsonObject(dataConfig.TEST_DATA_FILE_PATH),dataConfig.testMethodName,key);
        return value;
    }

    public void Assert(boolean condition, String message){
        Assert.assertTrue(condition);
    }


    public void initilizeClasses(){
        homeScreen = new HomeScreen(dataConfig);

    }
}
