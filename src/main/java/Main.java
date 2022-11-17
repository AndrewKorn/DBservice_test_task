import Operations.Operation;
import Common.Config;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Object> operationsConfig = new Config("operations.properties").getConfig();
        String operationKey = args[0];
        Operation operation = (Operation) operationsConfig.get(operationKey);
        operation.execute(args[1], args[2]);
    }
}
