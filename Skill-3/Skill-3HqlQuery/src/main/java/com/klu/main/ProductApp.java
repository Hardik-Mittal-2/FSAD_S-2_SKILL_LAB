package com.klu.main;

import com.klu.entity.Product;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ProductApp {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		// -----------------------------
		// Insert 5–8 Products
		// -----------------------------
		session.persist(new Product("Laptop", "Electronics", 60000, 5));
		session.persist(new Product("Mouse", "Electronics", 800, 25));
		session.persist(new Product("Keyboard", "Electronics", 1500, 20));
		session.persist(new Product("Book", "Stationery", 500, 50));
		session.persist(new Product("Pen", "Stationery", 20, 200));
		session.persist(new Product("Chair", "Furniture", 3000, 10));
		session.persist(new Product("Table", "Furniture", 7000, 8));

		tx.commit();

		// -----------------------------
		// Sorting by Price Ascending
		// -----------------------------
		System.out.println("\nPrice Ascending:");
		List<Product> asc = session.createQuery("from Product p order by p.price asc", Product.class).list();
		asc.forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));

		// -----------------------------
		// Sorting by Price Descending
		// -----------------------------
		System.out.println("\nPrice Descending:");
		List<Product> desc = session.createQuery("from Product p order by p.price desc", Product.class).list();
		desc.forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));

		// -----------------------------
		// Sort by Quantity (Highest First)
		// -----------------------------
		System.out.println("\nQuantity High to Low:");
		List<Product> qty = session.createQuery("from Product p order by p.quantity desc", Product.class).list();
		qty.forEach(p -> System.out.println(p.getName() + " " + p.getQuantity()));

		// -----------------------------
		// Pagination
		// -----------------------------
		System.out.println("\nFirst 3 Products:");
		List<Product> first3 = session.createQuery("from Product", Product.class).setFirstResult(0).setMaxResults(3)
				.list();
		first3.forEach(p -> System.out.println(p.getName()));

		System.out.println("\nNext 3 Products:");
		List<Product> next3 = session.createQuery("from Product", Product.class).setFirstResult(3).setMaxResults(3)
				.list();
		next3.forEach(p -> System.out.println(p.getName()));

		// -----------------------------
		// Aggregate Functions
		// -----------------------------
		Long total = session.createQuery("select count(p) from Product p", Long.class).uniqueResult();
		System.out.println("\nTotal Products = " + total);

		Long available = session.createQuery("select count(p) from Product p where p.quantity > 0", Long.class)
				.uniqueResult();
		System.out.println("Products with stock > 0 = " + available);

		List<Object[]> grpCount = session
				.createQuery("select p.description, count(p) from Product p group by p.description", Object[].class)
				.list();
		System.out.println("\nCount by Description:");
		for (Object[] o : grpCount) {
			System.out.println(o[0] + " -> " + o[1]);
		}

		Double minPrice = session.createQuery("select min(p.price) from Product p", Double.class).uniqueResult();
		Double maxPrice = session.createQuery("select max(p.price) from Product p", Double.class).uniqueResult();
		System.out.println("\nMin Price = " + minPrice);
		System.out.println("Max Price = " + maxPrice);

		// -----------------------------
		// GROUP BY description
		// -----------------------------
		System.out.println("\nProducts grouped by Description:");
		List<Object[]> group = session.createQuery(
				"select p.description, sum(p.quantity) from Product p group by p.description", Object[].class).list();
		for (Object[] o : group) {
			System.out.println(o[0] + " -> Total Qty: " + o[1]);
		}

		// -----------------------------
		// Price Range
		// -----------------------------
		System.out.println("\nProducts between 1000 and 10000:");
		List<Product> range = session.createQuery("from Product p where p.price between 1000 and 10000", Product.class)
				.list();
		range.forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));

		// -----------------------------
		// LIKE Queries
		// -----------------------------
		System.out.println("\nNames starting with 'L':");
		session.createQuery("from Product p where p.name like 'L%'", Product.class).list()
				.forEach(p -> System.out.println(p.getName()));

		System.out.println("\nNames ending with 'r':");
		session.createQuery("from Product p where p.name like '%r'", Product.class).list()
				.forEach(p -> System.out.println(p.getName()));

		System.out.println("\nNames containing 'oo':");
		session.createQuery("from Product p where p.name like '%oo%'", Product.class).list()
				.forEach(p -> System.out.println(p.getName()));

		System.out.println("\nNames with 4 letters:");
		session.createQuery("from Product p where length(p.name)=4", Product.class).list()
				.forEach(p -> System.out.println(p.getName()));

		session.close();
		factory.close();
	}
}
