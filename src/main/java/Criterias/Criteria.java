package Criterias;

import DAO.CustomerDAO;
import Models.Search.Result;
import com.google.gson.JsonObject;

public interface Criteria {
    Result find(JsonObject jsonObject, CustomerDAO dao);
}
