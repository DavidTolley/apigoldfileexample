package goldfiletest;

import com.google.common.base.Strings;
import com.example.goldfile.GoldFileTestObject;
import com.example.goldfile.GoldFileTool;
import org.testng.annotations.Factory;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class GoldFileTestFactory {

    @Factory
    public Object[] goldFileTestFactory() {
        String HOST_URL = System.getenv("HOST_URL");
        if (Strings.isNullOrEmpty(HOST_URL)) {
            System.out.println("Error: HOST_URL must be defined. Example: https://api.openweathermap.org");
            throw new IllegalStateException("Error: HOST_URL must be defined. Example: https://api.openweathermap.org");
        }

        String API_KEY = System.getenv("API_KEY");
        if (Strings.isNullOrEmpty(API_KEY)) {
            System.out.println("Error: API_KEY must be defined");
            throw new IllegalStateException("Error: API_KEY must be defined");
        }

        GoldFileTool goldFileTool = new GoldFileTool();
        ArrayList<File> goldFiles = null;
        try {
            goldFiles = goldFileTool.readGoldfiles();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ArrayList<GoldFileTestObject> tests = goldFileTool.parseGoldFiles(goldFiles, HOST_URL, API_KEY);
        ArrayList<GoldFileTest> testSuite = new ArrayList<>();
        for (GoldFileTestObject test : tests) {
            testSuite.add(new GoldFileTest(test));
        }
        return testSuite.toArray();
    }
}
