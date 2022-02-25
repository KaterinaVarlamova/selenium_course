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

import java.util.List;
import java.util.stream.Collectors;


public class Task9 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void task9() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");



        List<String> list;
        int countryNumber = driver.findElements(By.cssSelector("td>a:not(a[title])")).size();


        //  заходит в каждую из стран и проверяет, что зоны расположены в алфавитном порядке.
        for (int i = 0; i < countryNumber ; i++) {
            driver.findElement(By.cssSelector(String.format(".row:nth-child(%d)>td>a:not(a[title])", i+2))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("td")));

            list = driver.findElements(By.cssSelector("[name$='[zone_code]']  [selected~=selected]"))
                    .stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());

            for (int j = 0; j < list.size() - 1; j++) {
                Assert.assertTrue(list.get(j).compareTo(list.get(j + 1)) < 0);
            }

            driver.navigate().back();

        }
        driver.findElement(By.className("fa-sign-out")).click();
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
