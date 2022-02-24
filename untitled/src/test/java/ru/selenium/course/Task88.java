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
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class Task88 {
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
    public void task8() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");


        int countryColNum = 4;
        int zoneColNum = 5;
        String currentCountryName = "";
        String nextCountryName = "";
        String currentZoneName = "";
        String nextZoneName = "";

        initTable();

        //а) проверяет, что страны расположены в алфавитном порядке
        for (int i = 2; i < rows.size() - 2; i++) {
            currentCountryName = rows.get(i).findElements(By.tagName("td")).get(countryColNum).getText();
            nextCountryName = rows.get(i + 1).findElements(By.tagName("td")).get(countryColNum).getText();
            Assert.assertTrue(StringUtils.stripAccents(nextCountryName).compareTo(StringUtils.stripAccents(currentCountryName)) > 0);


            //для тех стран, у которых количество зон отлично от нуля -- открывает страницу этой страны и там проверяет,
            // что геозоны расположены в алфавитном порядке
            int zonesCount = Integer.parseInt(rows.get(i).findElements(By.tagName("td")).get(zoneColNum).getText());
            if (zonesCount > 1) {
                driver.findElement(By.linkText(currentCountryName)).click();
                initTable();
                List<WebElement> allZones = table.findElements(By.cssSelector(("[name$='][name]']")));
                for (int j = 0; j < allZones.size() - 1; j++) {

                    currentZoneName = StringUtils.stripAccents((allZones.get(j).getAttribute("value")));
                    nextZoneName = StringUtils.stripAccents(allZones.get(j + 1).getAttribute("value"));
                    Assert.assertTrue(nextZoneName.compareTo(currentZoneName) > 0);
                }
                driver.navigate().back();
                initTable();
            }
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