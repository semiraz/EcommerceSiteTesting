package company.data;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class StaticDataProvider extends DataReader {

    @DataProvider(name = "getData")
    public static Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "/src/test/java/company/data/purchaseOrder.json");

        return new Object[][]{
                {data.get(0)},
                {data.get(1)}
        };
    }
}

