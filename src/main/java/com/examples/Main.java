package com.examples;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        Products product1 = new Products("Electronics", "Electronics", 999.9, 10);
        Products product2 = new Products("Cosmetics", "Shampoo", 5.1, 50);
        Products product3 = new Products("Electronics", "Electronics", 699.6, 5);
        Products product4 = new Products("Electronics", "Electronics", 19.2, 20);
        Products product5 = new Products("Cosmetics", "Conditioner", 6.2, 30);

        inventory.addProduct(product1);
        inventory.addProduct(product2);
        inventory.addProduct(product3);
        inventory.addProduct(product4);
        inventory.addProduct(product5);

        System.out.println("Products by Category (Sorted by Name):");
        Map<String, List<Products>> productsByCategory = inventory.getProductsByCategorySortedByCategory();
        for (Map.Entry<String, List<Products>> entry : productsByCategory.entrySet()) {
            System.out.println("Category: " + entry.getKey());
            for (Products product : entry.getValue()) {
                System.out.println("  " + product);
            }
        }

        System.out.println("Products by Category (Sorted by Quantity):");
        Map<String, Integer> countItemsByCategory = inventory.getItemsCountByCategory();
        for (Map.Entry<String, Integer> entry : countItemsByCategory.entrySet()) {
            System.out.println("Category count: " + entry.getKey());
            productsByCategory.forEach((k,v) -> System.out.println("  " + k + ": " + v));
//            for (Products product  : entry.getValue()) {
//                System.out.println("  " + product);
//            }
        }

    }
}