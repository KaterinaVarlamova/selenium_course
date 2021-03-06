package ru.selenium.course.Task11;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task11 {

    private WebDriver driver;
    private WebDriverWait wait;

    String email = System.currentTimeMillis() / 1000L + "@yandex.ru";
    User newUser = new User("Kate", "Varlamova", "United States", email, "password", "89374562526",
            "12345", "California");

    public void logout() {
        driver.findElement(By.linkText("Logout")).click();
    }

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


    @Test
    public void task11() {
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.linkText("New customers click here")).click();
        WebElement loginRegistrationForm = driver.findElement(By.tagName("tbody"));

        loginRegistrationForm.findElement(By.cssSelector("[name='firstname']")).sendKeys(newUser.firstName);
        loginRegistrationForm.findElement(By.cssSelector("[name='lastname']")).sendKeys(newUser.lastName);
        loginRegistrationForm.findElement(By.cssSelector("[name='address1']")).sendKeys(newUser.address1);
        loginRegistrationForm.findElement(By.cssSelector("[name='email']")).sendKeys(newUser.email);
        loginRegistrationForm.findElement(By.cssSelector("[name='password']")).sendKeys(newUser.password);
        loginRegistrationForm.findElement(By.cssSelector("[name='confirmed_password']")).sendKeys(newUser.password);
        loginRegistrationForm.findElement(By.cssSelector("[name='postcode']")).sendKeys(newUser.postcode);
        loginRegistrationForm.findElement(By.cssSelector("[name='city']")).sendKeys(newUser.city);
        loginRegistrationForm.findElement(By.cssSelector("[name='phone']")).click(); //?????????????? ????????
        loginRegistrationForm.findElement(By.cssSelector("[name='phone']")).sendKeys(newUser.phone);

        loginRegistrationForm.findElement(By.xpath("//span[@class = 'select2-selection__arrow']")).click();
        loginRegistrationForm.findElement(By.xpath("(//li[contains(text(), 'United States')])[1]")).click();

        loginRegistrationForm.findElement(By.cssSelector("[name='create_account']")).click();
        // 2) ?????????? (logout), ???????????? ?????? ?????????? ???????????????? ?????????????????????? ?????????????????????????? ???????????????????? ????????,
        logout();


        // 3) ?????????????????? ???????? ?? ???????????? ?????? ?????????????????? ?????????????? ????????????,
        WebElement loginForm = driver.findElement(By.id("box-account-login"));

        loginForm.findElement(By.cssSelector("[name='email']")).sendKeys(newUser.email);
        loginForm.findElement(By.cssSelector("[name='password']")).sendKeys(newUser.password);


        // 4) ?? ?????? ?????? ??????????.
        loginForm.findElement(By.cssSelector("[name='login']")).click();
        logout();
    }
}