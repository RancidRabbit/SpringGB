package ru.gb.spring_webapp.repository;

import org.springframework.stereotype.Repository;
import ru.gb.spring_webapp.entities.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductRepository {

    private List<Product> productList;

    public ProductRepository() {
        this.productList = new ArrayList<>();
    }

    public List<Product> viewAllProducts() {
        return Collections.unmodifiableList(productList);
    }

    @PostConstruct
    public void init() {
        Collections.addAll(productList,new Product(1L,"Motherboard",300),new Product(2L,"CPU",350),
        new Product(3L,"RAM",200),new Product(4L,"GPU",500),new Product(5L,"Storage",150));
    }

    public Product viewProductById(Long id) {
        return productList
                .stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public void saveProduct(Product product) {
        productList.add(product);
    }

}
