package Common.JsonReader;

import DAO.CustomerDAO;
import DAO.CustomerDAOImpl;
import Models.Customer;
import Models.Product;
import Models.Stat.CustomerStat;
import Models.Stat.PurchaseStat;
import Models.Stat.StatOutput;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class StatJsonReader {
    private final String inputFile;

    public StatJsonReader(String inputFile) {
        this.inputFile = inputFile;
    }

    public StatOutput readJson() {
        CustomerDAO dao = new CustomerDAOImpl();
        StatOutput statOutput = new StatOutput();
        File file = new File(inputFile);
        if (file.exists()) {
            try {
                InputStream is = new FileInputStream(inputFile);
                String jsonTxt = IOUtils.toString(is, StandardCharsets.UTF_8);
                if (jsonTxt.isEmpty()) {
                    statOutput.setMessage("input file is empty");
                    return statOutput;
                }
                JsonObject jsonObject = (JsonObject) JsonParser.parseString(jsonTxt);

                Date startDate = Date.valueOf(jsonObject.get("startDate").getAsString());
                Date endDate = Date.valueOf(jsonObject.get("endDate").getAsString());
                if (startDate.after(endDate)) {
                    statOutput.setMessage("invalid data");
                    return statOutput;
                }
                int totalDays = (int) (endDate.toLocalDate().toEpochDay() - startDate.toLocalDate().toEpochDay());
                List<CustomerStat> customers = new ArrayList<>();
                statOutput.setTotalDays(totalDays);
                statOutput.setCustomers(customers);

                List<Customer> customerList = dao.getCustomersByDate(startDate, endDate);
                for (Customer customer : customerList) {
                    List<PurchaseStat> purchases = new ArrayList<>();
                    CustomerStat customerStat = new CustomerStat(customer.getFirstName() + " " + customer.getLastName(), purchases);
                    List<Product> products = dao.getCustomerProductsByDate(startDate, endDate, customer.getId());

                    for (Product product : products) {
                        int count = dao.getCountOfCustomerProductByDate(startDate, endDate, customer.getId(), product.getId());
                        int expenses = count * product.getPrice();
                        PurchaseStat purchaseStat = new PurchaseStat(product.getName(), expenses);
                        purchases.add(purchaseStat);
                        customerStat.setTotalExpenses(customerStat.getTotalExpenses() + expenses);
                    }
                    customers.add(customerStat);
                    statOutput.setTotalExpenses(statOutput.getTotalExpenses() + customerStat.getTotalExpenses());
                }
                statOutput.setAvgExpenses(statOutput.getTotalExpenses() / statOutput.getCustomers().size());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            statOutput.setMessage("file does not exist");
        }
        return statOutput;
    }
}
