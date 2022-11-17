package Criterias;

import DAO.CustomerDAO;
import Models.Customer;
import Models.Search.Result;
import com.google.gson.JsonObject;
import java.util.List;

public class BadCustomersCriteria implements Criteria {
    @Override
    public Result find(JsonObject jsonObject, CustomerDAO dao) {
        int badCustomersCount = jsonObject.get("badCustomers").getAsInt();
        List<Customer> customers = dao.getBadCustomers(badCustomersCount);
        return new Result(jsonObject, customers);
    }
}
