package ru.selenium.course;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Task13 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


    List<String> itemsToBuy = new ArrayList<>(Arrays.asList("Blue Duck", "Purple Duck", "Green Duck"));

    @Test
    public void task13() {
        //1) открыть главную страницу
        driver.get("http://localhost/litecart");

        for (int i = 0; i < 3; i++) {
            //2) открыть первый товар из списка
            driver.findElement(By.cssSelector("[title='" + itemsToBuy.get(i) + "']")).click();

            //2) добавить его в корзину (при этом может случайно добавиться товар,
            //     * который там уже есть, ничего страшного)
            driver.findElement(By.cssSelector("[name='add_cart_product']")).click();

            //3) подождать, пока счётчик товаров в корзине обновится
            wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.className("quantity")), "" + (i + 1)));

            // 4) вернуться на главную страницу, повторить предыдущие шаги ещё два
            //     * раза, чтобы в общей сложности в корзине было 3 единицы товара
            driver.findElement(By.cssSelector("[href='/litecart/']")).click();
        }

        // 5) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)
        driver.findElement(By.cssSelector("[id='cart']")).click();

        //6) удалить все товары из корзины один за другим, после каждого удаления
        //     * подождать, пока внизу обновится таблица
        WebElement buttonDeleteItem;
        while (driver.findElements(By.cssSelector("[name='remove_cart_item']")).size() != 0) {
            buttonDeleteItem = driver.findElement(By.cssSelector("[name='remove_cart_item']"));
            buttonDeleteItem.click();
            wait.until(ExpectedConditions.stalenessOf(buttonDeleteItem));
        }

        Assert.assertTrue(driver.findElement(By.tagName("p")).getText().contains("There are no items in your cart."));
    }

}