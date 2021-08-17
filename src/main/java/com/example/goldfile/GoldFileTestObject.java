package com.example.goldfile;

import com.example.apicall.APIRequest;

import java.util.HashMap;

public class GoldFileTestObject {

    private String name;
    private String host;
    private String apiKey;
    private APIRequest apiRequest;
    private HashMap<String, String> validResponses;

    public GoldFileTestObject(String name, String host, String apiKey, APIRequest apiRequest, HashMap<String, String> validResponses){
        this.name = name;
        this.host = host;
        this.apiKey = apiKey;
        this.apiRequest = apiRequest;
        this.validResponses = validResponses;
    }

    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }

    public String getApiKey() {
        return apiKey;
    }

    public APIRequest getApiRequest() {
        return apiRequest;
    }

    public HashMap<String, String> getValidResponses() {
        return validResponses;
    }
}
