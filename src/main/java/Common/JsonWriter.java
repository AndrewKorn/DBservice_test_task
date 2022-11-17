package Common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter {
    private final String outputFile;

    public JsonWriter(String outputFile) {
        this.outputFile = outputFile;
    }
    public void write(String str) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(str);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
