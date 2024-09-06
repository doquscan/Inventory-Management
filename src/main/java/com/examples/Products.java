package com.examples;

import lombok.Data;

@Data
public class Products {

    String category;
    String name;
    Integer stock;
    Double price;

    public Products(String category,  String name, Double price, Integer stock) {
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Products{" +
                "category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                '}';
    }
}
