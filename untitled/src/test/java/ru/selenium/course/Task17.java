package ru.selenium.course;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


public class Task17 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        LoggingPreferences prefs = new LoggingPreferences();
        prefs.enable("browser", Level.ALL);
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.LOGGING_PREFS, prefs);
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void task17() {
        //1) зайти в админку
        //2) открыть каталог, категорию, которая содержит товары (страница
        //http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1)
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("name")));

        //3) последовательно открывать страницы товаров и проверять, не
        //появляются ли в логе браузера сообщения (любого уровня)
        List<WebElement> products = driver.findElements(By.cssSelector("a[href*='product_id']"));
        List<String> productNames = new ArrayList<String>();
        for (int i = 0; i < products.size(); i += 2) {
            productNames.add(products.get(i).getText());
        }

        for (String productName : productNames) {
            driver.findElement(By.linkText(productName)).click();
            driver.navigate().back();
        }
        driver.manage().logs().get("browser").forEach(l -> {
            System.out.println(l);
            Assert.assertTrue(l.equals(""));
        });
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}