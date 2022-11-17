package Models.Search;

import Models.Customer;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;

import java.util.List;

public class Result {
    @Expose
    private final JsonObject criteria;
    @Expose
    private final List<Customer> results;

    public Result(JsonObject criteria, List<Customer> results) {
        this.criteria = criteria;
        this.results = results;
    }
}
