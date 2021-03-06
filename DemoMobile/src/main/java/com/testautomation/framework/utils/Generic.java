package com.testautomation.framework.utils;

import com.sun.istack.internal.NotNull;
import com.testautomation.framework.base.DataConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.openqa.selenium.Platform;

public class Generic {

    private static Platform platform = null;
    DataConfig dataConfig=null;
    JsonUtils jsonUtils=null;

    public Generic(DataConfig dataConfig){
        this.dataConfig=dataConfig;
        jsonUtils = new JsonUtils();
    }

    /*	To get the host OS name */
    public static Platform getCurretnPlatform(){
        if(platform == null){
            String osname = System.getProperty("os.name").toLowerCase();
            if(osname.contains("win")){
                platform = Platform.WINDOWS;
            } else if(osname.contains("nix") || osname.contains("nux") || osname.contains("aix")){
                platform=Platform.LINUX;
            } else if(osname.contains("mac")){
                platform=Platform.MAC;
            }
        }

        return platform;
    }

    /*	To get the ComputerName */
    public static String getComputerName() throws Exception{
        String hostname = "Unknown";
        try
        {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            hostname = addr.getHostName();
        }
        catch (UnknownHostException ex) {
            ex.fillInStackTrace();
            System.out.println("Hostname can not be resolved");
        }
        return (hostname);
    }

    public boolean testExecute(){
        String strExcute;
        strExcute = jsonUtils.getValue(jsonUtils.parseStringToJsonObject(dataConfig.TEST_DATA_FILE_PATH),dataConfig.testMethodName,"execute");
        return getBoolean(strExcute);
    }

    public boolean getBoolean(@NotNull String str){

        if (str.equalsIgnoreCase("yes") || str.equalsIgnoreCase("true") || str.equalsIgnoreCase("on")) {
             return true;
         } else {
             return false;
         }

    }

    public String readFile(String filepath) throws IOException{
        File file = new File(filepath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        try {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while(line!=null){
                stringBuilder.append(line);
                stringBuilder.append("\n");
                line = bufferedReader.readLine();
            }
            return stringBuilder.toString();

        } finally {
            bufferedReader.close();
        }
    }

    public String getSuiteXmlGroupName(String[] groupnames){
        String groupName=null;
        for(int i=0;i<=groupnames.length-1;i++){
            groupName = groupnames[0];
        }
        return groupName;

    }

    public void readMobileCapabilities(String structHead){
        JsonUtils jsonUtils =new JsonUtils();
        JSONObject jsonObject = null;
        String mobileCloudfilepath = null;

        try {
            if(StringUtils.equalsIgnoreCase(dataConfig.testNetowk,"cloud")){
                mobileCloudfilepath = dataConfig.workDir+Prop.getProperty("mobile.cloud.devices.capabilities.file");

            } else{
                mobileCloudfilepath = dataConfig.workDir+Prop.getProperty("mobile.local.devices.capabilities.file");
            }

            jsonObject = jsonUtils.parseStringToJsonObject(mobileCloudfilepath);
            dataConfig.mb_udid = jsonUtils.getValue(jsonObject,structHead,"udid");
            dataConfig.mb_deviceName= jsonUtils.getValue(jsonObject,structHead,"deviceName");
            dataConfig.mb_platformName = jsonUtils.getValue(jsonObject,structHead,"platformName");
            dataConfig.mb_platformVersion = jsonUtils.getValue(jsonObject,structHead,"platformVersion");
            dataConfig.mb_manufacturer = jsonUtils.getValue(jsonObject,structHead,"manufacturer");
            dataConfig.mb_appActivity = jsonUtils.getValue(jsonObject,structHead,"appActivity");


        }catch (Exception e){
            e.printStackTrace();
        }
    }




}
