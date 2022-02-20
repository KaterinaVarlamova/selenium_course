package ru.selenium.course;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Task7 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void task7() {
        //перейти на главную страницу
        driver.get("http://localhost/litecart/en/");
        //найти все товары
        //проверить, что у всех товаров есть ровно 1 стикер
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='image-wrapper']"));
        for (int i = 0; i < elements.size(); i++) {
            driver.findElements(By.xpath("//div[@class='image-wrapper']")).get(i).
                    findElement(By.xpath("//div[contains(@class,'sticker new') or contains(@class,'sticker sale')]"));
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
