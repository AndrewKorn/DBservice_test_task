package Criterias;

import DAO.CustomerDAO;
import Models.TablesEntities.Customer;
import Models.Search.Result;
import com.google.gson.JsonObject;

import java.util.List;

public class LastNameCriteria implements Criteria {
    @Override
    public Result find(JsonObject jsonObject, CustomerDAO dao) {
        String lastName = jsonObject.get("lastName").getAsString();
        List<Customer> customers = dao.getCustomersByLastName(lastName);
        return new Result(jsonObject, customers);
    }
}
