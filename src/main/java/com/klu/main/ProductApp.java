package com.klu.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.klu.entity.Product;

public class ProductApp {

    public static void main(String[] args) {

        // Step 1: Load Hibernate Configuration
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();

        /* -------------------------------
         * CREATE (Insert Product)
         * ------------------------------- */
        
        
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Product p1 = new Product();
        p1.setPname("Dragon Fruit");
        p1.setCost(250);

        Product p2 = new Product();
        p2.setPname("Oranges");
        p2.setCost(150);

        session.persist(p1);
        session.persist(p2);

        tx.commit();
        session.close();

        /* -------------------------------
         * READ (Retrieve Product by ID)
         * ------------------------------- */
        
        session = sf.openSession();
        Product prod = session.get(Product.class, 1);
        System.out.println("Retrieved Product: " + prod);
        session.close();

        /* -------------------------------
         * UPDATE (Update Product Cost)
         * ------------------------------- */
        
        session = sf.openSession();
        tx = session.beginTransaction();

        prod.setCost(120);
        session.merge(prod);

        tx.commit();
        session.close();

        /* -------------------------------
         * DELETE (Delete Product by ID)
         * ------------------------------- */
        session = sf.openSession();
        tx = session.beginTransaction();

        Product deleteProd = session.get(Product.class, 2);
        if (deleteProd != null) {
            session.remove(deleteProd);
        }

        tx.commit();
        session.close();

        // Close SessionFactory
        sf.close();
    }
}
