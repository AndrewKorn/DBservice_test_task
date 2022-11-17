package Common;

import Models.Customer;
import Models.Product;
import Models.Purchase;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private void HibernateSessionFactoryUtil() {}

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
