package com.org.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestMain {

    public int RESPONSE_STATUS_CODE_200 =  200;
    public int RESPONSE_STATUS_CODE_422 =  422;
    public Properties prop;

    public TestMain(){
        try {
            prop = new Properties();
            //Reading the config.properties file, which has all the URLs need to be tested
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/org/configuration/config.properties");
            prop.load(ip);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
