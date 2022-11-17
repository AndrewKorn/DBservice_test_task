package Operations;

import Common.JsonReader.StatJsonReader;
import Common.JsonWriter;
import Models.ErrorOutput;
import Models.Stat.StatOutput;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StatOperation implements Operation {
    @Override
    public void execute(String inputFile, String outputFile) {
        StatJsonReader statJsonReader = new StatJsonReader(inputFile);
        StatOutput statOutput = statJsonReader.readJson();

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json;
        if (statOutput.getMessage() == null) {
            json = gson.toJson(statOutput);
        }
        else {
            json = gson.toJson(new ErrorOutput(statOutput.getMessage()));
        }

        JsonWriter jsonWriter = new JsonWriter(outputFile);
        jsonWriter.write(json);
    }
}
