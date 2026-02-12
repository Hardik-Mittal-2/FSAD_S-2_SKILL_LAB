package com.klu.main;

import com.klu.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductApp {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // Insert records
        session.persist(new Product("Laptop", "Electronics", 80000, 5));
        session.persist(new Product("Mouse", "Electronics", 500, 50));
        session.persist(new Product("Keyboard", "Electronics", 1500, 30));
        session.persist(new Product("Chair", "Furniture", 2500, 10));
        session.persist(new Product("Desk", "Furniture", 7000, 3));
        session.persist(new Product("Pen", "Stationery", 20, 200));

        tx.commit();

        // ---------------- Sorting ----------------
        System.out.println("\nPrice ASC");
        session.createQuery("from Product order by price asc", Product.class)
                .list().forEach(System.out::println);

        System.out.println("\nPrice DESC");
        session.createQuery("from Product order by price desc", Product.class)
                .list().forEach(System.out::println);

        System.out.println("\nQuantity High -> Low");
        session.createQuery("from Product order by quantity desc", Product.class)
                .list().forEach(System.out::println);

        // ---------------- Pagination ----------------
        System.out.println("\nFirst 3");
        session.createQuery("from Product", Product.class)
                .setFirstResult(0)
                .setMaxResults(3)
                .list().forEach(System.out::println);

        System.out.println("\nNext 3");
        session.createQuery("from Product", Product.class)
                .setFirstResult(3)
                .setMaxResults(3)
                .list().forEach(System.out::println);

        // ---------------- Aggregates ----------------
        Long total = session.createQuery("select count(*) from Product", Long.class)
                .uniqueResult();
        System.out.println("\nTotal Products = " + total);

        Long available = session.createQuery(
                "select count(*) from Product where quantity > 0", Long.class)
                .uniqueResult();
        System.out.println("Available Products = " + available);

        System.out.println("\nCount by Description");
        List<Object[]> list = session.createQuery(
                "select description, count(*) from Product group by description",
                Object[].class).list();
        for (Object[] o : list) {
            System.out.println(o[0] + " -> " + o[1]);
        }

        Object[] mm = session.createQuery(
                "select min(price), max(price) from Product", Object[].class)
                .uniqueResult();
        System.out.println("\nMin Price = " + mm[0]);
        System.out.println("Max Price = " + mm[1]);

        // ---------------- WHERE ----------------
        System.out.println("\nPrice between 1000 and 10000");
        session.createQuery(
                "from Product where price between 1000 and 10000",
                Product.class).list().forEach(System.out::println);

        // ---------------- LIKE ----------------
        System.out.println("\nStarts with L");
        session.createQuery(
                "from Product where name like 'L%'",
                Product.class).list().forEach(System.out::println);

        System.out.println("\nEnds with r");
        session.createQuery(
                "from Product where name like '%r'",
                Product.class).list().forEach(System.out::println);

        System.out.println("\nContains a");
        session.createQuery(
                "from Product where name like '%a%'",
                Product.class).list().forEach(System.out::println);

        System.out.println("\nLength = 3");
        session.createQuery(
                "from Product where length(name)=3",
                Product.class).list().forEach(System.out::println);

        session.close();
        factory.close();
    }
}
