package com.pluralsight.NorthwindTradersSpringBoot.service;

import com.pluralsight.NorthwindTradersSpringBoot.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;

public class Service {
    private final ProductDao productDao;

@Autowired
    public Service(ProductDao productDao) {
        this.productDao = productDao;
    }
}
