package ru.selenium.course;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Task6 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void task6() {
        //входит в панель администратора http://localhost/litecart/admin
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        //прокликивает последовательно все пункты меню слева, включая вложенные пункты

        List<WebElement> elements = driver.findElements(By.cssSelector("#box-apps-menu-wrapper a"));

        List<String> elementNames = getElementNames(elements);

        for (String s : elementNames) {
            driver.findElement(By.xpath("//span[text()='" + s + "']")).click();
            isTitlePresent(driver);

            List<WebElement> menuSubItems = driver.findElements(By.cssSelector(("[id^=doc-]")));
            List<String> leftSubMenuItemNames = getElementNames(menuSubItems);

            for (String se : leftSubMenuItemNames) {
                driver.findElement(By.xpath("//span[text()='" + se + "']")).click();
                isTitlePresent(driver);
            }
        }
    }

    //для каждой страницы проверяет наличие заголовка (то есть элемента с тегом h1)
    boolean isTitlePresent(WebDriver driver) {
        try {
            driver.findElement(By.xpath("//h1"));
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private List<String> getElementNames(List<WebElement> elements) {
        List<String> names = new ArrayList<String>();
        for (WebElement e : elements) {
            names.add(e.getText());
        }

        return names;
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
