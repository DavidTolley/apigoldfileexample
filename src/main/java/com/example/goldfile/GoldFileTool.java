package com.example.goldfile;

import com.google.common.base.Strings;
import com.example.apicall.APIRequest;
import com.example.util.RequestMethod;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigObject;
import com.example.util.Util;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class GoldFileTool {

//    public static void main(String args[]) throws Exception {
//        String HOST_URL = System.getenv("HOST_URL");
//        if (Strings.isNullOrEmpty(HOST_URL)) {
//            throw new IllegalStateException("Error: HOST_URL must be defined. Example: https://api.openweathermap.org");
//        }
//
//        String API_KEY = System.getenv("API_KEY");
//        if (Strings.isNullOrEmpty(API_KEY)) {
//            throw new IllegalStateException("Error: API_KEY must be defined");
//        }
//
//        GoldFileTool goldFileTool = new GoldFileTool();
//        ArrayList<File> goldFiles = goldFileTool.readGoldfiles();
//        ArrayList<GoldFileTestObject> tests = goldFileTool.parseGoldFiles(goldFiles);
//        for(GoldFileTestObject test: tests){
//            test.getApiRequest();
//            test.getApiRequest().run(host, sessionID);
//        }
//    }

    public GoldFileTool(){

    }

    public ArrayList<File> readGoldfiles() throws URISyntaxException {
        String goldfileDirectory = "";
        if (Strings.isNullOrEmpty(System.getProperty("goldfileDirectory"))) {
            goldfileDirectory = "/goldfiles";
        } else {
            goldfileDirectory = System.getProperty("goldfileDirectory");
            if (!goldfileDirectory.endsWith("/")) goldfileDirectory = goldfileDirectory + "/";
            Util.setExternalGoldfile();
        }

        URL goldFileUrl = GoldFileTool.class.getResource(goldfileDirectory);
        File goldFileDirectory = new File(goldFileUrl.toURI());
        FileUtils.listFiles(goldFileDirectory, null, true);
        IOFileFilter filter = new WildcardFileFilter("*.gold");
        ArrayList<File> goldFiles = new ArrayList<>(FileUtils.listFiles(goldFileDirectory, filter, TrueFileFilter.TRUE));

        return goldFiles;
    }

    public ArrayList<GoldFileTestObject> parseGoldFiles(ArrayList<File> goldFiles, String host, String apiKey){
        ArrayList<GoldFileTestObject> goldFileTestObjects = new ArrayList<>();
        for(File goldFile: goldFiles){
            Config goldFileConfig = ConfigFactory.parseFile(goldFile);
            goldFileTestObjects.addAll(parseGoldFile(goldFileConfig, host, apiKey));
        }
        return goldFileTestObjects;
    }

    public ArrayList<GoldFileTestObject> parseGoldFile(Config goldFileConfig, String host, String apiKey) {
        ArrayList<GoldFileTestObject> goldFileTestObjects = new ArrayList<>();
        Iterator testIterator = goldFileConfig.getObjectList("tests").iterator();
        while (testIterator.hasNext()) {
            ConfigObject testConfigObject = (ConfigObject) testIterator.next();
            String name = testConfigObject.get("name").unwrapped().toString();
            String apiEndPoint = testConfigObject.get("api_endpoint").unwrapped().toString();
            String method = testConfigObject.get("method").unwrapped().toString();
            HashMap<String, String> response = (HashMap<String, String>)testConfigObject.get("response").unwrapped();

            RequestMethod requestMethod = RequestMethod.valueOf(method);

            APIRequest apiRequest = new APIRequest(apiEndPoint, requestMethod);

            GoldFileTestObject goldFileTestObject = new GoldFileTestObject(name, host, apiKey, apiRequest, response);
            goldFileTestObjects.add(goldFileTestObject);
        }
        return goldFileTestObjects;
    }
}