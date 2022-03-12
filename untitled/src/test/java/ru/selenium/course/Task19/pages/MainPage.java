package ru.selenium.course.Task19.pages;

import org.openqa.selenium.WebDriver;
import ru.selenium.course.Task19.pages.pageBlocks.ShoppingCart;

import java.util.List;


public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        super.open("http://localhost/litecart");
    }

    public void choosePopularProduct(String name) {
        clickLink("[title='" + name + "']");
    }

    public void addItemsFromShoppingListToCart(List<String> shoppingList) {
        for (int i = 0; i < shoppingList.size(); i++) {
            choosePopularProduct(shoppingList.get(i));
            new ProductPage(driver).addProductToCart();
            new ShoppingCart(driver).wasItemAdded(i + 1);
            goToHomePage();
        }

    }
}