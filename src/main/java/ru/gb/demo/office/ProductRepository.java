package ru.gb.demo.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.gb.demo.constant.Constant;

import javax.annotation.PostConstruct;
import javax.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ProductRepository {

    private List<Product> productList;

    private Provider<Cart> cartProvider;


    @Autowired
    public void setCartProvider(Provider<Cart> cartProvider) {
        this.cartProvider = cartProvider;
    }

    public void add(Product product) {
        productList.add(product);
    }

    public void viewAllProducts() {
        productList
                .stream()
                .map(Product::toString)
                .forEach(System.out::println);

    }

    public void viewProductById(int id) {
        productList
                .stream()
                .filter(f -> f.getId() == id)
                .map(Product::toString)
                .forEach(System.out::println);
    }

    public ProductRepository() {
        productList = new ArrayList<>();
    }


    public void cartMethods(String s) {
        switch (s) {
            case "new": {
                newCart();
                break;
            }
            case "view": {
                viewCart();
                break;
            }
            default:
                if (s.startsWith("add")) {
                    final String[] split = s.split(" ");
                    addToCart(Integer.parseInt(split[1]));
                    break;
                }
                if (s.startsWith("del")) {
                    final String[] split = s.split(" ");
                    delFromCart(Integer.parseInt(split[1]));
                    break;
                }
        }

    }

    @PostConstruct
    public Cart cartInit() {
        return cartProvider.get();
    }

    public Cart newCart() {
        System.out.println("Вы создали новую корзину");
        return cartProvider.get();

    }

    public void addToCart(int id) {
        try {
            if (!Cart.getCartProducts().contains(productList.get(id - 1))) {
                Cart.getCartProducts().add(productList.get(id - 1));
                System.out.println("Продукт: " + productList.get(id - 1).toString() + " добавлен в корзину");
            } else System.out.println("Данный продукт уже есть в корзине!");

        } catch (IndexOutOfBoundsException e) {
            System.out.println(Constant.ANSI_RED + "Данный товар не найден" + Constant.ANSI_RESET);
        }
    }

    public void viewCart() {
        if (!Cart.getCartProducts().isEmpty()) {
            System.out.println(Cart.getCartProducts());
        } else System.out.println("Корзина пустая!");
    }

    public void delFromCart(int id) {
        try {
            if (Cart.getCartProducts().contains(productList.get(id - 1))) {
                Cart.getCartProducts().remove(productList.get(id - 1));
                System.out.println("Продукт: " + productList.get(id - 1).toString() + " удален из корзины");
            } else System.out.println("Такого продукта нет в корзине");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Constant.ANSI_RED + "Данный товар не найден" + Constant.ANSI_RESET);
        }
    }
}
