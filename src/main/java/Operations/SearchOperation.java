package Operations;

import JsonReader.SearchJsonReader;
import Models.ErrorOutput;
import Models.Search.SearchOutput;

public class SearchOperation extends Operation {
    @Override
    public String doOperation(String inputFile) {
        SearchJsonReader searchJsonReader = new SearchJsonReader(inputFile);
        SearchOutput searchOutput = searchJsonReader.readJson();

        String json;
        if (searchOutput.getMessage() == null) {
            json = getGson().toJson(searchOutput);
        }
        else {
            json = getGson().toJson(new ErrorOutput(searchOutput.getMessage()));
        }
        return json;
    }
}
