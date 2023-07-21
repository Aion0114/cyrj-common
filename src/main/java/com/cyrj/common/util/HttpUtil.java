package com.cyrj.common.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;


public class HttpUtil {

    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    /**
     * 发送HttpGet请求
     * @param url
     * @return
     */
    public static String sendGet(String url) {

        HttpGet httpget = new HttpGet(url);
        
        CloseableHttpResponse response = null;
        
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000).setConnectionRequestTimeout(10000).setSocketTimeout(10000).build();
        httpget.setConfig(requestConfig);
        try {
            response = httpclient.execute(httpget);
        } catch (Exception e1) {
        	response = null;
            e1.printStackTrace();
        }
        String result = null;
        try {
        	if(response != null)
        	{
        		HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity);
                }
        	}
        } catch (Exception e) {
        	result = null;
            System.out.println(e);
        } finally {
            try {
            	if(response != null)
            	{
            		response.close();
            	}
            } catch (Exception e) {
            	response = null;
                System.out.println(e);
            }
        }
        if(response == null || response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
        	return null;
        }
        return result;
    }

    /**
     * 发送HttpGet请求
     * @param url
     * @return
     */
    public static String sendGetHttps(String url) {

        SSLConnectionSocketFactory sslsf = null;
        try {
            sslsf = new SSLConnectionSocketFactory(
                    SSLContexts.custom().loadTrustMaterial(null,new TrustSelfSignedStrategy()).build(),
                    NoopHostnameVerifier.INSTANCE);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        // 创建Httpclient对象
        CloseableHttpClient httpsclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        HttpGet httpget = new HttpGet(url);

        CloseableHttpResponse response = null;

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000).setConnectionRequestTimeout(10000).setSocketTimeout(10000).build();
        httpget.setConfig(requestConfig);
        try {
            response = httpsclient.execute(httpget);
        } catch (Exception e1) {
            response = null;
            e1.printStackTrace();
        }
        String result = null;
        try {
            if(response != null)
            {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity);
                }
            }
        } catch (Exception e) {
            result = null;
            System.out.println(e);
        } finally {
            try {
                if(response != null)
                {
                    response.close();
                }
            } catch (Exception e) {
                response = null;
                System.out.println(e);
            }
        }
        if(response == null || response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
            return null;
        }
        return result;
    }

    /**
     * 发送HttpGet请求
     * @param url
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String sendGet(String url,Map headMap) throws UnsupportedEncodingException {
    	url = url.replaceAll(" ", "%20");
        HttpGet httpget = new HttpGet(url);
       httpget.setHeader("Accept", "*/*");
        if(headMap !=null){
        	httpget.setHeader("X-User",headMap.get("X-User").toString());
        	httpget.setHeader("X-Token",headMap.get("X-Token").toString());
        }
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (Exception e1) {
        	response = null;
            e1.printStackTrace();
        }
        String result = null;
        try {
        	if(response != null)
        	{
        		HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity);
                }
        	}
        } catch (ParseException | IOException e) {
            System.out.println(e);
        } finally {
            try {
            	if(response != null)
            	{
            		response.close();
            	}
            } catch (Exception e) {
            	response = null;
                System.out.println(e);
            }
        }
        if(response == null || response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
        	return null;
        }
        return result;
    }
    
    
    /**
     * 发送HttpPost请求，参数为map
     * @param url
     * @param map
     * @return
     */
    public static String sendPost(String url, Map<String, String> map) throws UnsupportedEncodingException {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8.toString());

         HttpPost httppost = new HttpPost(url);
        httppost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            System.out.println(e);
        }
        HttpEntity entity1 = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity1);
        } catch (ParseException | IOException e) {
            System.out.println(e);
        }
        return result;
    }

    /**
     * 发送不带参数的HttpPost请求
     * @param url
     * @return
     */
    public static String sendPost(String url) {
        HttpPost httppost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            System.out.println(e);
        }
        HttpEntity entity = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity);
        } catch (ParseException | IOException e) {
            System.out.println(e);
        }
        return result;
    }
    
    /**
     * 发送HttpPost请求，无参数名称
     * @param url
     * @param params
     * @return
     */
    public static String sendPost(String url, String params) throws UnsupportedEncodingException {
    	StringEntity entity = new StringEntity(params,"utf-8");//解决中文乱码问题 

        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            System.out.println(e);
        }
        HttpEntity entity1 = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity1);
        } catch (ParseException | IOException e) {
            System.out.println(e);
        }
        return result;
    }

    /**
     * 发送HttpPost请求，无参数名称
     * @param url
     * @param params
     * @return
     */
    public static String sendPostHttps(String url, String params) throws UnsupportedEncodingException {
        SSLConnectionSocketFactory sslsf = null;
        try {
            sslsf = new SSLConnectionSocketFactory(
                    SSLContexts.custom().loadTrustMaterial(null,new TrustSelfSignedStrategy()).build(),
                    NoopHostnameVerifier.INSTANCE);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        // 创建Httpclient对象
        CloseableHttpClient httpsclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

        StringEntity entity = new StringEntity(params,"utf-8");//解决中文乱码问题

        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpsclient.execute(httppost);
        } catch (IOException e) {
            System.out.println(e);
        }
        HttpEntity entity1 = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity1);
        } catch (ParseException | IOException e) {
            System.out.println(e);
        }
        return result;
    }
    
    /**
     * 发送HttpPost请求，无参数名称
     * @param url
     * @param jsonObj
     * @return
     */
    public static String sendPost(String url,JSONObject jsonObj,Map headMap) throws UnsupportedEncodingException {
    	
    	
    	StringEntity entity = new StringEntity(jsonObj.toString(),"utf-8");//解决中文乱码问题 

        HttpPost httppost = new HttpPost(url);
        httppost.setHeader("Accept", "*/*");
        if(headMap.get("X-User") != null){
            httppost.setHeader("X-User",headMap.get("X-User").toString());
        }
        if(headMap.get("X-Token") != null){
            httppost.setHeader("X-Token",headMap.get("X-Token").toString());
        }
        if(headMap.get("X-Domain") != null){
            httppost.setHeader("X-Domain",headMap.get("X-Domain").toString());
        }
        httppost.setHeader("Content-Type","application/json");
		
        httppost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            System.out.println(e);
        }
        HttpEntity entity1 = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity1);
        } catch (ParseException | IOException e) {
            System.out.println(e);
        }
        return result;
    }

    /**
     * 发送HttpPost请求，无参数名称
     * @param url
     * @param jsonObj
     * @return
     */
    public static String sendPatch(String url,JSONObject jsonObj,Map headMap) throws UnsupportedEncodingException {


        StringEntity entity = new StringEntity(jsonObj.toString(),"utf-8");//解决中文乱码问题

        HttpPatch httpPatch = new HttpPatch(url);
        httpPatch.setHeader("Accept", "*/*");
        httpPatch.setHeader("X-User",headMap.get("X-User").toString());
        httpPatch.setHeader("X-Token",headMap.get("X-Token").toString());
        httpPatch.setHeader("Content-Type","application/json");

        httpPatch.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPatch);
        } catch (IOException e) {
            System.out.println(e);
        }
        HttpEntity entity1 = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity1);
        } catch (ParseException | IOException e) {
            System.out.println(e);
        }
        return result;
    }

    public static String sendPost(String url,Map<String,String> headerMap,Map<String, String> contentMap) {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        List<NameValuePair> content = new ArrayList<NameValuePair>();
        Iterator iterator = contentMap.entrySet().iterator();           //将content生成entity
        while(iterator.hasNext()){
            Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
            content.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
        }
        CloseableHttpResponse response = null;
        try {
            Iterator headerIterator = headerMap.entrySet().iterator();          //循环增加header
            while(headerIterator.hasNext()){
                Map.Entry<String,String> elem = (Map.Entry<String, String>) headerIterator.next();
                post.addHeader(elem.getKey(),elem.getValue());
            }
            if(content.size() > 0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(content,"UTF-8");
                post.setEntity(entity);
            }
            response = httpClient.execute(post);            //发送请求并接收返回数据
            if(response != null && response.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity entity = response.getEntity();       //获取response的body部分
                result = EntityUtils.toString(entity);          //读取reponse的body部分并转化成字符串
            }
            return result;
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
        } catch (ClientProtocolException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }finally {
            try {
                httpClient.close();
                if(response != null)
                {
                    response.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }

        }
        return null;
    }
    
    /**
     * 发送HttpPost请求，无参数名称
     * @param url
     * @param params
     * @return
     */
    public static String sendPostOMS(String url, String params) throws UnsupportedEncodingException {
    	StringEntity entity = new StringEntity(params,"utf-8");//解决中文乱码问题 

        HttpPost httppost = new HttpPost(url);
        httppost.setHeader("Content-Type", "application/json;charset=UTF-8");
        httppost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            System.out.println(e);
        }
        HttpEntity entity1 = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity1);
        } catch (ParseException | IOException e) {
            System.out.println(e);
        }
        return result;
    }
    
    
    
    /**
     * 发送HttpGet请求
     * @param url
     * @return
     */
    public static String sendGet2(String url) throws UnsupportedEncodingException {

        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String result = null;
        try {
        	
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.getContentCharSet(entity);
            }
        } catch (ParseException e) {
            System.out.println(e);
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return result;
    }


    public static String sendPostToken(String url, String params,String accessToken) throws UnsupportedEncodingException {
        StringEntity entity = new StringEntity(params,"utf-8");//解决中文乱码问题

        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(entity);
        httppost.setHeader("access_token",accessToken);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            System.out.println(e);
        }
        HttpEntity entity1 = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity1);
        } catch (ParseException | IOException e) {
            System.out.println(e);
        }
        return result;
    }

    public static String sendGetToken(String url,String accessToken) throws UnsupportedEncodingException {
        url = url.replaceAll("&", "%26");
        url = url.replaceAll(" ", "%20");
        HttpGet httpget = new HttpGet(url);
        httpget.setHeader("access_token",accessToken);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (Exception e1) {
            response = null;
            e1.printStackTrace();
        }
        String result = null;
        try {
            if(response != null)
            {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity);
                }
            }
        } catch (ParseException | IOException e) {
            System.out.println(e);
        } finally {
            try {
                if(response != null)
                {
                    response.close();
                }
            } catch (Exception e) {
                response = null;
                System.out.println(e);
            }
        }
        if(response == null || response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
            return null;
        }
        return result;
    }

    /**
     * D0专用GET请求方法
     * @param url
     * @param accessToken
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String sendGetToken2(String url,String accessToken) throws UnsupportedEncodingException {
        //url = url.replaceAll("&", "%26");
        //url = url.replaceAll(" ", "%20");
        HttpGet httpget = new HttpGet(url);
        httpget.setHeader("access_token",accessToken);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (Exception e1) {
            response = null;
            e1.printStackTrace();
        }
        String result = null;
        try {
            if(response != null)
            {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity);
                }
            }
        } catch (ParseException | IOException e) {
            System.out.println(e);
        } finally {
            try {
                if(response != null)
                {
                    response.close();
                }
            } catch (Exception e) {
                response = null;
                System.out.println(e);
            }
        }
        if(response == null || response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
            return null;
        }
        return result;
    }

    /**
     * 发送HttpPost请求，参数为map
     * @param url
     * @param map2
     * @return
     */
    public static String sendPostDY(String url, Map<String, Object> map2,Map headMap) throws UnsupportedEncodingException {

        Object o = JSON.toJSON(map2);
        String join = StringUtils.join(o);
        StringEntity entity = new StringEntity(join,"utf-8");//解决中文乱码问题

        HttpPost httppost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(50000).setConnectionRequestTimeout(10000)
                .setSocketTimeout(50000).build();
        httppost.setConfig(requestConfig);
        httppost.setHeader("Content-Type", "application/json");//;charset=UTF-8
        httppost.setHeader("Accept", "*/*");
        httppost.setHeader("Cache-Control", "no-cache");
        httppost.setHeader("token", String.valueOf(headMap.get("token")));
        httppost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);

        } catch (IOException e) {
            System.out.println(e);
        }
        HttpEntity entity1 = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity1);
        } catch (ParseException | IOException e) {
            System.out.println(e);
        }
        return result;
    }

    /**
     * tapd
     * */
    public static String sendPostOMSTAPD(String url, String params) throws UnsupportedEncodingException {
        StringEntity entity = new StringEntity(params,"UTF-8");//解决中文乱码问题
        HttpPost httppost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(50000).setConnectionRequestTimeout(10000)
                .setSocketTimeout(50000).build();
        httppost.setConfig(requestConfig);

        String authString = "IGs^&SlQ" + ":" + "C274BEEF-9AE9-AEE9-F0C4-2C0C6B8FA25D";
        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
        String authStringEnc = new String(authEncBytes);

        httppost.setHeader("Content-Type", "application/json;charset=UTF-8");
        httppost.setHeader("Authorization", "Basic " +authStringEnc);
        httppost.setEntity(entity);
        CloseableHttpResponse response = null;
        String result = null;
        try {
            response = httpclient.execute(httppost);
            HttpEntity entity1 = response.getEntity();
            result = EntityUtils.toString(entity1,"utf-8");
        } catch (IOException e) {
//              System.out.println(e);
            System.out.println(url+"读取接口错误");
        }
        return result;
    }

    private final static int CONNECT_TIMEOUT = 5000; // in milliseconds
    private final static String DEFAULT_ENCODING = "UTF-8";

    public static String postData(String urlStr, String data) {
        return postData(urlStr, data, null);
    }

    public static String postData(String urlStr, String data, String contentType) {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlStr);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(CONNECT_TIMEOUT);
            if (contentType != null)
                conn.setRequestProperty("content-type", contentType);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), DEFAULT_ENCODING);
            if (data == null)
                data = "";
            writer.write(data);
            writer.flush();
            writer.close();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), DEFAULT_ENCODING));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\r\n");
            }
            return sb.toString();
        } catch (IOException e) {
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
            }
        }
        return null;
    }

}
