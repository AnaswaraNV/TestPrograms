package tester;

import model.Product;
import util.MyClass;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UtilTester {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();

        Product p1 = new Product("p1", "XApple", 1240.00);
        Product p2 = new Product("p2", "Samsung", 1800.00);
        Product p3 = new Product("p3", "Experia", 789.50);

        products.add(p1);
        products.add(p2);
        products.add(p3);

        products.sort(Comparator.comparing(Product::getName));

        products.forEach((product -> {
            System.out.println(product.getName());
        }));

        MyClass myClass = new MyClass();
        myClass.log("hello from util");
    }
}
