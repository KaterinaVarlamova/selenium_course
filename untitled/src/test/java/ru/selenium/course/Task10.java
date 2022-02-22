package ru.selenium.course;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Task10 {
    private WebDriver driver;
    private WebDriverWait wait;
    ProductItem productMP;
    ProductItem productPP;

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

    public void loginAdmin() {
        driver.get("http://localhost/litecart/en/");
    }

    public void initTestData() {
        loginAdmin();
        productMP = new ProductItem(driver, By.id("box-campaigns"));
        driver.findElement(By.cssSelector("[title='Yellow Duck']")).click();
        productPP = new ProductItem(driver);
    }

    //на главной странице и на странице товара совпадает текст названия товара
    @Test()
    public void checkProductPageAndMainPage() {
        loginAdmin();
        initTestData();
        Assert.assertTrue(productMP.name.equals(productPP.name));

        //на главной странице и на странице товара совпадаeт обычная цена
        Assert.assertTrue(productMP.regularPrice == productPP.regularPrice);

        //на главной странице и на странице товара совпадаeт акционная цена
        Assert.assertTrue(productMP.specialPrice == productPP.specialPrice);

        //обычная цена зачёркнутая (главная страница)
        Assert.assertTrue(productMP.regularPriceTextDecoration.contains("line-through"));

        //обычная цена серая (главная страница)
        Assert.assertTrue(productMP.regularPriceColor.getBlue() == productMP.regularPriceColor.getGreen() &&
                productMP.regularPriceColor.getGreen() == productMP.regularPriceColor.getRed());

        //акционная цена жирная (главная страница)
        Assert.assertTrue(productMP.specialPriceTextDecoration.contains("strong"));

        //акционная цена красная (главная страница)
        Assert.assertTrue(productMP.specialPriceColor.getBlue() == 0 &&
                productMP.specialPriceColor.getGreen() == 0);

        //акционная цена крупнее, чем обычная (главная страница)
        Assert.assertTrue(productMP.specialPriceFontSize > productMP.regularPriceFontSize);

        //обычная цена зачёркнутая (акционная страница)
        Assert.assertTrue(productMP.regularPriceTextDecoration.contains("line-through"));

        //обычная цена серая (акционная страница)
        Assert.assertTrue(productMP.regularPriceColor.getBlue() == productMP.regularPriceColor.getGreen() &&
                productMP.regularPriceColor.getGreen() == productMP.regularPriceColor.getRed());

        //акционная цена жирная (акционная страница)
        Assert.assertTrue(productMP.specialPriceTextDecoration.contains("strong"));

        //акционная цена красная (акционная страница)
        Assert.assertTrue(productMP.specialPriceColor.getBlue() == 0 &&
                productMP.specialPriceColor.getGreen() == 0);

        //акционная цена крупнее, чем обычная (акционная страница)
        Assert.assertTrue(productMP.specialPriceFontSize > productMP.regularPriceFontSize);
    }
}