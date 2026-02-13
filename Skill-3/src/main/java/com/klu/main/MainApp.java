package com.klu.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.klu.entity.Product;

public class MainApp {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        session.beginTransaction();

        // ✅ Insert records
        session.persist(new Product("Laptop", "Electronics", 65000, 10));
        session.persist(new Product("Phone", "Electronics", 30000, 20));
        session.persist(new Product("Mouse", "Accessory", 500, 50));
        session.persist(new Product("Keyboard", "Accessory", 1500, 30));
        session.persist(new Product("Monitor", "Electronics", 12000, 15));
        session.persist(new Product("Tablet", "Electronics", 25000, 8));

        System.out.println("Data Inserted");

        // ✅ Sort by price ASC
        System.out.println("\nPrice ASC");
        List<Product> l1 = session.createQuery("from Product order by price asc", Product.class).list();
        l1.forEach(System.out::println);

        // ✅ Sort by price DESC
        System.out.println("\nPrice DESC");
        List<Product> l2 = session.createQuery("from Product order by price desc", Product.class).list();
        l2.forEach(System.out::println);

        // ✅ Sort by quantity DESC
        System.out.println("\nQuantity DESC");
        List<Product> l3 = session.createQuery("from Product order by quantity desc", Product.class).list();
        l3.forEach(System.out::println);

        // ✅ Pagination First 3
        System.out.println("\nFirst 3 Products");
        Query<Product> q1 = session.createQuery("from Product", Product.class);
        q1.setFirstResult(0);
        q1.setMaxResults(3);
        q1.list().forEach(System.out::println);

        // ✅ Pagination Next 3
        System.out.println("\nNext 3 Products");
        Query<Product> q2 = session.createQuery("from Product", Product.class);
        q2.setFirstResult(3);
        q2.setMaxResults(3);
        q2.list().forEach(System.out::println);

        // ✅ Count total
        Long total = session.createQuery("select count(*) from Product", Long.class).uniqueResult();
        System.out.println("\nTotal Products = " + total);

        // ✅ Count quantity > 0
        Long available = session.createQuery(
                "select count(*) from Product where quantity > 0", Long.class)
                .uniqueResult();
        System.out.println("Available Products = " + available);

        // ✅ Group by description
        System.out.println("\nGroup By Description");
        List<Object[]> g = session.createQuery(
                "select description, count(*) from Product group by description",
                Object[].class).list();
        for (Object[] o : g) {
            System.out.println(o[0] + " -> " + o[1]);
        }

        // ✅ Min & Max price
        Object[] mm = session.createQuery(
                "select min(price), max(price) from Product",
                Object[].class).uniqueResult();
        System.out.println("\nMin Price = " + mm[0]);
        System.out.println("Max Price = " + mm[1]);

        // ✅ Price range
        System.out.println("\nPrice between 1000 and 30000");
        session.createQuery(
                "from Product where price between 1000 and 30000",
                Product.class).list().forEach(System.out::println);

        // ✅ LIKE queries
        System.out.println("\nStarts with L");
        session.createQuery("from Product where name like 'L%'", Product.class)
                .list().forEach(System.out::println);

        System.out.println("\nEnds with e");
        session.createQuery("from Product where name like '%e'", Product.class)
                .list().forEach(System.out::println);

        System.out.println("\nContains 'top'");
        session.createQuery("from Product where name like '%top%'", Product.class)
                .list().forEach(System.out::println);

        System.out.println("\nName length = 5");
        session.createQuery("from Product where length(name)=5", Product.class)
                .list().forEach(System.out::println);

        session.getTransaction().commit();
        session.close();
        factory.close();
    }
}
