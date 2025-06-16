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
            System.out.println("4. Update product");
            System.out.println("5. Search product");
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
                case 4:
                    System.out.print("Enter product ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();

                    Product product1 = productDao.findById(updateId);
                    if (product1 != null){
                        System.out.println("We have now: " + product1);
                        System.out.print("Enter new ID (0 to skip): ");
                        int newId = scanner.nextInt();
                        scanner.nextLine();
                        if (newId>0 && productDao.findById(newId)==null){
                            product1.setProductId(newId);
                        }
                        System.out.print("Enter new name (enter to skip): ");
                        String newName = scanner.nextLine();
                        if (!newName.isEmpty())product1.setName(newName);
                        System.out.print("Enter new category (enter to skip): ");
                        String newCategory = scanner.nextLine();
                        if (!newCategory.isEmpty())product1.setCategory(newCategory);
                        System.out.print("Enter new price(0 to skip): ");
                        double newPrice = scanner.nextDouble();
                        if (newPrice>0) product1.setPrice(newPrice);
                    } else {
                        System.out.println("No product with that ID");
                    }
                    break;
                case 5:
                    System.out.print("Enter product ID to search: ");
                    int findId = scanner.nextInt();
                    scanner.nextLine();
                    Product product2 = productDao.findById(findId);
                    if (product2 != null){
                        System.out.println(product2);
                    } else {
                        System.out.println("No product with that ID");
                    }
                    break;
                default:
                    System.out.println("Invalid input, try again");
                    break;
            }}
    }
}
