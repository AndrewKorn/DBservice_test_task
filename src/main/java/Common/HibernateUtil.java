package Common;

import Models.TablesEntities.Customer;
import Models.TablesEntities.Product;
import Models.TablesEntities.Purchase;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(Purchase.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }
}
