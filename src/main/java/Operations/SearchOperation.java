package Operations;

import Common.JsonReader.SearchJsonReader;
import Common.JsonWriter;
import Models.ErrorOutput;
import Models.Search.SearchOutput;
import com.google.gson.*;


public class SearchOperation implements Operation {
    @Override
    public void execute(String inputFile, String outputFile) {
        SearchJsonReader searchJsonReader = new SearchJsonReader(inputFile);
        SearchOutput searchOutput = searchJsonReader.readJson();

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json;
        if (searchOutput.getMessage() == null) {
            json = gson.toJson(searchOutput);
        }
        else {
            json = gson.toJson(new ErrorOutput(searchOutput.getMessage()));
        }

        JsonWriter jsonWriter = new JsonWriter(outputFile);
        jsonWriter.write(json);
    }
}
