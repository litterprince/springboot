package com.example1.tools;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {
    public String doGet(String uri, Map<String, Object> param){
        HttpGet httpGet = new HttpGet(uri);
        try{
            URIBuilder uriBuilder = new URIBuilder(httpGet.getURI());
            if(param != null) {
                for (String key : param.keySet()) {
                    uriBuilder.setParameter(key, param.get(key).toString());
                }
            }
            httpGet.setURI(uriBuilder.build());
        }catch(URISyntaxException e){
            e.printStackTrace();
        }
        return sendHttpGet(httpGet);
    }

    private String sendHttpGet(HttpGet httpGet){
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try{
            httpClient = HttpClients.createDefault();
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(response != null) response.close();
                if(httpClient != null) httpClient.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return responseContent;
    }

    public String doPost(String uri, Map<String, Object> param){
        HttpPost httpPost = new HttpPost(uri);
        if(param != null) {
            List<NameValuePair> parameters = getPostParams(param);
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(parameters, HTTP.UTF_8));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sendHttpPost(httpPost);
    }

    private String sendHttpPost(HttpPost httpPost){
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try{
            httpClient = HttpClients.createDefault();
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(response != null) response.close();
                if(httpClient !=null) httpClient.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return responseContent;
    }

    private List<NameValuePair> getPostParams(Map<String, Object> param){
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        for (String key : param.keySet()) {
            parameters.add(new BasicNameValuePair(key, param.get(key).toString()));
        }
        return parameters;
    }
}
