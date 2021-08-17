package goldfiletest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.example.goldfile.GoldFileTestObject;
import com.example.listener.GoldFileTestListener;
import com.example.reporter.GoldFileTestReporter;
import org.testng.ITest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.Iterator;

@Listeners({GoldFileTestListener.class, GoldFileTestReporter.class})
public class GoldFileTest implements ITest {
    public GoldFileTestObject goldFileTestObject;

    public GoldFileTest(GoldFileTestObject goldFileTestObject) {
        this.goldFileTestObject = goldFileTestObject;
    }

    @Override
    public String getTestName() {
        return this.goldFileTestObject.getName();
    }

    @BeforeClass
    public void beforeClass() {

    }

    @Test
    public void testMethod() throws Exception {
        System.out.println("Starting Test: " + goldFileTestObject.getName());

        for (String keys : goldFileTestObject.getValidResponses().keySet()) {
            JsonObject result = goldFileTestObject.getApiRequest().run(goldFileTestObject.getHost(), goldFileTestObject.getApiKey());
            String[] keySet = keys.split("\\.");
            int count = 0;
            if (keySet.length > 1) {
                for (int i = 0; i < keySet.length - 1; i++) {
                    result = result.getAsJsonObject(keySet[i]);
                    count++;
                }
            }
            String value = goldFileTestObject.getValidResponses().get(keys);
            Assert.assertEquals(result.get(keySet[count]).getAsString(), value, "Error: " + keys);
        }
        System.out.println("Finished Test: " + goldFileTestObject.getName());
    }
}

