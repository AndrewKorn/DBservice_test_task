package JsonReader;

import Common.Config;
import Criterias.Criteria;
import DAO.CustomerDAO;
import DAO.CustomerDAOImpl;
import Models.Search.Result;
import Models.Search.SearchOutput;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.io.IOUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchJsonReader {
    private final String inputFile;

    public SearchJsonReader(String inputFile) {
        this.inputFile = inputFile;
    }

    public SearchOutput readJson() {
        Map<String, Object> criteriasConfig = new Config("criterias.properties").getConfig();
        CustomerDAO dao = new CustomerDAOImpl();
        SearchOutput searchOutput = new SearchOutput();

        File file = new File(inputFile);
        if (file.exists()) {
            try {
                List<Result> resultList = new ArrayList<>();
                InputStream is = new FileInputStream(inputFile);
                String jsonTxt = IOUtils.toString(is, StandardCharsets.UTF_8);
                if (jsonTxt.isEmpty()) {
                    searchOutput.setMessage("input file is empty");
                    return searchOutput;
                }

                JsonObject jsonObject = (JsonObject) com.google.gson.JsonParser.parseString(jsonTxt);
                if (!jsonObject.has("criterias")) {
                    searchOutput.setMessage("incorrect input");
                    return searchOutput;
                }
                JsonArray jsonArray = jsonObject.getAsJsonArray("criterias");
                if (jsonArray.isEmpty()) {
                    searchOutput.setMessage("criterias are empty");
                    return searchOutput;
                }

                for (Object o : jsonArray) {
                    JsonObject jsonsObject = (JsonObject) o;
                    String criteriaKey = (String) jsonsObject.keySet().toArray()[0];
                    if (!criteriasConfig.containsKey(criteriaKey)) {
                        searchOutput.setMessage("unknown criterion " + criteriaKey);
                        return searchOutput;
                    }
                    Criteria criteria = (Criteria) criteriasConfig.get(criteriaKey);
                    Result newResult = criteria.find(jsonsObject, dao);
                    resultList.add(newResult);
                }
                searchOutput.setResults(resultList);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            searchOutput.setMessage("file does not exist");
        }
        return searchOutput;
    }
}
