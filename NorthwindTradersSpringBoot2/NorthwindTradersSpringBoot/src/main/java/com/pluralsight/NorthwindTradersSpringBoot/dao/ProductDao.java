package com.pluralsight.NorthwindTradersSpringBoot.dao;

import com.pluralsight.NorthwindTradersSpringBoot.model.Product;

import java.util.List;

public interface ProductDao {

    public void add (Product product);
    public List<Product> getAll();
    public void delete(int productId);
    public void update(Product updatedProd);
    Product findById(int productId );
    Product getName(String name);
    public List<Product>getByPrice (double min, double max);

}
