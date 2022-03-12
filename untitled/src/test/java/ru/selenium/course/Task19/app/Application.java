package ru.selenium.course.Task19.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.selenium.course.Task19.pages.MainPage;
import ru.selenium.course.Task19.pages.ProductPage;
import ru.selenium.course.Task19.pages.ShoppingCartPage;
import ru.selenium.course.Task19.pages.pageBlocks.Header;


public class Application {

    public WebDriver driver;
    public WebDriverWait wait;
    public MainPage mainPage;
    public ProductPage productPage;
    public ShoppingCartPage shoppingCartPage;
    public Header header;


    public Application() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        header = new Header(driver);
    }


    public void quite() {
        driver.quit();
    }


}