package ru.gb.spring_webapp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.spring_webapp.entities.Product;
import ru.gb.spring_webapp.services.ProductService;

@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/all_products")
    public String allProducts(Model model) {
       model.addAttribute("products", productService.viewAllProducts());
       return "products";
   }

   @GetMapping(value = "/info/{id}")
   public String productInfo(Model model, @PathVariable Long id) {
        model.addAttribute("product", productService.viewProductById(id));
        return "product_info";
   }

   @GetMapping(value = "/new_product")
   public String createProduct() {
        return "create";
   }

   @PostMapping(value = "/new_product")
   public String createProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/all_products";
   }

   @GetMapping(value = "/up/{id}")
   public String up(@PathVariable Long id) {
        productService.costUp(id);
        return "redirect:/all_products";
   }

   @GetMapping(value = "/down/{id}")
   public String down(@PathVariable Long id) {
        productService.costDown(id);
        return "redirect:/all_products";
   }

}
