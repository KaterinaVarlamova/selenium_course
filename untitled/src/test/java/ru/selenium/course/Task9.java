package ru.selenium.course;


import org.apache.commons.lang3.StringUtils;
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


public class Task9 {
    private WebDriver driver;
    private WebDriverWait wait;
    private WebElement table;
    private List<WebElement> rows;

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

        initTable();

        int countryColNum = 2;
        int zoneColNum = 2;
        String countryName = "";
        String currentZoneName = "";
        String nextZoneName = "";

        //  заходит в каждую из стран и проверяет, что зоны расположены в алфавитном порядке.
        for (int i = 1; i < rows.size()-1; i++) {

            countryName = rows.get(i).findElements(By.tagName("td")).get(countryColNum).getText();
            driver.findElement(By.linkText(countryName)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("td")));
            initTable();
            for (int j = 1; j < rows.size() - 1; j++) {

                currentZoneName = StringUtils.stripAccents(rows.get(j).findElements(By.tagName("td")).
                        get(zoneColNum).getText());
                nextZoneName = StringUtils.stripAccents(rows.get(j + 1).findElements(By.tagName("td")).
                        get(zoneColNum).getText());
                Assert.assertTrue(nextZoneName.compareTo(currentZoneName) > 0);
            }
            driver.navigate().back();
            initTable();

        }
        driver.findElement(By.className("fa-sign-out")).click();
    }


    private void initTable() {
        table = driver.findElement(By.cssSelector("#content > form > table > tbody"));
        rows = table.findElements(By.tagName("tr"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
