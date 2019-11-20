package com.toutiao.utils;

import com.toutiao.model.InterfaneName;
import lombok.extern.log4j.Log4j2;

import java.util.Locale;
import java.util.ResourceBundle;
@Log4j2
public class ConfigFile {
    private static ResourceBundle bundle =ResourceBundle.getBundle("application", Locale.CHINA);

    public static String getUrl(InterfaneName name){
        String address= bundle.getString("test.url");
        log.info(address);

        String uri = "" ;
        String testUrl;
        if(name == InterfaneName.LOGIN){
            uri = bundle.getString("get.url");
        }
        if (name == InterfaneName.POSTDEMO){
            uri = bundle.getString("post.url");
        }
        if (name == InterfaneName.USER){
            uri = bundle.getString("user.url");
        }
        if (name == InterfaneName.GETLIST){
            uri = bundle.getString("getlist.url");
        }
        testUrl = address + uri;
        return testUrl;


}

}
