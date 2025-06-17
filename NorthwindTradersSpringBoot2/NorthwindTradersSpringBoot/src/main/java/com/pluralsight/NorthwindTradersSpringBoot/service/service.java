package com.pluralsight.NorthwindTradersSpringBoot.service;

import com.pluralsight.NorthwindTradersSpringBoot.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;

public class service {
    private final ProductDao productDao;

@Autowired
    public service(ProductDao productDao) {
        this.productDao = productDao;
    }
}
