package ru.selenium.course.Task19.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class ShoppingCartPage extends Page {

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }


    public void deleteAllItemsFromCart() {

        WebElement buttonDeleteItem;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name='remove_cart_item']")));
        while (driver.findElements(By.cssSelector("[name='remove_cart_item']")).size() != 0) {
            buttonDeleteItem = driver.findElement(By.cssSelector("[name='remove_cart_item']"));
            buttonDeleteItem.click();
            wait.until(ExpectedConditions.stalenessOf(buttonDeleteItem));
        }
    }

    public boolean isCartEmpty(){
        return driver.findElement(By.tagName("p")).getText().contains("There are no items in your cart.");
    }
}