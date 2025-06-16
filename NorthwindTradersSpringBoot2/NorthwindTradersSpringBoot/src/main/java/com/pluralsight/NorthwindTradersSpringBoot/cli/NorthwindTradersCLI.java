package com.pluralsight.NorthwindTradersSpringBoot.cli;


import com.pluralsight.NorthwindTradersSpringBoot.dao.ProductDao;
import com.pluralsight.NorthwindTradersSpringBoot.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class NorthwindTradersCLI implements CommandLineRunner {
    @Autowired
    private ProductDao productDao;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        boolean run = true;
        while (run){
            System.out.println("Choose from the next options");
            System.out.println("1. List Products");
            System.out.println("2. Add Product");
            System.out.println("3. Delete product");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    List<Product> products = productDao.getAll();
                    for (Product product : products) {
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

                    Product product = new Product(addId, addName, addCategory, addPrice);
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
            }}
    }
}
