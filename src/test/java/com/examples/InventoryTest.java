package com.examples;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

public class InventoryTest {

    private Inventory inventory;
    private Products product1;
    private Products product2;
    private Products product3;

    @Before
    public void setUp() {
        inventory = new Inventory();
        product1 = new Products("Laptop", "Electronics", 1000.0, 5);
        product2 = new Products("Shampoo", "Cosmetics", 10.0, 50);
        product3 = new Products("Phone", "Electronics", 500.0, 10);
    }

    @Test
    public void testAddProduct() {
        inventory.addProduct(product1);
        assertEquals(1, inventory.getAllProducts().size());
        assertEquals(product1, inventory.getAllProducts().get(0));
    }

    @Test
    public void testRemoveProduct() {
        inventory.addProduct(product1);
        inventory.removeProduct(product1);
        assertEquals(0, inventory.getAllProducts().size());
    }

    @Test
    public void testFindProductByName() {
        inventory.addProduct(product1);
        Products foundProduct = inventory.findProductByName("Electronics");
        assertNotNull(foundProduct);
        assertEquals("Electronics", foundProduct.getName());
    }

    @Test
    public void testFindProductByNameNotFound() {
        Products foundProduct = inventory.findProductByName("NonExistingProduct");
        assertNull(foundProduct);
    }

    @Test
    public void testGetProductsByName() {
        inventory.addProduct(product1);
        inventory.addProduct(product2);
        inventory.addProduct(product3);

        List<Products> electronics = inventory.getProductsByName("Electronics");
        assertEquals(2, electronics.size());

        List<Products> cosmetics = inventory.getProductsByName("Cosmetics");
        assertEquals(1, cosmetics.size());
    }

    @Test
    public void testCalculateTotalValue() {
        inventory.addProduct(product1);
        inventory.addProduct(product2);
        inventory.addProduct(product3);

        double totalValue = inventory.calculateTotalValue();
        assertEquals(10500.0, totalValue, 0.001);
    }

    @Test
    public void testGetItemsCountByCategory() {
        inventory.addProduct(product1);
        inventory.addProduct(product2);
        inventory.addProduct(product3);

        Map<String, Integer> countByCategory = inventory.getItemsCountByCategory();
        assertEquals(3, countByCategory.size());
        assertEquals(Integer.valueOf(5), countByCategory.get("Laptop"));
        assertEquals(Integer.valueOf(50), countByCategory.get("Shampoo"));
    }

    @Test
    public void testGetProductsByCategorySortedByName() {
        inventory.addProduct(product1);
        inventory.addProduct(product2);
        inventory.addProduct(product3);

        Map<String, List<Products>> productsByCategory = inventory.getProductsByCategorySortedByName();
        List<Products> electronics = productsByCategory.get("Electronics");
        assertEquals(2, electronics.size());
        assertEquals("Laptop", electronics.get(0).getName());
        assertEquals("Phone", electronics.get(1).getName());

        List<Products> cosmetics = productsByCategory.get("Cosmetics");
        assertEquals(1, cosmetics.size());
        assertEquals("Shampoo", cosmetics.get(0).getName());
    }

    @Test
    public void testToString() {
        inventory.addProduct(product1);
        String inventoryString = inventory.toString();
        assertTrue(inventoryString.contains("Laptop"));
    }
}

