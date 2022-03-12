package ru.selenium.course.Task19.pages.pageBlocks;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ShoppingCart extends PageBlock {

    public ShoppingCart(WebDriver driver) {
        super(driver);
    }

    public int getItemsCount() {
        return Integer.valueOf(driver.findElement(By.className("quantity")).getText());
    }


    public void wasItemAdded(int countAfter) {
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                //
                if (getItemsCount() == countAfter) return true;
                return null;
            }
        });
    }
}