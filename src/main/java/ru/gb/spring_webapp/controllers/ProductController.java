package ru.gb.spring_webapp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
   public String createProduct(@RequestParam(name = "id") Long id, @RequestParam(name = "title") String title,
                                @RequestParam(name = "cost") int cost) {
        productService.saveProduct(new Product(id, title, cost));
        return "redirect:/all_products";
   }


}
