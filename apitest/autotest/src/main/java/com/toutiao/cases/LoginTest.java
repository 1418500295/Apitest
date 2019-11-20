package com.toutiao.cases;

import com.toutiao.config.TestConfig;
import com.toutiao.model.InterfaneName;
import com.toutiao.model.LoginCase;
import com.toutiao.utils.ConfigFile;
import com.toutiao.utils.DatabaseUtil;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class LoginTest {

    @BeforeTest
    public void beforeTest() throws IOException {

        TestConfig.getLoginUrl = ConfigFile.getUrl(InterfaneName.LOGIN);
        TestConfig.postUrl = ConfigFile.getUrl(InterfaneName.POSTDEMO);
        TestConfig.userUrl = ConfigFile.getUrl(InterfaneName.USER);
        TestConfig.getListUrl = ConfigFile.getUrl(InterfaneName.GETLIST);
        TestConfig.defaultHttpClient = new DefaultHttpClient();

    }
    @Test
    public void getDemo() throws IOException {
        SqlSession session =DatabaseUtil.getSqlsession();

        LoginCase loginCase = session.selectOne("loginCase",2);
        log.info("数据读取成功");

        HttpGet get = new HttpGet(TestConfig.userUrl);

        HttpResponse response = TestConfig.defaultHttpClient.execute(get);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

    }
    @Test
    public void postDemo() throws IOException {
        SqlSession session = DatabaseUtil.getSqlsession();
        LoginCase loginCase = session.selectOne("loginCase",4);
        System.out.println(loginCase);

        String result = getResult(loginCase);
        System.out.println(result);


    }

    private String getResult(LoginCase loginCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.postUrl);
        JSONObject param = new JSONObject();
        param.put("name",loginCase.getName());
        param.put("age",loginCase.getAge());
        System.out.println(loginCase.getAge());

        post.setHeader("content-type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        String result;
        result =EntityUtils.toString(response.getEntity(),"utf-8");

        return result;





    }



    }


