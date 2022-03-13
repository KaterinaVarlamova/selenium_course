package ru.selenium.course.Task19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class ProductPage extends Page {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addProductToCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='add_cart_product']"))).click();
        expandPage();
    }
}