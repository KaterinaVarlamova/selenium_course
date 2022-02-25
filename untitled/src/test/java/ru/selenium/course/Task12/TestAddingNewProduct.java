package ru.selenium.course.Task12;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;


public class TestAddingNewProduct {
    private WebDriver driver;
    private WebDriverWait wait;
    NewProduct newProduct = new NewProduct();

    public void checkCheckBox(By selector){

        WebElement checkBox = driver.findElement(selector);
        if(checkBox.getAttribute("checked") == null){
            checkBox.click();
        }
    }

    public void uncheckCheckBox(By selector){

        WebElement checkBox = driver.findElement(selector);
        if(checkBox.getAttribute("checked") != null){
            checkBox.click();
        }
    }

    public void fillOutField(String selector, String value){
        driver.findElement(By.cssSelector(selector)).clear();
        driver.findElement(By.cssSelector(selector)).sendKeys(value);
    }

    public void selectFromDropDownList(String selector, String value){
        Select select = new Select(driver.findElement(By.cssSelector(selector)));
        select.selectByVisibleText(value);
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test()
    public void testAddingNewProduct() {
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        createNewProduct();
        // переходим в Каталог и создаем новый продукт
        driver.findElement(By.cssSelector("a[href*='catalog']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        Assert.assertTrue(driver.findElement(By.tagName("h1")).getText().equals("Catalog"));

        driver.findElement(By.linkText("Add New Product")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        Assert.assertTrue(driver.findElement(By.tagName("h1")).getText().equals("Add New Product"));
        
        // заполняем поля во вкладке "General"
        uncheckCheckBox(By.cssSelector("[data-name='Root']"));
        checkCheckBox(By.cssSelector("[data-name='Rubber Ducks']"));
        fillOutField("[name='code']", newProduct.code);
        fillOutField("[name='name[en]']", newProduct.name);
        fillOutField("[name='quantity']", newProduct.quantity);
        driver.findElement(By.cssSelector("[name='new_images[]']")).sendKeys(newProduct.picture);

        //вкладка "Information"
        driver.findElement(By.linkText("Information")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='manufacturer_id']")));
        selectFromDropDownList("[name='manufacturer_id']", "ACME Corp.");
        fillOutField("[name='short_description[en]']", newProduct.shortDescription);
        fillOutField("[name='description[en]']", newProduct.shortDescription);

        //вкладка "Prices"
        driver.findElement(By.linkText("Prices")).click();
        fillOutField("[name='purchase_price']", newProduct.price);
        selectFromDropDownList("[name='purchase_price_currency_code'", "Euros");
        fillOutField("[name='gross_prices[USD]']", newProduct.price);
        fillOutField("[name='gross_prices[EUR]']", newProduct.price);

        driver.findElement(By.cssSelector("[name='save']")).click();

        //проверим, что товар добавился
        driver.findElement(By.cssSelector("a[href*='catalog']")).click();
        driver.findElement(By.linkText("Rubber Ducks")).click();
        Assert.assertTrue(driver.findElement(By.linkText("New Product")).isDisplayed());
    }



    void createNewProduct() {
        String pathToPicFile = new File("src/test/java/resources/image.png")
                .getAbsolutePath();
        newProduct.withName("New Product").withCode("RD00x").withHeadTitle("New Product").withQuantity(25).withPrice(12.00)
                .withPicture(pathToPicFile);

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}