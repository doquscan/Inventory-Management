package com.examples;

import java.util.*;
import java.util.stream.Collectors;

public class Inventory {

    private List<Products> products;

    public Inventory() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Products product) {
        this.products.add(product);
    }

    public void removeProduct(Products product) {
        this.products.remove(product);
    }

    public Products findProductByName(String name) {
        return products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

//        for (Products product : products) {
//            if (product.getName().equalsIgnoreCase(name)) {
//                return product;
//            }
//        }
//        return null;
    }

    public List<Products> getProductsByName(String name) {
        List<Products> result = products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
        return result;
//        List<Products> result = new ArrayList<>();
//        for (Products product : products) {
//            if (product.getName().equalsIgnoreCase(name)) {
//                result.add(product);
//             }
//        }
//        return result;
    }

    public List<Products> getAllProducts() {
        return products;
    }

    public double calculateTotalValue() {
        double totalValue = products.stream()
                .mapToDouble(product -> product.getPrice() * product.getStock())
                .sum();
        return totalValue;
//        double totalValue = 0;
//        for (Products product : products) {
//            totalValue += product.getPrice() * product.getStock();
//        }
//        return totalValue;
    }

    public Map<String,Double> getCalculateTotalValueByCategory() {
        Map<String, Double> result = products.stream()
                .collect(Collectors.toMap(
                        Products::getCategory,
                        product -> product.getPrice() * product.getStock(),
                        Double::sum));
        return result;
//        Map<String,Double> result = new HashMap<>();
//        for (Products product : products) {
//            result.put(product.getCategory(), result.getOrDefault(product.getCategory(),0.0) + product.getPrice() * product.getStock());
//        }
//        return result;
    }

//sort by less amount of items
    public Map<String, Integer> getItemsCountByCategory() {
        Map<String, Integer> countByCategory = new HashMap<>();
        for (Products product : products) {
            countByCategory.put(
                    product.getCategory(),
                    countByCategory.getOrDefault(product.getCategory(), 0) + product.getStock()
            );
        }
        return countByCategory;
    }
//    For smaller text lengths, the for loop approach tends to outperform the stream approach in terms of performance.
//    This is likely due to the overhead introduced by stream processing.
//        However, as we increase the text size, the performance gap between the for loop and stream approaches narrows significantly.

    public Map<String, List<Products>> getProductsByCategorySortedByName() {
//        Map<String, List<Products>> productsByCategory = new HashMap<>();
//        for (Products product : products) {
//            String category = product.getCategory();
//            if (!productsByCategory.containsKey(category)) {
//                productsByCategory.put(category, new ArrayList<>());
//            }
//            productsByCategory.get(category).add(product);
//        }
        Map<String, List<Products>> productsByCategory = products.stream()
                .collect(Collectors.groupingBy(Products::getCategory));

        // Sort each list of products by name
//        for (List<Products> productList : productsByCategory.values()) {
//            Collections.sort(productList, new Comparator<Products>() {
//                @Override
//                public int compare(Products p1, Products p2) {
//                    return p1.getName().compareToIgnoreCase(p2.getName());
//                }
//            });
//        }
//
//        return productsByCategory;
        productsByCategory.values().forEach(productList ->
                productList.sort(Comparator.comparing(Products::getName, String.CASE_INSENSITIVE_ORDER))
        );

        return productsByCategory;

    }

    public Map<String, List<Products>> getProductsByCategorySortedByStock() {
        Map<String, List<Products>> productsByCategory = new HashMap<>();
        for (Products product : products) {
            String category = product.getCategory();
            if (!productsByCategory.containsKey(category)) {
                productsByCategory.put(category, new ArrayList<>());
            }
            productsByCategory.get(category).add(product);
        }

        // Sort each list of products by name
        for (List<Products> productList : productsByCategory.values()) {
            Collections.sort(productList, new Comparator<Products>() {
                @Override
                public int compare(Products p1, Products p2) {

                    return Integer.compare(p1.getStock(),p2.getStock());
                }
            });
        }

        return productsByCategory;
    }
    public Map<String,List<Products>> getProductsByCategorySortedByCategory() {
        Map<String,List<Products>> productsByCategory = new HashMap<>();
        for(Products product: products) {
            String category = product.getCategory();
            if(!productsByCategory.containsKey(category)) {
                productsByCategory.put(category, new ArrayList<>());
            }
            productsByCategory.get(category).add(product);
        }
        for(List<Products> productList : productsByCategory.values()) {
            Collections.sort(productList,new Comparator<Products>() {
                @Override
                public int compare(Products p1, Products p2) {
                    return p1.getCategory().compareToIgnoreCase(p2.getCategory());
                }
            });
        }
        return productsByCategory;
    }


    @Override
    public String toString() {
        return "Inventory{" +
                "products=" + products +
                '}';
    }

}
