package DAO;

import Models.Customer;
import Models.Product;
import java.sql.Date;
import java.util.List;

public interface CustomerDAO {
    List<Customer> getCustomersByLastName(String lastName);
    List<Customer> getCustomersByProductName(String productName, int minTimes);
    List<Customer> getCustomersByMinMaxExpenses(int minExpenses, int maxExpenses);
    List<Customer> getBadCustomers(int amount);
    List<Customer> getCustomersByDate(Date startDate, Date endDate);
    List<Product> getCustomerProductsByDate(Date startDate, Date endDate, int id);

    int getCountOfCustomerProductByDate(Date startDate, Date endDate, int cId, int pId);
}
