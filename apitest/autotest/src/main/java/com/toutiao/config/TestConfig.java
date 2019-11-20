package com.toutiao.config;

import lombok.Data;
import org.apache.http.client.CookieStore;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
@Data
public class TestConfig {

    public static String getLoginUrl;

    public static String postUrl;

    public static String userUrl;

    public static CookieStore store;

    public static String getListUrl;

    public static DefaultHttpClient defaultHttpClient;




}
