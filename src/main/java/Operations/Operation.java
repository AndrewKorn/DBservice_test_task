package Operations;

import Common.JsonWriter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;

@Getter
public abstract class Operation {
    private final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public abstract String doOperation(String inputFile);

    private void writeJson(String outputFile, String json) {
        JsonWriter jsonWriter = new JsonWriter(outputFile);
        jsonWriter.write(json);
    }

    public void execute(String inputFile, String outputFile) {
        String json = doOperation(inputFile);
        writeJson(outputFile, json);
    }
}
