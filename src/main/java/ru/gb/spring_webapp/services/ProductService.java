package ru.gb.spring_webapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gb.spring_webapp.entities.Product;
import ru.gb.spring_webapp.repository.ProductRepository;

import java.util.List;

@Component
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> viewAllProducts(){
       return productRepository.viewAllProducts();
    }

    public Product viewProductById(Long id){
        return productRepository.viewProductById(id);
    }

    public void saveProduct(Product product) {
        productRepository.saveProduct(product);
    }

    public void costUp(Long id){
        productRepository.costUp(id);
    }

    public void costDown(Long id) {
        productRepository.costDown(id);
    }


}
