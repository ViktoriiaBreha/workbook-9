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

        SpringApplication.run(App.class, args);
    }
}
