package ru.selenium.course.Task19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

    public WebDriver driver;
    public WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 60);
    }

    public void open(String url) {
        driver.navigate().to(url);
    }

    public void clickLink(String linkLocator) {
        driver.findElement(By.cssSelector(linkLocator)).click();
    }


    public void expandPage() {
        try {
            driver.findElement(By.linkText("View full page")).click();
        } catch (Exception e) {
        }
    }

    public void goToCheckOut() {
        clickLink("[id='cart']");
    }

    public void goToHomePage() {
        clickLink("[href='/litecart/']");
    }

}