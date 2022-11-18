package Operations;

import JsonReader.StatJsonReader;
import Models.ErrorOutput;
import Models.Stat.StatOutput;

public class StatOperation extends Operation {
    @Override
    public String doOperation(String inputFile) {
        StatJsonReader statJsonReader = new StatJsonReader(inputFile);
        StatOutput statOutput = statJsonReader.readJson();

        String json;
        if (statOutput.getMessage() == null) {
            json = getGson().toJson(statOutput);
        }
        else {
            json = getGson().toJson(new ErrorOutput(statOutput.getMessage()));
        }
        return json;
    }
}
