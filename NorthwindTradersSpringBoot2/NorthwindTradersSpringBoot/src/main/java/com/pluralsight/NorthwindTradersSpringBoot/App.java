package com.pluralsight.NorthwindTradersSpringBoot;

import com.pluralsight.NorthwindTradersSpringBoot.dao.ProductDao;
import com.pluralsight.NorthwindTradersSpringBoot.dao.SimpleProductDao;
import com.pluralsight.NorthwindTradersSpringBoot.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class App {
    private ProductDao productDao;
    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(App.class, args);
        ProductDao productDao = context.getBean(SimpleProductDao.class);

        Scanner scanner = new Scanner(System.in);

        boolean run = true;
        while (run){
            System.out.println("Choose from the next options");
            System.out.println("1. List Products");
            System.out.println("2. Add Product");
            System.out.println("3. Delete product");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();

            switch (choice){
                case 1:
                    List <Product> products = productDao.getAll();
                    for (Product product: products){
                        System.out.println(product);
                    }
                    break;
                case 2:
                    System.out.print("Enter product ID: ");
                    int addId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Add product name: ");
                    String addName = scanner.nextLine();
                    System.out.print("Add product category: ");
                    String addCategory = scanner.nextLine();
                    System.out.print("Add price: $");
                    double addPrice = scanner.nextDouble();

                    Product product = new Product(addId, addName, addCategory,addPrice);
                    productDao.add(product);
                    System.out.println("Product added successfully ");
                    break;
                case 3:
                    System.out.print("Enter product id: ");
                    int deleteId = scanner.nextInt();
                    productDao.delete(deleteId);
                    break;
                default:
                    System.out.println("Invalid input, try again");
                    break;
            }
        }



    }
}
