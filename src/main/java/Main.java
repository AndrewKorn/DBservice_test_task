import Operations.Operation;
import Common.Config;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            Map<String, Object> operationsConfig = new Config("operations.properties").getConfig();
            String operationKey = args[0];
            if (operationsConfig.containsKey(operationKey)) {
                Operation operation = (Operation) operationsConfig.get(operationKey);
                operation.execute(args[1], args[2]);
            }
            else {
                System.out.println("Operation " + operationKey + " not found");
            }
        }
        catch (IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
               InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
