package com.org.client;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import java.io.IOException;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class RestClient {
    //Create the connection with server
    //GET url method, this will give the GitHub url
    public CloseableHttpResponse get(String url) throws IOException {
       CloseableHttpClient httpClient = HttpClients.createDefault();
       HttpGet httpGet = new HttpGet(url); //http get request
       CloseableHttpResponse closableHttpResponse =  httpClient.execute(httpGet); // hit the get url

       return closableHttpResponse;


    }
}
