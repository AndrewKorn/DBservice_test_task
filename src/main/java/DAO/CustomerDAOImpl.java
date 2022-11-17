package DAO;

import Models.Customer;
import Common.HibernateUtil;
import Models.Product;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public List<Customer> getCustomersByLastName(String lastName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query= session.createQuery("from Customer where lastName=:lastName");
        query.setParameter("lastName", lastName);
        List<Customer> customers = query.getResultList();

        session.getTransaction().commit();
        session.close();
        return customers;
    }
    @Override
    public List<Customer> getCustomersByProductName(String productName, int minTimes) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query= session.createSQLQuery("" +
                "SELECT customer.id, customer.first_name,customer.last_name from customer " +
                "INNER JOIN purchase ON customer.id = purchase.customer_id " +
                "INNER JOIN product ON purchase.product_id = product.id " +
                "WHERE product.name =:productName " +
                "GROUP BY customer.id, customer.first_name, customer.last_name " +
                "HAVING COUNT(product.name) > :minTimes").addEntity(Customer.class);
        query.setParameter("productName", productName);
        query.setParameter("minTimes", minTimes);
        List<Customer> customers = query.getResultList();

        session.getTransaction().commit();
        session.close();
        return customers;
    }
    @Override
    public List<Customer> getCustomersByMinMaxExpenses(int minExpenses, int maxExpenses) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query= session.createSQLQuery("" +
                "SELECT customer.id, customer.first_name, customer.last_name " +
                "FROM customer " +
                "INNER JOIN purchase ON customer.id = purchase.customer_id " +
                "INNER JOIN product ON purchase.product_id = product.id " +
                "GROUP BY customer.id, customer.first_name, customer.last_name " +
                "HAVING  SUM(product.price) BETWEEN :minExpenses AND :maxExpenses ").addEntity(Customer.class);
        query.setParameter("minExpenses", minExpenses);
        query.setParameter("maxExpenses", maxExpenses);
        List<Customer> customers = query.getResultList();

        session.getTransaction().commit();
        session.close();
        return customers;
    }
    @Override
    public List<Customer> getBadCustomers(int amount) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query= session.createSQLQuery("" +
                "SELECT customer.id, customer.first_name, customer.last_name, COUNT(product.id) cnt " +
                "FROM customer " +
                "INNER JOIN purchase ON customer.id = purchase.customer_id " +
                "INNER JOIN product ON purchase.product_id = product.id " +
                "GROUP BY customer.id, customer.first_name, customer.last_name " +
                "ORDER BY cnt asc " +
                "LIMIT :amount").addEntity(Customer.class);
        query.setParameter("amount", amount);
        List<Customer> customers = query.getResultList();

        session.getTransaction().commit();
        session.close();
        return customers;
    }
    @Override
    public List<Customer> getCustomersByDate(Date startDate, Date endDate) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query= session.createSQLQuery("" +
                "SELECT DISTINCT customer.id, customer.first_name,customer.last_name from customer " +
                "INNER JOIN purchase ON customer.id = purchase.customer_id " +
                "INNER JOIN product ON purchase.product_id = product.id " +
                "WHERE purchase.date BETWEEN :startDate AND :endDate ").addEntity(Customer.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        List<Customer> customers = query.getResultList();

        session.getTransaction().commit();
        session.close();
        return customers;
    }
    @Override
    public List<Product> getCustomerProductsByDate(Date startDate, Date endDate, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query= session.createSQLQuery("" +
                "SELECT DISTINCT product.id, product.name, product.price from customer " +
                "INNER JOIN purchase ON customer.id = purchase.customer_id " +
                "INNER JOIN product ON purchase.product_id = product.id " +
                "WHERE purchase.date BETWEEN :startDate AND :endDate " +
                "AND customer.id =:cid").addEntity(Product.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        query.setParameter("cid", id);
        List<Product> products = query.getResultList();

        session.getTransaction().commit();
        session.close();
        return products;
    }

    @Override
    public int getCountOfCustomerProductByDate(Date startDate, Date endDate, int cId, int pId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query= session.createSQLQuery("" +
                "SELECT COUNT(product.id) from customer " +
                "INNER JOIN purchase ON customer.id = purchase.customer_id " +
                "INNER JOIN product ON purchase.product_id = product.id " +
                "WHERE purchase.date BETWEEN :startDate AND :endDate " +
                "AND customer.id =:cid " +
                "AND product.id = :pid ");
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        query.setParameter("cid", cId);
        query.setParameter("pid", pId);

        int count = Integer.parseInt(query.uniqueResult().toString());
        session.getTransaction().commit();
        session.close();

        return count;
    }
}
