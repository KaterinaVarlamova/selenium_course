package ru.selenium.course.Task19.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TestShoppingCart extends BaseTest {
    List<String> shoppingList = new ArrayList<>(Arrays.asList("Blue Duck", "Purple Duck", "Green Duck"));

    @Test
    public void testAddingProductsToCart() {
        app.mainPage.open();
        app.mainPage.addItemsFromShoppingListToCart(shoppingList);
        app.productPage.goToCheckOut();
        app.shoppingCartPage.deleteAllItemsFromCart();
        Assert.assertTrue(app.shoppingCartPage.isCartEmpty());
    }
}
