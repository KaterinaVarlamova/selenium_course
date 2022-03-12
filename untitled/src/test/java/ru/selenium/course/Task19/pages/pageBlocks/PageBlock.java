package ru.selenium.course.Task19.pages.pageBlocks;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBlock {
    public WebDriver driver;
    public WebDriverWait wait;

    public PageBlock(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

}