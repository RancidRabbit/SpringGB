package ru.gb.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gb.demo.config.ApplicationConfiguration;
import ru.gb.demo.constant.Constant;
import ru.gb.demo.office.Product;
import ru.gb.demo.office.ProductRepository;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        Product VGA = new Product(1, "vga", 300);
        Product DDR = new Product(2, "ddr", 200);
        Product CPU = new Product(3, "cpu", 150);
        Product SSD = new Product(4, "ssd", 100);
        Product RAM = new Product(5, "ram", 50);

        ProductRepository pr = context.getBean(ProductRepository.class);
        pr.add(VGA);
        pr.add(DDR);
        pr.add(CPU);
        pr.add(SSD);
        pr.add(RAM);

        Scanner sc = new Scanner(System.in);
        String console;
        System.out.println("Введите команду " + "\n" +
                "[ap] - посмотреть список всех продуктов" + "\n" +
                "[end] - выход из приложения" + "\n" +
                "[id (id продукта)] - посмотреть продукт по id" + "\n" +
                "[new] - создать новую корзину" + "\n" +
                "[view] - посмотреть товары в корзине" + "\n" +
                "[add (id продукта)] - добавить продукт в корзину" + "\n" +
                "[del (id продукта)] - удалить товар из корзины");
        while (true) {
            console = sc.nextLine();
            switch (console) {
                case "ap": {
                    pr.viewAllProducts();
                    break;
                }
                case "new": {
                    pr.cartMethods("new");
                    break;
                }
                case "view": {
                    pr.cartMethods("view");
                    break;
                }
                case "end":
                    System.exit(0);
                default: {
                    if (console.startsWith("id")) {
                        try {
                            final String[] s = console.split(" ");
                            pr.viewProductById(Integer.parseInt(s[1]));
                            break;
                        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                            System.out.println(Constant.ANSI_RED + "Команда имеет вид: id_[номер продукта]" + Constant.ANSI_RESET);
                            break;
                        }
                    }
                    if (console.startsWith("add")) {
                        try {
                            pr.cartMethods(console);
                            break;
                        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                            System.out.println(Constant.ANSI_RED + "Команда имеет вид: add_[номер продукта]" + Constant.ANSI_RESET);
                            break;
                        }
                    }
                    if (console.startsWith("del")) {
                        try {
                            pr.cartMethods(console);
                            break;
                        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                            System.out.println(Constant.ANSI_RED + "Команда имеет вид: del_[номер продукта]" + Constant.ANSI_RESET);
                            break;
                        }
                    }
                    System.out.println("Такой команды не существует");
                    break;
                }
            }
        }
    }
}
