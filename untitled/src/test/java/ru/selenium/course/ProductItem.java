package ru.selenium.course;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.util.StringTokenizer;


public class ProductItem {

    public WebDriver driver;
    public WebElement prodContainer;
    public WebElement regPriceContainer;
    public WebElement specialPriceContainer;

    public String name;

    public double regularPrice;
    public Color regularPriceColor;
    public String regularPriceTextDecoration;
    public double regularPriceFontSize;

    public double specialPrice;
    public Color specialPriceColor;
    public String specialPriceTextDecoration;
    public Double specialPriceFontSize;

    public ProductItem() {
    }

    public ProductItem(WebDriver driver, By selector) {
        this.driver = driver;
        this.prodContainer = this.driver.findElement(selector);
        initFields();
    }

    public ProductItem(WebDriver driver) {
        this.driver = driver;
        initFields();
    }

    private void initFields() {
        setName();
        setRegPriceContainer();
        setSpecialPriceContainer();
        setRegularPrice();
        setRegularPriceColor();
        setRegularPriceTextDecoration();
        setRegularPriceFontSize();
        setSpecialPrice();
        setSpecialPriceColor();
        setSpecialPriceTextDecoration();
        setSpecialPriceFontSize();
    }

    public void setName() {
        if (this.prodContainer == null) {
            this.name = driver.findElement(By.tagName("h1")).getText();
        } else {
            this.name = prodContainer.findElement(By.className("name")).getText();
        }
    }

    public void setRegPriceContainer() {
        if (this.prodContainer == null) {
            this.regPriceContainer = driver.findElement(By.className("regular-price"));
        } else {
            this.regPriceContainer = prodContainer.findElement(By.className("regular-price"));
        }
    }

    public void setSpecialPriceContainer() {
        if (this.prodContainer == null) {
            this.specialPriceContainer = driver.findElement(By.className("campaign-price"));
        } else {
            this.specialPriceContainer = prodContainer.findElement(By.className("campaign-price"));
        }
    }

    public void setRegularPrice() {
        this.regularPrice = Double.valueOf(regPriceContainer.getText().substring(1));
    }

    public void setRegularPriceColor() {
        this.regularPriceColor = getColor(regPriceContainer.getCssValue("color"));
    }

    public void setRegularPriceTextDecoration() {
        this.regularPriceTextDecoration = regPriceContainer.getCssValue("text-decoration");
    }

    public void setRegularPriceFontSize() {
        this.regularPriceFontSize = Double.valueOf(regPriceContainer.getCssValue("font-size").substring(0, 2));
    }

    public void setSpecialPrice() {
        this.specialPrice = Double.valueOf(specialPriceContainer.getText().substring(1));
    }

    public void setSpecialPriceColor() {
        this.specialPriceColor = ProductItem.getColor(specialPriceContainer.getCssValue("color"));
    }

    public void setSpecialPriceTextDecoration() {
        this.specialPriceTextDecoration = specialPriceContainer.getTagName();
    }

    public void setSpecialPriceFontSize() {
        this.specialPriceFontSize = Double.valueOf(specialPriceContainer.getCssValue("font-size").substring(0, 2));
    }

    public static Color getColor(String color) {
        String s1 = color.substring(5);
        StringTokenizer st = new StringTokenizer(s1);
        int r = Integer.parseInt(st.nextToken(",").trim());
        int g = Integer.parseInt(st.nextToken(",").trim());
        int b = Integer.parseInt(st.nextToken(",").trim());
        return new Color(r, g, b);
    }
}
