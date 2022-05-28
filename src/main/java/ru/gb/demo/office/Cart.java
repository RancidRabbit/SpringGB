package ru.gb.demo.office;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)

public class Cart {

    private static List<Product> cartProducts;

    public Cart() {
        cartProducts = new ArrayList<>();
    }

    public static List<Product> getCartProducts() {
        return cartProducts;
    }


    public static void setCartProducts(List<Product> cartProducts) {
        Cart.cartProducts = cartProducts;
    }
}
