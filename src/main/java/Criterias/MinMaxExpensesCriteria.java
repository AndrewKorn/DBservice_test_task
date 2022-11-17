package Criterias;

import DAO.CustomerDAO;
import Models.Customer;
import Models.Search.Result;
import com.google.gson.JsonObject;


import java.util.List;

public class MinMaxExpensesCriteria implements Criteria {
    @Override
    public Result find(JsonObject jsonObject, CustomerDAO dao) {
        int minExpenses = jsonObject.get("minExpenses").getAsInt();
        int maxExpenses = jsonObject.get("maxExpenses").getAsInt();
        List<Customer> customers = dao.getCustomersByMinMaxExpenses(minExpenses, maxExpenses);
        return new Result(jsonObject, customers);

    }
}
