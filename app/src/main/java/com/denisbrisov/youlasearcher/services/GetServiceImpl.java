package com.denisbrisov.youlasearcher.services;

import com.denisbrisov.youlasearcher.models.lot.Welcome;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.SneakyThrows;


public class GetServiceImpl implements GetService{

    private String urlString;
    private ObjectMapper mapper;

    public GetServiceImpl(){
        mapper = new ObjectMapper();
        urlString = "https://api.youla.io/api/v1/product/";
    }

    @SneakyThrows
    @Override
    public long get(String productId) {
        String path = urlString + productId;
        URL url = new URL(path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Host", "api.youla.io");
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.124 Safari/537.36 Edg/102.0.1245.44");
        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        Welcome object = null;
        try {
            object = mapper.readValue(content.toString(), Welcome.class);
        }catch (Exception ex){
            return 0;
        }
        return object.getData().getDatePublished() * 1000;
    }
}
