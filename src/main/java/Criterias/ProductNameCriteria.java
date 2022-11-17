package Criterias;

import DAO.CustomerDAO;
import Models.Customer;
import Models.Search.Result;
import com.google.gson.JsonObject;

import java.util.List;

public class ProductNameCriteria implements Criteria {
    @Override
    public Result find(JsonObject jsonObject, CustomerDAO dao) {
        String productName = jsonObject.get("productName").getAsString();
        int minTimes = jsonObject.get("minTimes").getAsInt();
        List<Customer> customers = dao.getCustomersByProductName(productName, minTimes);
        return new Result(jsonObject, customers);
    }
}
