package com.pluralsight.NorthwindTradersSpringBoot.dao;

import com.pluralsight.NorthwindTradersSpringBoot.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class SimpleProductDao implements ProductDao{
    private List<Product> products;

    public SimpleProductDao() {
        this.products = new ArrayList<>();
        this.products.add(new Product(81,"Candy", "Sweets", 23.67));
        this.products.add(new Product(82, "Beef", "Meat", 39.99));
        this.products.add(new Product(83, "Bread", "Pastry", 3.67));
    }

    @Override
    public void add(Product product) {
        this.products.add(product);

    }

    @Override
    public List<Product> getAll() {
        return this.products;
    }

    @Override
    public void delete(int productId) {
        for (Product product: products){
            if (product.getProductId()== productId){
                products.remove(product);
            }
        }
    }

}
