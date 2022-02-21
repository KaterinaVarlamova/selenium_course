package ru.selenium.course;

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

    public void loginAdmin(){
        //входит в панель администратора http://localhost/litecart/admin
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get("http://localhost/litecart/en/");
    }

    public void initTestData(){
        productMP = new ProductItem(driver, By.id("box-campaigns"));
        driver.findElement(By.cssSelector("[title='Yellow Duck']")).click();
        productPP = new ProductItem(driver);
    }

    //на главной странице и на странице товара совпадает текст названия товара
    @Test()
    public void testProductNamesAreSame() {
        loginAdmin();
        initTestData();
        Assert.assertTrue(productMP.name.equals(productPP.name));
    }

    //на главной странице и на странице товара совпадаeт обычная цена
    @Test()
    public void testRegularPricesAreSame() {
        loginAdmin();
        initTestData();
        Assert.assertTrue(productMP.regularPrice == productPP.regularPrice);
    }

    //на главной странице и на странице товара совпадаeт акционная цена
    @Test()
    public void testSpecialPricesAreSame() {
        loginAdmin();
        initTestData();
        Assert.assertTrue(productMP.specialPrice == productPP.specialPrice);
    }

    //обычная цена зачёркнутая (главная страница)
    @Test()
    public void testRegularPriceIsLignedThroughMP() {
        loginAdmin();
        initTestData();
        Assert.assertTrue(productMP.regularPriceTextDecoration.contains("line-through"));
    }

    //обычная цена серая (главная страница)
    @Test()
    public void testRegularPriceIsGrayMP() {
        loginAdmin();
        initTestData();
        Assert.assertTrue(productMP.regularPriceColor.getBlue() == productMP.regularPriceColor.getGreen() &&
                productMP.regularPriceColor.getGreen() == productMP.regularPriceColor.getRed());
    }

    //акционная цена жирная (главная страница)
    @Test()
    public void testSpecialPriceIsStrongMP() {
        loginAdmin();
        initTestData();
        Assert.assertTrue(productMP.specialPriceTextDecoration.contains("strong"));
    }

    //акционная цена красная (главная страница)
    @Test()
    public void testSpecialrPriceIsRedMP() {
        loginAdmin();
        initTestData();
        Assert.assertTrue(productMP.specialPriceColor.getBlue() == 0 &&
                productMP.specialPriceColor.getGreen() == 0);
    }

    //акционная цена крупнее, чем обычная (главная страница)
    @Test()
    public void testSpecialPriceFontGreaterThanRegularMP() {
        loginAdmin();
        initTestData();
        Assert.assertTrue(productMP.specialPriceFontSize > productMP.regularPriceFontSize);
    }

    //обычная цена зачёркнутая (акционная страница)
    @Test()
    public void testRegularPriceIsLignedThroughPP() {
        loginAdmin();
        initTestData();
        Assert.assertTrue(productMP.regularPriceTextDecoration.contains("line-through"));
    }

    //обычная цена серая (акционная страница)
    @Test()
    public void testRegularPriceIsGrayPP() {
        loginAdmin();
        initTestData();
        Assert.assertTrue(productMP.regularPriceColor.getBlue() == productMP.regularPriceColor.getGreen() &&
                productMP.regularPriceColor.getGreen() == productMP.regularPriceColor.getRed());
    }

    //акционная цена жирная (акционная страница)
    @Test()
    public void testSpecialPriceIsStrongPP() {
        loginAdmin();
        initTestData();
        Assert.assertTrue(productMP.specialPriceTextDecoration.contains("strong"));
    }

    //акционная цена красная (акционная страница)
    @Test()
    public void testSpecialrPriceIsRedPP() {
        loginAdmin();
        initTestData();
        Assert.assertTrue(productMP.specialPriceColor.getBlue() == 0 &&
                productMP.specialPriceColor.getGreen() == 0);
    }

    //акционная цена крупнее, чем обычная (акционная страница)
    @Test()
    public void testSpecialPriceFontGreaterThanRegularPP() {
        loginAdmin();
        initTestData();
        Assert.assertTrue(productMP.specialPriceFontSize > productMP.regularPriceFontSize);
    }
}