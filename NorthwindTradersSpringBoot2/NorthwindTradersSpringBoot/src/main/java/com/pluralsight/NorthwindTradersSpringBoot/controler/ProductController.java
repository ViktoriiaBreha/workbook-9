package com.pluralsight.NorthwindTradersSpringBoot.controler;

import com.pluralsight.NorthwindTradersSpringBoot.dao.ProductDao;
import com.pluralsight.NorthwindTradersSpringBoot.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private ProductDao productDao;

    @Autowired
    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public List<Product> getAllCustomers() {
        return productDao.getAll();
    }

    @RequestMapping(path = "/products/{productId}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable int productId) {
        return productDao.findById(productId);
    }

    @RequestMapping(path = "/product", method = RequestMethod.GET)
    public Product getByName(@RequestParam(defaultValue = " ") String productName) {
        return productDao.getName(productName);
    }

    @RequestMapping(path = "/productss", method = RequestMethod.GET)
        public List<Product> getByPrice (@RequestParam double min, @RequestParam double max){
            return productDao.getByPrice(min,max);
        }
}


