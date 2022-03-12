package ru.selenium.course.Task19.test;

import org.junit.After;
import org.openqa.selenium.By;
import ru.selenium.course.Task19.app.Application;


public class BaseTest {

    Application app = new Application();

    @After
    public void finish() {
        app.quite();
    }

    void login(String username, String password, String confirmLocator) {
        app.driver.get("http://localhost/litecart/admin/login.php");
        app.driver.findElement(By.name("username")).sendKeys(username);
        app.driver.findElement(By.name("password")).sendKeys(password);
        app.driver.findElement(By.xpath(confirmLocator)).click();
    }

    public void logout() {
        app.driver.findElement(By.className("fa-sign-out")).click();
    }

}